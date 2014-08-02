package org.punnoose.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="JOB_TITLE")
	private String title;
	
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}