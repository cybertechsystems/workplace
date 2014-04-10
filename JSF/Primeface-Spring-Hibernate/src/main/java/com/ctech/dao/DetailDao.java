package com.ctech.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ctech.entity.Details;

@Repository("detailDao")
public class DetailDao  extends GenericDao<Details> {
	@Autowired
	public DetailDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
