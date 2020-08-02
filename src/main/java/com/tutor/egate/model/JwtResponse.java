package com.tutor.egate.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -1428603846781953145L;
	private final String jwttoken;
	private User user;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	


	public JwtResponse(String jwttoken,User user) {
		this.jwttoken = jwttoken;
		this.user=user;
	}

	public String getToken() {
		return this.jwttoken;
	}
}