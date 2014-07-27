package org.punnoose.hibernate.service;

import org.punnoose.hibernate.dao.UserDao;
import org.punnoose.hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void save(User user){
		dao.save(user);
	}
	
	@Transactional(readOnly=true)
	public User getById(int userId){
		return dao.getById(userId);
	}
}