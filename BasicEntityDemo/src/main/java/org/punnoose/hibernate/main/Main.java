package org.punnoose.hibernate.main;

import static org.punnoose.hibernate.model.builder.UserBuilder.aUser;

import java.util.GregorianCalendar;

import org.punnoose.hibernate.cachestatistics.CacheStatistics;
import org.punnoose.hibernate.model.BankAccount;
import org.punnoose.hibernate.model.Car;
import org.punnoose.hibernate.model.Company;
import org.punnoose.hibernate.model.LoanAccount;
import org.punnoose.hibernate.model.RegistrationDetails;
import org.punnoose.hibernate.model.SavingsAccount;
import org.punnoose.hibernate.model.Truck;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.model.Vehicle;
import org.punnoose.hibernate.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"context.xml");
		
		UserService userSvc = (UserService) ctx.getBean("userService");

		//create vehicles
		Vehicle v1 = new Car("Toyota", "Rav4", new RegistrationDetails("NJ", "001"),4);
		Vehicle v2 = new Vehicle("Hyundai", "Accent", new RegistrationDetails("NJ", "002"));
		Vehicle v3 = new Truck ("Volvo", "Volvo", new RegistrationDetails("NY", "001"), 10000);
		
		//create companies
		Company company1 =new Company("Company1");
		Company company2 =new Company("Company2");
		
		//create bank accounts
		BankAccount acct1 = new SavingsAccount(001, "BOA", 10000l, 0.01d);
		BankAccount acct2 = new LoanAccount(002, "BOA", 2000l, 0.01d);
		
		// Create user1
		User user = aUser("VERY LOMG NAME VER User 1")
				.havingAddress("Street 1", "NJ", "US")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						company1, "Title 1","Some description")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						company2, "Title 2", "Some description")
				.withVehicle(v1)
				.withVehicle(v2)
				.withVehicle(v3)
				.build();

		// Create user2
		User user2 = aUser("User 2")
				.havingAddress("Street 1", "NJ", "US")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						company1, "Title 1","Some description")
				.build();

		userSvc.save(user);

		User user_r1 = userSvc.getById(1);
		//User user_r2 = userSvc.getById(user.getId());
		
		user_r1.setName("Name_R1");
		//user_r2.setName("Name_R2");
		
		userSvc.update(user_r1);
		//userSvc.update(user_r2); -> Will throw exception
		
		//print the cache statistics
		CacheStatistics.printCacheStatistics("user");
		
		//close application context
		((ConfigurableApplicationContext)ctx).close();
	}
}