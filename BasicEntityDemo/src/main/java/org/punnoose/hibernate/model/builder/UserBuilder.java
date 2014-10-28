package org.punnoose.hibernate.model.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.punnoose.hibernate.model.Address;
import org.punnoose.hibernate.model.BankAccount;
import org.punnoose.hibernate.model.Company;
import org.punnoose.hibernate.model.ProfessionalExperience;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.model.Vehicle;

public class UserBuilder {
	private String name;
	private Address address;
	private Collection<ProfessionalExperience> professionalExperiences = new ArrayList<>();
	private Collection<Vehicle> vehicles = new ArrayList<>();
	private Collection<BankAccount> bankAccounts = new ArrayList<>();
	

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
	
	public UserBuilder withProfessionalExperience(Date from, Date to, Company company, String title, String jobDescription){
		ProfessionalExperience experience = new ProfessionalExperience();
		experience.setCompany(company);
		experience.setFrom(from);
		experience.setTo(to);
		experience.setTitle(title);
		experience.setJobDescription(jobDescription);
		professionalExperiences.add(experience);
		return this;
	}
	
	public User build(){
		User user = new User();
		user.setName(name);
		user.setProfessionalExperiences(professionalExperiences);
		user.setAddress(address);
		user.setVehicles(vehicles);
		user.setBankAccounts(bankAccounts);
		return user;
	}

	public UserBuilder withVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
		return this;
	}

	public UserBuilder withBankAccount(BankAccount acct) {
		bankAccounts.add(acct);
		return this;
	}
}