package org.punnoose.hibernate.model.reporting;

public class CountrySummary {
	private String countryName;
	private long userCount;
	
	public CountrySummary(String countryName, long userCount) {
		super();
		this.countryName = countryName;
		this.userCount = userCount;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	@Override
	public String toString() {
		return "CountrySummary [countryName=" + countryName + ", userCount="
				+ userCount + "]";
	}
	
	
}
