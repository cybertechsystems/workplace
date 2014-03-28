package com.zsg.dcvps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsg.dcvps.dao.DetailDao;
import com.zsg.dcvps.dao.OrderDAO;
import com.zsg.dcvps.entity.Details;
import com.zsg.dcvps.entity.Orders;

/**
 * This class has been used for the implementation such as fetching/storing/deleting
 * the corresponding orders/details records into the database.
 * @author Cybertech1
 *
 */

@Service("orderService")
public class OrderService implements IOrderService{

	@Autowired
	private OrderDAO dao;
	 
	@Autowired
	private DetailDao detailDao;
	 
	/**
	 * This method has been used for getting all the orders from the database.
	 */
	@Override
	public List<Orders> getOrders() {
		List<Orders> orderList = dao.findAll("Orders.findAllOrders", new Object[]{});
		return orderList;
	}

	/**
	 * This method has been used for getting the order values for the corresponding order id. 
	 */
	@Override
	public Orders findOrder(int checkId) {
		Orders orders=dao.find("Orders.findByOrderscheckId", new Object[]{checkId});
		return orders;
	}

	/**
	 * This method has been used for deleting the order from the database.
	 */
	@Override
	public void deleteOrder(Orders orders) {
		dao.delete(orders);
	}

	/**
	 * This method has been used for saving the order record into the database.
	 */
	@Override
	public void save(Orders order) {
		dao.update(order);		
	}

	/**
	 * This method has been used for getting the details for the corresponding order.
	 */
	@Override
	public List<Details> getDetails(Integer chechId) {
		 List<Details> List = detailDao.findAll("Details.findDetails", new Object[]{chechId});
		return List;
	}

	/**
	 * This method has been used for finding the details for the corresponding
	 * details input.
	 */
	@Override
	public Details findDetail(Integer detailId) {
		Details details=detailDao.find("Details.findByDetailId", new Object[]{detailId});
		return details;
	}

	/**
	 * This method has been used for deleting the details record from the database.
	 */
	@Override
	public void deleteDetail(Details details) {
		detailDao.delete(details);
	}

	/**
	 * This method has been used for saving the details record into the database.
	 */
	@Override
	public void saveDetails(Details details) {
		detailDao.update(details);
	}

}
