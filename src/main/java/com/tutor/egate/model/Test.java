package com.tutor.egate.model;

import java.io.Serializable;
import java.sql.Date;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "test")
public class Test implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3436614554505654681L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	private String test_Title;
	private String test_subtitle;
	private String total_marks;
	private String total_questions;
	private String duration;
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "test_id")
//	private List<User2test> user2test=new ArrayList<>();
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

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "exam_ref")
	private ExamReference exam;
	@Id
	@NotNull
	private String test_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getTest_id() {
		return test_id;
	}

	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}
}
