package org.punnoose.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericeDao<T, K extends Serializable> {
	@Autowired
	private SessionFactory factory;
	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericeDao() {
		super();
		entityClass = ((Class<T>) ((ParameterizedType) (getClass()
				.getGenericSuperclass())).getActualTypeArguments()[0]);
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public void save(T entity) {
		factory.getCurrentSession().persist(entity);
	}

	public T getById(K entityId) {
		return (T) factory.getCurrentSession().get(entityClass, entityId);
	}
	
	public void delete(T entity){
		factory.getCurrentSession().delete(entity);
	}

	public void update(T entity){
		factory.getCurrentSession().update(entity);
	}
}