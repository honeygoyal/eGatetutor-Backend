package com.tutor.egate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_answer")
public class UserAnswer implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String status;
    private String answerSubmitted;
    private String timetaken;
    @AttributeOverrides(value =
            {@AttributeOverride(column = @Column(name="question_id"), name = "question_id"),@AttributeOverride(column = @Column(name="email"), name = "email"),
                    @AttributeOverride(column = @Column(name="test_id"), name = "test_id")})
    @EmbeddedId
    private UserAnswerCompositeKey pk;

    @Column(nullable=false, insertable=false, updatable=false)
    private Long question_id;
    @Column(nullable=false, insertable=false, updatable=false)
    private String email;
    @Column(nullable=false, insertable=false, updatable=false)
    private String test_id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswerSubmitted() {
        return answerSubmitted;
    }

    public void setAnswerSubmitted(String answerSubmitted) {
        this.answerSubmitted = answerSubmitted;
    }

    public String getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(String timetaken) {
        this.timetaken = timetaken;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public UserAnswerCompositeKey getPk() {
        return pk;
    }

    public void setPk(UserAnswerCompositeKey pk) {
        this.pk = pk;
    }
}
