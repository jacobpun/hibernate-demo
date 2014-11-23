package org.punnoose.hibernate.service;

import java.util.List;

import org.hibernate.Query;
import org.punnoose.hibernate.dao.CompanyDao;
import org.punnoose.hibernate.dao.UserDao;
import org.punnoose.hibernate.dao.VehicleDao;
import org.punnoose.hibernate.model.BankAccount;
import org.punnoose.hibernate.model.Company;
import org.punnoose.hibernate.model.User;
import org.punnoose.hibernate.model.UserNetWorth;
import org.punnoose.hibernate.model.reporting.CountrySummary;
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

	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	
	@Transactional(readOnly = true)
	public User getById(long userId) {
		return userDao.getById(userId);
	}

	@Transactional
	public void delete(User user) {
		User spouse = user.getSpouse();
		if(spouse != null){
			spouse.setSpouse(null);
			userDao.update(spouse);
		}
		userDao.delete(user);
	}

	@Transactional(readOnly = true)
	public List<User> findByVehicleType(String vehicleType, int maxResults) {
		return userDao.findByVehicleType(vehicleType, maxResults);
	}

	@Transactional(readOnly = true)
	public List<User> findByEmployer(Company company) {
		return userDao.findByEmployer(company);
	}

	@Transactional(readOnly=true)
	public long getUsersCountInCountry(String country){
		return userDao.getUsersCountInCountry(country);
	}
	
	@Transactional(readOnly=true)
	public List<CountrySummary> getUsersCountByCountry(){
		return userDao.getUsersCountByCountry();
	}

	@Transactional(readOnly=true)
	public UserNetWorth getNetWorth(Long id) {
		return userDao.getNetWorth(id);
	}
	
	@Transactional(readOnly=true)
	public List<BankAccount> getBankAccounts(Long userId, String type){
		return userDao.getBankAccounts(userId, type);
	}
	
}