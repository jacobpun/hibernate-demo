package org.punnoose.hibernate.main;

import static org.punnoose.hibernate.model.builder.UserBuilder.aUser;

import java.util.GregorianCalendar;
import java.util.List;

import org.punnoose.hibernate.cachestatistics.CacheStatistics;
import org.punnoose.hibernate.model.BankAccount;
import org.punnoose.hibernate.model.Car;
import org.punnoose.hibernate.model.Company;
import org.punnoose.hibernate.model.LoanAccount;
import org.punnoose.hibernate.model.RegistrationDetails;
import org.punnoose.hibernate.model.SavingsAccount;
import org.punnoose.hibernate.model.Truck;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.model.*;
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
		BankAccount acct2 = new LoanAccount(002, "BOA", 10000l, 0.01d);
		
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
				.withVehicle(v1)
				.withVehicle(v2)
				.withVehicle(v3)
				.withBankAccount(acct1)
				.withBankAccount(acct2)
				.build();

		// Create user2
		User user2 = aUser("User 2")
				.havingAddress("Street 1", "NJ", "US")
				.withProfessionalExperience(
						new GregorianCalendar(2000, 01, 01).getTime(),
						new GregorianCalendar(2000, 01, 01).getTime(),
						company1, "Title 1","Some description")
				.build();
		user.setSpouse(user2);
		
		userSvc.save(user);

		userSvc.getById(1);
		userSvc.getById(1);

		List<User> usersHavingCar = userSvc.findByVehicleType("C", 10);
		for (User tmpUser : usersHavingCar) {
			System.out.println("User Having Car: " + tmpUser.getName());
			System.out.println("Vehicles: ");
			for (Vehicle tmpVehicle : tmpUser.getVehicles()) {
				System.out.println("Vehicle: " + tmpVehicle.getMake() + " " + tmpVehicle.getModel());
			}
		}

		
		List<User> usersWorkedForCompany1 = userSvc.findByEmployer(company1);
		for (User tmpUser : usersWorkedForCompany1) {
			System.out.println("User Woked for Company 1: " + tmpUser.getName());
			System.out.println("Employers: ");
			for (ProfessionalExperience exp : tmpUser.getProfessionalExperiences()) {
				System.out.println("Company: " + exp.getCompany().getCompanyName());
			}
		}
		
		//print the cache statistics
		CacheStatistics.printCacheStatistics("user");
		
		//close application context
		((ConfigurableApplicationContext)ctx).close();
	}
}