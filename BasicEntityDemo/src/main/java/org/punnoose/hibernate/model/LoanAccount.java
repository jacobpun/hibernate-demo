package org.punnoose.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name="LOAN_ACCOUNT")
@DiscriminatorValue("L")
public class LoanAccount extends BankAccount {
	
	@Column(name="LOAN_AMOUNT")
	private long loanAmount;
	
	@Column(name="INTEREST_RATE")
	private double interestRate;
	
	private String accountType="L";
	
	public LoanAccount() {
		super();
	}

	public LoanAccount(long accountId, String bankName, long loanAmount,
			double interestRate) {
		super(accountId, bankName);
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
	}

	public long getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
}