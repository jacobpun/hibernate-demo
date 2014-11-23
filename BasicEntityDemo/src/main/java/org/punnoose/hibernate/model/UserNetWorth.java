package org.punnoose.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Immutable
@Subselect(value = "select u.USER_ID as USERID, (sum(sv.ACCOUNT_BALANCE) - sum(ln.LOAN_AMOUNT)) AS NETWORTH from USER_DETAILS u "
		+ "left outer join USER_BANK_ACCT uba on u.USER_ID = uba.USER_ID "
		+ "left outer join SAVINGS_ACCOUNT sv on uba.ACCT_ID = sv.ACCT_ID "
		+ "left outer join LOAN_ACCOUNT ln on uba.ACCT_ID = ln.ACCT_ID "
		+ "group by u.USER_ID")
@Synchronize({"SavingsAccount", "LoanAccount", "BankAccount"})
public class UserNetWorth {
	@Id
	private Long userId;
	private double netWorth;
	
	public UserNetWorth() {
	}
	
	public double getNetWorth() {
		return netWorth;
	}

	@Override
	public String toString() {
		return "UserNetWorth [userId=" + userId + ", netWorth=" + netWorth + "]";
	}
}