package org.punnoose.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER_DETAILS")
@SecondaryTable(name = "USER_ADDRESS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "USER_ID"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "USER_NAME", length = 64)
	@Basic(optional = false)
	private String name;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "streetName", column = @Column(name = "STREET", table = "USER_ADDRESS")),
			@AttributeOverride(name = "state", column = @Column(name = "STATE", table = "USER_ADDRESS")),
			@AttributeOverride(name = "country", column = @Column(name = "COUNTRY", table = "USER_ADDRESS")) })
	private Address address;

	@ElementCollection(fetch=FetchType.LAZY)
	@JoinTable(name = "USER_EXPERIENCE", joinColumns = @JoinColumn(name = "USER_ID"))
	@GenericGenerator(name = "hilo-generator", strategy = "hilo")
	@CollectionId(columns = { @Column(name = "EXPERIENCE_ID") }, generator = "hilo-generator", type = @Type(type = "long"))
	private Collection<ProfessionalExperience> professionalExperiences = new ArrayList<>();

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="SPOUSE_ID")
	private User spouse;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.PERSIST)
	private Collection<Vehicle> vehicles = new ArrayList<>();
	
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
		return "[" + "ID:" + getId() + ", Name:" + getName() + ", Address:"
				+ address + ", Professional Experiences: "
				+ professionalExperiences + "]";
	}

	public Collection<ProfessionalExperience> getProfessionalExperiences() {
		return professionalExperiences;
	}

	public void setProfessionalExperiences(
			Collection<ProfessionalExperience> professionalExperiences) {
		this.professionalExperiences = professionalExperiences;
	}

	public User getSpouse() {
		return spouse;
	}

	public void setSpouse(User spouse) {
		this.spouse = spouse;
		spouse.spouse=this;
	}

	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
		for (Vehicle vehicle : vehicles) {
			vehicle.setOwner(this);
		}
	}
}