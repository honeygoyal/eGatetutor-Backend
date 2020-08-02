package com.tutor.egate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_reference")
public class ExamReference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3230408103166494796L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public long id;
	private String exam_name;
	private String exam_name_service;
	private String price;
	private String branch;
	private String exam_code;

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}

	public String getExam_name_service() {
		return exam_name_service;
	}

	public void setExam_name_service(String exam_name_service) {
		this.exam_name_service = exam_name_service;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getExam_code() {
		return exam_code;
	}

	public void setExam_code(String exam_code) {
		this.exam_code = exam_code;
	}



}
