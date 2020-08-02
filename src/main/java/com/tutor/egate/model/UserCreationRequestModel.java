package com.tutor.egate.model;

import javax.validation.constraints.NotNull;

public class UserCreationRequestModel {

	@NotNull(message = "Name can not be null")
	private String name;

	@NotNull(message = "Email can not be null")
	private String email;

	@NotNull(message = "Confirm Email can not be null")
	private String confirmemail;

	@NotNull(message = "Address can not be null")
	private String address;

	private String checkterm;

	private String university;

	@NotNull(message = "University can not be null")
	private String discipline;

	@NotNull(message = "Mobile Number can not be null")
	private String mobilenumber;

	@NotNull(message = "Qualification can not be null")
	private String qualification;

	@NotNull(message = "Target Year can not be null")
	private String targetyear;

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
