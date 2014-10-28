package org.punnoose.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("T")
public class Truck extends Vehicle {

	private long capacityInLbs;
	
	public Truck(String make, String model, RegistrationDetails regnDetails, long capacityInLbs) {
		super(make, model, regnDetails);
		this.capacityInLbs=capacityInLbs;
	}

	public long getCapacityInLbs() {
		return capacityInLbs;
	}

	public void setCapacityInLbs(long capacityInLbs) {
		this.capacityInLbs = capacityInLbs;
	}
}