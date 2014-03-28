package com.zsg.dcvps.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsg.dcvps.entity.User;

@Repository("userDao")
public class UserDao extends GenericDao<User> {
	
	@Autowired
	public UserDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
