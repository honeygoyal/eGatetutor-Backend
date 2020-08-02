package com.tutor.egate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exam")
public class Question implements Serializable {

    @AttributeOverrides(value =
            {@AttributeOverride(column = @Column(name="test_id"), name = "test_id"),@AttributeOverride(column = @Column(name="section"), name = "section"),
                    @AttributeOverride(column = @Column(name="questionLabel"), name = "questionLabel")})
    @EmbeddedId
    private QuestionCompositeKey pk;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String exam;

    @MapsId("test_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="test_id",referencedColumnName = "test_id",insertable=false, updatable=false)
    private Test test_id;

    @Column(nullable=false, insertable=false, updatable=false)
    private String section;

    private String marks;

    private String negativeMarks;

    private String type;

    private String question;

    private String answer;

    private String solution;

    private String questionDifficulty;

    private String videoLink;

    @Column(nullable=false, insertable=false, updatable=false)
    private int questionLabel;

    public int getQuestionLabel() {
        return questionLabel;
    }

    public void setQuestionLabel(int questionLabel) {
        this.questionLabel = questionLabel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public Test getTest_id() {
        return test_id;
    }

    public void setTest_id(Test test_id) {
        this.test_id = test_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getNegativeMarks() {
        return negativeMarks;
    }

    public void setNegativeMarks(String negativeMarks) {
        this.negativeMarks = negativeMarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }



    public String getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setQuestionDifficulty(String questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public void setPk(QuestionCompositeKey pk) {
        this.pk = pk;
    }

    public QuestionCompositeKey getPk() {
        return pk;
    }
}
