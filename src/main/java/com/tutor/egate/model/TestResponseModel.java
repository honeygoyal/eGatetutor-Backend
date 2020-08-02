package com.tutor.egate.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


public class TestResponseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1725651000451898593L;
	private String test_Title;
	private String test_subtitle;
	private String total_marks;
	private String total_questions;

	private String duration;
	private ExamReference exam;
	private String status;
	@NotNull
	public String test_id;
	private Date start_date;
	private Date end_date;

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getTest_Title() {
		return test_Title;
	}

	public void setTest_Title(String test_Title) {
		this.test_Title = test_Title;
	}

	public String getTest_subtitle() {
		return test_subtitle;
	}

	public void setTest_subtitle(String test_subtitle) {
		this.test_subtitle = test_subtitle;
	}

	public String getTotal_marks() {
		return total_marks;
	}

	public void setTotal_marks(String total_marks) {
		this.total_marks = total_marks;
	}

	public String getTotal_questions() {
		return total_questions;
	}

	public void setTotal_questions(String total_questions) {
		this.total_questions = total_questions;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public ExamReference getExam() {
		return exam;
	}

	public void setExam(ExamReference exam) {
		this.exam = exam;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTest_id() {
		return test_id;
	}

	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}

}
