package org.punnoose.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column (name ="STREET_NAME")
	private String streetName;
	@Column (name ="STATE")
	private String state;
	@Column (name ="COUNTRY")
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