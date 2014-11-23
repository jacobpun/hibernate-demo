package org.punnoose.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name ="VEHICLE_TYPE",
		discriminatorType =DiscriminatorType.STRING
)
@DiscriminatorValue("V")
public class Vehicle {
	
	@EmbeddedId
	private RegistrationDetails regnDetails;	
	
	@Column(name="MAKE")
	private String make;
	
	@Column(name="MODEL")
	private String model;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="USER_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private User owner;
	
	public Vehicle(){}
	
	public Vehicle(String make, String model, RegistrationDetails regnDetails) {
		this.make = make;
		this.model = model;
		this.regnDetails = regnDetails;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public RegistrationDetails getRegnDetails() {
		return regnDetails;
	}
	public void setRegnDetails(RegistrationDetails regnDetails) {
		this.regnDetails = regnDetails;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}