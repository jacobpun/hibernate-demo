package org.punnoose.hibernate.model.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.punnoose.hibernate.model.Address;
import org.punnoose.hibernate.model.ProfessionalExperience;
import org.punnoose.hibernate.model.User;

public class UserBuilder {
	private String name;
	private Address address;
	private Collection<ProfessionalExperience> professionalExperiences = new ArrayList<>();

	public static UserBuilder aUser(String name){
		UserBuilder builder = new UserBuilder();
		builder.name = name;
		return builder;
	}
	
	public UserBuilder havingAddress(String streetName, String state, String country){
		address = new Address();
		address.setStreetName(streetName);
		address.setState(state);
		address.setCountry(country);
		return this;
	}
	
	public UserBuilder withProfessionalExperience(Date from, Date to, String companyName, String title){
		ProfessionalExperience experience = new ProfessionalExperience();
		experience.setCompanyName(companyName);
		experience.setFrom(from);
		experience.setTo(to);
		experience.setTitle(title);
		professionalExperiences.add(experience);
		return this;
	}
	
	public User build(){
		User user = new User();
		user.setName(name);
		user.setProfessionalExperiences(professionalExperiences);
		user.setAddress(address);
		return user;
	}
}