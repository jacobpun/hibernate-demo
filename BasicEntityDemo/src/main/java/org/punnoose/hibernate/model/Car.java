package org.punnoose.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Car extends Vehicle{

	private int passangersCount;
	
	public Car(String make, String model, RegistrationDetails regnDetails, int passangersCount) {
		super(make, model, regnDetails);
		this.passangersCount = passangersCount;
	}

	public int getPassangersCount() {
		return passangersCount;
	}

	public void setPassangersCount(int passangersCount) {
		this.passangersCount = passangersCount;
	}
}