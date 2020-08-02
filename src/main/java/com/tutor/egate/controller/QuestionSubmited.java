package com.tutor.egate.controller;

import com.tutor.egate.model.*;
import com.tutor.egate.repo.User2TestRepository;
import com.tutor.egate.repo.UserAnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionSubmited {

    @Autowired
    UserAnswerRepo userAnswerRepo;
    @Autowired
    User2TestRepository user2TestRepository;
    @PostMapping("/submitanswer")
    public String saveAnswerData(@RequestBody UserAnswerRequestModel userAnswerRequestModel){
        for(int i=0;i<userAnswerRequestModel.getAnswerModel().length;i++){
            UserAnswer userAnswer=new UserAnswer() ;
            userAnswer.setEmail(userAnswerRequestModel.getEmail());
            userAnswer.setTest_id(userAnswerRequestModel.getTest_id());
            userAnswer.setAnswerSubmitted(userAnswerRequestModel.getAnswerModel()[i].getAnswerSubmitted());
            userAnswer.setStatus(userAnswerRequestModel.getAnswerModel()[i].getStatus());
            userAnswer.setTimetaken(userAnswerRequestModel.getAnswerModel()[i].getTimetaken());
            userAnswer.setQuestion_id(userAnswerRequestModel.getAnswerModel()[i].getQuestion_id());
            userAnswer.setPk(new UserAnswerCompositeKey(userAnswer.getQuestion_id(), userAnswer.getEmail(), userAnswer.getTest_id()));
            userAnswerRepo.save(userAnswer);
        }
        User2test user2test=new User2test();
        user2test.setTest_id(userAnswerRequestModel.getTest_id());
        user2test.setUser_id(userAnswerRequestModel.getEmail());
        user2test.setStatus("COMPLETED");
        user2test.setTest_time(userAnswerRequestModel.getTime_left());
        user2test.setPk(new User2TestCompositeKey(user2test.getUser_id(), user2test.getStatus(), user2test.getTest_id()));
        user2TestRepository.save(user2test);
        return "{}";
    }
}
