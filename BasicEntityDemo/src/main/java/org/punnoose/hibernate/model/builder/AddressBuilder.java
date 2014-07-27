package org.punnoose.hibernate.model.builder;

import org.punnoose.hibernate.model.Address;


public class AddressBuilder{
	private String streetName;
	private String state;
	private String country; 
	
	public AddressBuilder withStreetName(String streetName){
		this.streetName = streetName;
		return this;
	}

	public AddressBuilder withCountry(String country){
		this.country = country;
		return this;
	}
	
	public AddressBuilder withState(String state){
		this.state = state;
		return this;
	}	
	
	public static AddressBuilder anAddress(){
		return new AddressBuilder();
	}
	
	public Address build(){
		Address address = new Address();
		address.setCountry(country);
		address.setState(state);
		address.setStreetName(streetName);
		return address;
	}
}