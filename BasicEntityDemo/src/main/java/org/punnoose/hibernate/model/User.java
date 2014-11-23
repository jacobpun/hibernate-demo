package org.punnoose.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.punnoose.hibernate.model.converter.SchoolToStringConverter;

@Entity
@Table(name = "USER_DETAILS")
@SecondaryTable(name = "USER_ADDRESS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "USER_ID"))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user")
@NamedQueries({
		@NamedQuery(name = "User.findByVehicleType", query = "select distinct u from User u inner join fetch u.vehicles v where u.id in (select iu.id from User iu inner join iu.vehicles iv where iv.class=:vehicleType)"),
		@NamedQuery(name = "User.findByEmployer", query = "select distinct u from User u inner join fetch u.professionalExperiences e where u.id in (select iu.id from User iu inner join iu.professionalExperiences ie where ie.company=:company)"),
		@NamedQuery(name = "User.usersCountInCountry", query = "select count(u) from User u where u.address.country=:country"),
		@NamedQuery(name = "User.countByCountry", query = "select new org.punnoose.hibernate.model.reporting.CountrySummary(u.address.country, count(u)) from User u group by u.address.country") })
@DynamicInsert
@DynamicUpdate
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USER_NAME", nullable = false, length = 64)
	@Size(min=5, max=64) 
	private String name;

	@Column(name = "ACTIVE")
	@Type(type = "yes_no")
	private boolean active = true;

	@Column(name = "TS", insertable = false)
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	private Date timeStamp;

	@Column(name ="SCHOOL_NAME", length=64)
	@Convert(converter = SchoolToStringConverter.class, disableConversion = false)
	@Transient
	private School school;
	
	@Version
	private long versionNumber;
	
	private User() {
	}

	public User(String name) {
		this.name = name;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "streetName", column = @Column(name = "STREET", table = "USER_ADDRESS")),
			@AttributeOverride(name = "state", column = @Column(name = "STATE", table = "USER_ADDRESS")),
			@AttributeOverride(name = "country", column = @Column(name = "COUNTRY", table = "USER_ADDRESS")) })
	private Address address;

	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_EXPERIENCE", joinColumns = @JoinColumn(name = "USER_ID"))
	@GenericGenerator(name = "hilo-generator", strategy = "hilo")
	@CollectionId(columns = { @Column(name = "EXPERIENCE_ID") }, generator = "hilo-generator", type = @Type(type = "long"))
	@OrderBy("to DESC, from DESC")
	private Collection<ProfessionalExperience> professionalExperiences = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "SPOUSE_ID")
	private User spouse;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	@org.hibernate.annotations.IndexColumn(
			name="POSITION", base = 1
	)
	@org.hibernate.annotations.LazyCollection(
			org.hibernate.annotations.LazyCollectionOption.EXTRA
	)
	private List<Vehicle> vehicles = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinTable(name = "USER_BANK_ACCT", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ACCT_ID") })
	private Collection<BankAccount> bankAccounts = new ArrayList<>();

	public Long getId() {
		return id;
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
		if (spouse != null)
			spouse.spouse = this;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
		for (Vehicle vehicle : vehicles) {
			vehicle.setOwner(this);
		}
	}

	public Collection<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Collection<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public long getVersionNumber() {
		return versionNumber;
	}
}