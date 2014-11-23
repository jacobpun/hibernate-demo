package org.punnoose.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.punnoose.hibernate.model.BankAccount;
import org.punnoose.hibernate.model.Company;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.model.UserNetWorth;
import org.punnoose.hibernate.model.reporting.CountrySummary;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao  extends GenericeDao<User, Long>{	
	public List<User> findByVehicleType(String vehicleType, int maxResults){
		Query query = factory.getCurrentSession().getNamedQuery("User.findByVehicleType").setString("vehicleType", vehicleType);
		query.setMaxResults(maxResults);
		return query.list();
	}

	public List<User> findByEmployer(Company company){
		Query query = factory.getCurrentSession().getNamedQuery("User.findByEmployer").setEntity("company", company);
		return query.list();
	}
	
	public long getUsersCountInCountry(String country){
		Query query = factory.getCurrentSession().getNamedQuery("User.usersCountInCountry").setString("country", country);
		return (long) query.uniqueResult();
	}
	
	public List<CountrySummary> getUsersCountByCountry(){
		Query query = factory.getCurrentSession().getNamedQuery("User.countByCountry");
		return query.list();
	}

	public UserNetWorth getNetWorth(Long id) {
		Query query = factory.getCurrentSession().createQuery("from UserNetWorth nw where nw.userId =:userId");
		query.setLong("userId", id);
		return (UserNetWorth) query.uniqueResult();
	}

	public List<BankAccount> getBankAccounts(Long userId, String type){
		Session session = factory.getCurrentSession();
		User user = getById(userId);
		Query query = session.createFilter(user.getBankAccounts(), "where this.accountType=:type");
		query.setString("type", type);
		return query.list();
	}
}