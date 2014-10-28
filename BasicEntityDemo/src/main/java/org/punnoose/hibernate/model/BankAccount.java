package org.punnoose.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BankAccount {
	@Id
	@Column(name="APP_ID")
	private long accountId;
	@Column(name="BANK_NAME")
	private String bankName;

	public BankAccount() {
		super();
	}

	public BankAccount(long accountId, String bankName) {
		super();
		this.accountId = accountId;
		this.bankName = bankName;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
}