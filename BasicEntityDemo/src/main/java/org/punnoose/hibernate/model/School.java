package org.punnoose.hibernate.model;

import java.io.Serializable;


public class School{
	private static final long serialVersionUID = 1L;
	private String name;
	private long startYear;

	public School(){}
	
	public School(String name, int startYear) {
		this.name=name;
		this.startYear=startYear;
	}

	public String getName() {
		return name;
	}


	public long getStartYear() {
		return startYear;
	}


	@Override
	public String toString() {
		return name + "-" + startYear;
	}

	public static School fromString(String schoolString) {
		School school = new School();
		String []  schoolData = schoolString.split("-");
		school.name = schoolData[0];
		school.startYear = Integer.parseInt(schoolData[1]);
		return school;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (startYear ^ (startYear >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startYear != other.startYear)
			return false;
		return true;
	}
}