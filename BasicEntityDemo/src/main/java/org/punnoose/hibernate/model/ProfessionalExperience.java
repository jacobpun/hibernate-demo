package org.punnoose.hibernate.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ProfessionalExperience {
	@Temporal(TemporalType.DATE)
	@Column(name="FROM_DATE")
	private Date from;
	
	@Temporal(TemporalType.DATE)
	@Column(name="TO_DATE")
	private Date to;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Company company;
	
	@Column(name="JOB_TITLE")
	private String title;

	private String jobDescription;
	
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}