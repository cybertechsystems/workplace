package com.email.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class AbstractEntityManager {
	private static final String PERSISTENCE_UNIT_NAME = "MysqlCIDDS";
	
	@PersistenceUnit
	private EntityManagerFactory factory;
	
	protected EntityManager getEntityManager() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		return em;
	}
	
}
