package org.punnoose.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "USER_NAME", length = 64)
	private String name;

	@Embedded
	private Address address;

	@ElementCollection
	private Collection<ProfessionalExperience> professionalExperiences = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "["
				+ "ID:" + getId()
				+ ", Name:" + getName() 
				+ ", Address:" + address 
				+ ", Professional Experiences: " + professionalExperiences 
				+ "]";
	}

	public Collection<ProfessionalExperience> getProfessionalExperiences() {
		return professionalExperiences;
	}

	public void setProfessionalExperiences(
			Collection<ProfessionalExperience> professionalExperiences) {
		this.professionalExperiences = professionalExperiences;
	}
}