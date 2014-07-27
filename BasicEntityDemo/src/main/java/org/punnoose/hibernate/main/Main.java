package org.punnoose.hibernate.main;

import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		
		//Create user
		User user = new User();
		user.setName("User 1");
		UserService svc = (UserService) ctx.getBean("userService");
		svc.save(user);
		
		//Read User
		user = null;
		user = svc.getById(1);
		System.out.println("Got User: " + user);
		
	}
}