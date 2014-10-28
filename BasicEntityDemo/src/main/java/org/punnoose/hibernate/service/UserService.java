package org.punnoose.hibernate.service;

import org.punnoose.hibernate.dao.CompanyDao;
import org.punnoose.hibernate.dao.UserDao;
import org.punnoose.hibernate.dao.VehicleDao;
import org.punnoose.hibernate.model.ProfessionalExperience;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VehicleDao vehicleDao;

	@Autowired
	private CompanyDao companyDao;

	public UserDao getDao() {
		return userDao;
	}

	public void setDao(UserDao dao) {
		this.userDao = dao;
	}

	@Transactional
	public void save(User user) {
		userDao.save(user);
	}

	@Transactional(readOnly = true)
	public User getById(int userId) {
		return userDao.getById(userId);
	}

	@Transactional
	public void delete(User user) {
		User spouse = user.getSpouse();
		spouse.setSpouse(null);	
		userDao.update(spouse);
		
		userDao.delete(user);
	}
}