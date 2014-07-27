package org.punnoose.hibernate.dao;

import org.hibernate.SessionFactory;
import org.punnoose.hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public void save(User user){
		factory.getCurrentSession().save(user);
		System.out.println("Saved" + user);
	}
	
	public User getById(int userId){
		return (User) factory.getCurrentSession().get(User.class, userId);
	}
}