package org.punnoose.hibernate.main;

import static org.punnoose.hibernate.model.builder.UserBuilder.aUser;

import java.util.GregorianCalendar;

import org.punnoose.hibernate.model.Company;
import org.punnoose.hibernate.model.RegistrationDetails;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.model.Vehicle;
import org.punnoose.hibernate.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"context.xml");
		UserService userSvc = (UserService) ctx.getBean("userService");

		//create vehicles
		Vehicle v1 = new Vehicle("Toyota", "Rav4", new RegistrationDetails("NJ", "001"));
		Vehicle v2 = new Vehicle("Hyundai", "Accent", new RegistrationDetails("NJ", "002"));
		
		//create companies
		Company company1 =new Company("Company1");
		Company company2 =new Company("Company2");
		
		// Create user1
		User user = aUser("User 1")
				.havingAddress("Street 1", "NJ", "US")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						company1, "Title 1","Some description")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						company2, "Title 2", "Some description")
				.withVehicles(v1)
				.withVehicles(v2)
				.build();

		// Create user2
		User user2 = aUser("User 2")
				.havingAddress("Street 1", "NJ", "US")
				.build();
		user.setSpouse(user2);
		
		userSvc.save(user);

	}
}