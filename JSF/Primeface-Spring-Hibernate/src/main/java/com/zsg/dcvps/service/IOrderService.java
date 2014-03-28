package com.zsg.dcvps.service;

import java.util.List;

import com.zsg.dcvps.entity.Details;
import com.zsg.dcvps.entity.Orders;

public interface IOrderService {

	List<Orders> getOrders();

	Orders findOrder(int orderNum);

	public void deleteOrder(Orders order);

	public void save(Orders order);
	

	Details findDetail(Integer detailId);

	void deleteDetail(Details details);

	List<Details> getDetails(Integer chechId);

	void saveDetails(Details details);
	
}
