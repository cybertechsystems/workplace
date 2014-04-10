package com.ctech.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ctech.entity.Address;

@Repository("addressDao")
public class AddressDao  extends GenericDao<Address> {
	
	@Autowired
	public AddressDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
