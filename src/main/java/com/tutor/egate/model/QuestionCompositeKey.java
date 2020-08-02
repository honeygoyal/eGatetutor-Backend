package com.tutor.egate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuestionCompositeKey implements Serializable {
    @Column(name = "section")
    private String section;
    @Column(name = "questionLabel")
    private int questionLabel;
    @Column(name = "test_id")
    private String test_id;

    public QuestionCompositeKey() {
    }

    public QuestionCompositeKey(String section, int questionLabel, String test_id) {
        this.section = section;
        this.questionLabel = questionLabel;
        this.test_id = test_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionCompositeKey)) return false;
        QuestionCompositeKey that = (QuestionCompositeKey) o;
        return questionLabel == that.questionLabel &&
                section.equals(that.section) &&
                test_id.equals(that.test_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, questionLabel, test_id);
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getQuestionLabel() {
        return questionLabel;
    }

    public void setQuestionLabel(int questionLabel) {
        this.questionLabel = questionLabel;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

}
