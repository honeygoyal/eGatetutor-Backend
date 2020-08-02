package com.tutor.egate.service;


import com.tutor.egate.exception.TestExistException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tutor.egate.model.ExamReference;
import com.tutor.egate.model.Test;
import com.tutor.egate.model.TestResponseModel;
import com.tutor.egate.model.User;
import com.tutor.egate.model.User2test;
import com.tutor.egate.repo.ExamReferenceRepository;
import com.tutor.egate.repo.TestRepository;
import com.tutor.egate.repo.User2TestRepository;
import com.tutor.egate.repo.UserRepository;

import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private ExamReferenceRepository examref;
	@Autowired
	private TestRepository testRepository;
	@Autowired
	private User2TestRepository user2TestRepo;

	@Override
	public TestResponseModel[]  getTest(String service,String email) {
	   ExamReference examrefresult= examref.findByexam_code(service);
	   Test[]  returntest;
	   User userEntity=userRepository.findByEmail(email);
		returntest = testRepository.findTestByExamRef(examrefresult.id);
		TestResponseModel[] returntestresponse=new TestResponseModel[returntest.length];
		for(int i=0;i<returntest.length;i++) {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			TestResponseModel testResponseModel = modelMapper.map(returntest[i], TestResponseModel.class);
			User2test user2Test=user2TestRepo.findstatusByUserIdandTestId(email,testResponseModel.getTest_id());
			if(user2Test != null) {
				String status=user2Test.getStatus();
				testResponseModel.setStatus(status);
			}
			if(!userEntity.getDiscipline().equals(testResponseModel.getExam().getBranch())) {
				return null;
			}
			returntestresponse[i]=testResponseModel;
		}
		return returntestresponse;
	}

	@Override
	public ExamReference[] getExamReference() {
		return examref.findAllExam();
	}

	@Override
	public void createTest(Test test) throws TestExistException {
		 Optional<Test> testOptional =testRepository.findById(test.getTest_id());
		 if(testOptional.isPresent()) throw new TestExistException("Test Id is already Exist ");
		testRepository.save(test);
	}
}
