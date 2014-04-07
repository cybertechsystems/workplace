package com.ctech.admin;

import javax.jws.WebService;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@WebService
public class DAO extends SqlMapClientDaoSupport {
	private DataSourceTransactionManager transactionManager;

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
