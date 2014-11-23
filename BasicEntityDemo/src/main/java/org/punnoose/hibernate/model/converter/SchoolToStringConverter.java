package org.punnoose.hibernate.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.punnoose.hibernate.model.School;

@Converter(autoApply = true)
public class SchoolToStringConverter implements
		AttributeConverter<School, String> {

	@Override
	public String convertToDatabaseColumn(School school) {
		System.out.println("****" + school.toString());
		return school.toString();
	}

	@Override
	public School convertToEntityAttribute(String schoolString) {
		return School.fromString(schoolString);
	}
}