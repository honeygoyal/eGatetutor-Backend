package com.tutor.egate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6710298083579270857L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	private String name;
	private String email;
	private String confirmemail;
	private String address;
	private String checkterm;
	private String university;
	private String discipline;
	private String mobilenumber;
	private String qualification;
	private String targetyear;
	private byte[] photo;
	private byte[] signature;
	private byte[] idproof;
	
	
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "User2Exam", 
	             joinColumns = { @JoinColumn(name = "user_id") }, 
	             inverseJoinColumns = { @JoinColumn(name = "exam_id") })
	private List<ExamReference> examref =new ArrayList<ExamReference>();

	private String password;

	
	
	public List<ExamReference> getExamref() {
		return examref;
	}

	public void setExamref(List<ExamReference> examref) {
		this.examref = examref;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public byte[] getIdproof() {
		return idproof;
	}

	public void setIdproof(byte[] idproof) {
		this.idproof = idproof;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmemail() {
		return confirmemail;
	}

	public void setConfirmemail(String confirmemail) {
		this.confirmemail = confirmemail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCheckTerm() {
		return checkterm;
	}

	public void setCheckTerm(String checkTerm) {
		this.checkterm = checkTerm;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getTargetyear() {
		return targetyear;
	}

	public void setTargetyear(String targetyear) {
		this.targetyear = targetyear;
	}

}
