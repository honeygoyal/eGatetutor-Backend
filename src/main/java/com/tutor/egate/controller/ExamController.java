package com.tutor.egate.controller;

import com.tutor.egate.Type;
import com.tutor.egate.exception.TestExistException;
import com.tutor.egate.model.*;
import com.tutor.egate.repo.QuestionRepository;
import com.tutor.egate.repo.TestRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/question")
public class ExamController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private Environment env;


    @GetMapping
    public ResponseEntity<Map<String, List<QuestionResponseModel>>> getQuestionForTest(@RequestParam("test_id") String test_id) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<Question> questionList = questionRepository.findQuestionsById(test_id);
       // QuestionResponseModel[] returnvalue = new QuestionResponseModel[questionList.size()];
        Map<String, List<QuestionResponseModel>> questionMap  = new HashMap<String, List<QuestionResponseModel>>();
        
        for (int i = 0; i < questionList.size(); i++) {
            Path quePath = Paths.get(questionList.get(i).getQuestion()+".png");
            Path solPath = Paths.get(questionList.get(i).getSolution()+".png");
            byte[] imageque = Files.readAllBytes(quePath);
            byte[] imagesol = Files.readAllBytes(solPath);
            String encodedQuestion = Base64.getEncoder().encodeToString(imageque);
            String encodedSolution = Base64.getEncoder().encodeToString(imagesol);
            List<QuestionResponseModel> tempList = new ArrayList<QuestionResponseModel>();
            questionList.get(i).setQuestion(encodedQuestion);
            questionList.get(i).setSolution(encodedSolution);
            if(questionMap.containsKey(questionList.get(i).getSection())){
                tempList = questionMap.get(questionList.get(i).getSection());
                tempList.add(modelMapper.map(questionList.get(i), QuestionResponseModel.class));
                questionMap.put(questionList.get(i).getSection(), tempList);
            }else{
                tempList.add(modelMapper.map(questionList.get(i), QuestionResponseModel.class));
                questionMap.put(questionList.get(i).getSection(), tempList);
            }
        }
        for(String ss: questionMap.keySet()){
            System.out.println("Section: "+ ss);
            for(QuestionResponseModel qq: questionMap.get(ss)){
                System.out.println(qq.getQuestionDifficulty());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionMap);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "Make a POST request to upload the file",
            produces = "text/plain", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The POST call is Successful"),
            @ApiResponse(code = 500, message = "The POST call is Failed"),
            @ApiResponse(code = 404, message = "The API could not be found")
    })
    public ResponseEntity<String> uploadFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file,
            String test_id
    )  {

        try{
            File testFile = new File("test");
            FileUtils.writeByteArrayToFile(testFile, file.getBytes());
            String BASE_URL = env.getProperty("image_base_url");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(testFile));
            Iterator bodyElementIterator = xdoc.getBodyElementsIterator();
            List<Question> questionsList = new ArrayList();
            Question question = new Question();
            Optional<Test> t = testRepository.findById(test_id);
            Test test = null;
            if (!t.isPresent()) {
                throw new NoSuchElementException("No test is present");
            }
            test = t.get();
            question.setTest_id(test);
            while (bodyElementIterator.hasNext()) {
                IBodyElement element = (IBodyElement) bodyElementIterator.next();

                if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
                    List<XWPFTable> tableList = element.getBody().getTables();
                    for (XWPFTable table : tableList) {
                        int k = 1;
                        for (int i = 0; i < table.getRows().size(); i++) {
                            String typeString = table.getRow(i).getCell(0).getText();
                            String actualText = table.getRow(i).getCell(1).getText();
                            Type type = Type.find(typeString);
                            String s = "";
                            switch (type) {
                                case SECTION:
                                    question.setSection(actualText);
                                    break;
                                case MARKS:
                                    question.setMarks(actualText);
                                    break;
                                case NEGATIVE_MARKS:
                                    question.setNegativeMarks(actualText);
                                    break;
                                case QUESTION_TYPE:
                                    question.setType(actualText);
                                    break;
                                case QUESTION_LABEL:
                                    question.setQuestionLabel(Integer.parseInt(typeString));
                                    String URL = BASE_URL + "/" + question.getTest_id().getTest_id() + "_" + question.getSection() + "_Question_" + question.getQuestionLabel();
                                    /*TODO: Save Image*/
                                    for (XWPFParagraph p : table.getRow(i).getCell(1).getParagraphs()) {
                                        for (XWPFRun run : p.getRuns()) {
                                            for (XWPFPicture pic : run.getEmbeddedPictures()) {
                                                byte[] pictureData = pic.getPictureData().getData();
                                               // URL = URL+ count[c];
                                                BufferedImage imag=ImageIO.read(new ByteArrayInputStream(pictureData));
                                                ImageIO.write(imag, "png", new File(URL +".png"));
                                            }
                                        }

                                    }
                                    question.setQuestion(URL);
                                    break;
                                case ANSWER:
                                    question.setAnswer(actualText);
                                    break;
                                case SOLUTION:
                                    String SURL = BASE_URL + "/" + question.getTest_id().getTest_id() + "_" + question.getSection() + "_Solution_" + question.getQuestionLabel();
                                    /*TODO: Save Image*/
                                    for (XWPFParagraph p : table.getRow(i).getCell(1).getParagraphs()) {
                                        for (XWPFRun run : p.getRuns()) {
                                            for (XWPFPicture pic : run.getEmbeddedPictures()) {
                                                byte[] pictureData = pic.getPictureData().getData();
                                                BufferedImage imag=ImageIO.read(new ByteArrayInputStream(pictureData));
                                                ImageIO.write(imag, "png", new File(SURL +".png"));
                                               // break;
                                                //c++;
                                            }
                                        }

                                    }
                                    question.setSolution(SURL);
                                    break;
                                case DIFFICULTY:
                                    question.setQuestionDifficulty(actualText);
                                    break;
                                case VIDEO_LINK:
                                    question.setVideoLink(actualText);
                                    break;
                            }

                            k++;
                            if (k == 10) {
                                questionsList.add(question);
                                k = 1;
                                question = new Question();
                                question.setTest_id(test);
                            }
                        }
                    }
                }
            }


            for(Question q: questionsList){
                q.setPk(new QuestionCompositeKey(q.getSection(), q.getQuestionLabel(), q.getTest_id().getTest_id()));
                questionRepository.save(q);
            }
           // questionRepository.saveAll( (Iterable<? extends Question>) questionsList);

            //questionRepository.insertQuestions(questionsList);

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failed ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }



}
