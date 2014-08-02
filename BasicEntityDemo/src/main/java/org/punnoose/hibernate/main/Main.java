package org.punnoose.hibernate.main;

import static org.punnoose.hibernate.model.builder.UserBuilder.aUser;

import java.util.GregorianCalendar;

import org.punnoose.hibernate.model.Address;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"context.xml");

		// Create user
		User user = aUser("User 1")
				.havingAddress("Street 1", "NJ", "US")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						"Company1", "Title 1")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						"Company2", "Title 2")
				.build();

		UserService svc = (UserService) ctx.getBean("userService");
		svc.save(user);

		// Read User
		user = null;
		user = svc.getById(1);
		System.out.println("Got User: " + user);
	}
}