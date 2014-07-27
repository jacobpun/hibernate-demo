package org.punnoose.hibernate.main;

import static org.punnoose.hibernate.model.builder.AddressBuilder.anAddress;

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
		User user = new User();
		user.setName("User 1");
		Address address = anAddress()
							.withStreetName("Street 1")
							.withState("NJ")
							.withCountry("US")
							.build();
		user.setAddress(address);
		
		UserService svc = (UserService) ctx.getBean("userService");
		svc.save(user);

		// Read User
		user = null;
		user = svc.getById(1);
		System.out.println("Got User: " + user);

	}
}