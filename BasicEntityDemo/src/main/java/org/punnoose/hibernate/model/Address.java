package org.punnoose.hibernate.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String streetName;
	private String state;
	private String country;
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "[" + getStreetName() + ", " + getState() + ", " + getCountry() + "]";
	}
}