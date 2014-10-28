package org.punnoose.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="SAVINGS_ACCOUNT")
public class SavingsAccount extends BankAccount {
	@Column(name="ACCOUNT_BALANCE")
	private long balanceAmount;
	
	@Column(name="INTEREST_RATE")
	private double interestRate;
	
	public SavingsAccount() {
		super();
	}

	public SavingsAccount(long accountId, String bankName, long balanceAmount,
			double interestRate) {
		super(accountId, bankName);
		this.balanceAmount = balanceAmount;
		this.interestRate = interestRate;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public long getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}