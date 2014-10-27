package org.punnoose.hibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RegistrationDetails implements Serializable{
	private static final long serialVersionUID = -4953579429474566704L;
	private String state;
	private String regnNumber;

	public RegistrationDetails() {
		super();
	}

	public RegistrationDetails(String state, String regnNumber) {
		super();
		this.state = state;
		this.regnNumber = regnNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRegnNumber() {
		return regnNumber;
	}

	public void setRegnNumber(String regnNumber) {
		this.regnNumber = regnNumber;
	}
}
