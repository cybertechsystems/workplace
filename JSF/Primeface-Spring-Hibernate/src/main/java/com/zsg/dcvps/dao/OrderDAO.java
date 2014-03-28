package com.zsg.dcvps.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsg.dcvps.entity.Orders;


@Repository("orderDao")
public class OrderDAO extends GenericDao<Orders>  {

	@Autowired
	public OrderDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
