package org.punnoose.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.punnoose.hibernate.model.User;
import org.springframework.stereotype.Repository;
import org.punnoose.hibernate.model.Company;

@Repository
public class UserDao  extends GenericeDao<User, Integer>{	
	public List<User> findByVehicleType(String vehicleType, int maxResults){
		Query query = factory.getCurrentSession().getNamedQuery("User.findByVehicleType").setString("vehicleType", vehicleType);
		query.setMaxResults(maxResults);
		return query.list();
	}

	public List<User> findByEmployer(Company company){
		Query query = factory.getCurrentSession().getNamedQuery("User.findByEmployer").setEntity("company", company);
		return query.list();
	}
}