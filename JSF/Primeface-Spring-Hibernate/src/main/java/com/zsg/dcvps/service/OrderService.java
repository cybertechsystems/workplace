package com.zsg.dcvps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsg.dcvps.dao.DetailDao;
import com.zsg.dcvps.dao.OrderDAO;
import com.zsg.dcvps.entity.Details;
import com.zsg.dcvps.entity.Orders;

@Service("orderService")
public class OrderService implements IOrderService{

	 @Autowired
	 private OrderDAO dao;
	 
	 @Autowired
	 private DetailDao detailDao;
	 
	@Override
	public List<Orders> getOrders() {
		List<Orders> orderList = dao.findAll("Orders.findAllOrders", new Object[]{});
		return orderList;
		}

	@Override
	public Orders findOrder(int checkId) {
		Orders orders=dao.find("Orders.findByOrderscheckId", new Object[]{checkId});
		return orders;
	}

	@Override
	public void deleteOrder(Orders orders) {
		dao.delete(orders);
	}

	@Override
	public void save(Orders order) {
		dao.update(order);		
	}

	@Override
	public List<Details> getDetails(Integer chechId) {
		 List<Details> List = detailDao.findAll("Details.findDetails", new Object[]{chechId});
		return List;
	}

	@Override
	public Details findDetail(Integer detailId) {
		Details details=detailDao.find("Details.findByDetailId", new Object[]{detailId});
		return details;
	}

	@Override
	public void deleteDetail(Details details) {
		detailDao.delete(details);
	}

	@Override
	public void saveDetails(Details details) {
		detailDao.update(details);
	}

}
