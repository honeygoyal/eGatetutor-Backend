package com.tutor.egate.shared;

import java.io.Serializable;

public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3054925332733680250L;
	
	private String name;
	private String email;
	private String confirmemail;
	private String address;
	private String checkterm;
	private String university;
	private String password;
	private String discipline;
	private String mobilenumber;
	private String qualification;
	private String targetyear;
	private byte[] photo;
	private byte[] signature;
	private byte[] idproof;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
