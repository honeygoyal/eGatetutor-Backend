package com.tutor.egate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAnswerCompositeKey implements Serializable {
    @Column(name = "question_id")
    private Long question_id;
    @Column(name = "email")
    private String email;
    @Column(name = "test_id")
    private String test_id;

    public UserAnswerCompositeKey(Long question_id, String email, String test_id) {
        this.question_id = question_id;
        this.email = email;
        this.test_id = test_id;
    }

    public UserAnswerCompositeKey() {
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAnswerCompositeKey)) return false;
        UserAnswerCompositeKey that = (UserAnswerCompositeKey) o;
        return getQuestion_id().equals(that.getQuestion_id()) &&
                getEmail().equals(that.getEmail()) &&
                getTest_id().equals(that.getTest_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion_id(), getEmail(), getTest_id());
    }
}
