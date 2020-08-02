package com.tutor.egate.model;

public class UserAnswerRequestModel {
    private String test_id;
    private String email;
    private String time_left;

    private AnswerModel[] answerModel;

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime_left() {
        return time_left;
    }

    public void setTime_left(String time_left) {
        this.time_left = time_left;
    }

    public AnswerModel[] getAnswerModel() {
        return answerModel;
    }

    public void setAnswerModel(AnswerModel[] answerModel) {
        this.answerModel = answerModel;
    }
}
