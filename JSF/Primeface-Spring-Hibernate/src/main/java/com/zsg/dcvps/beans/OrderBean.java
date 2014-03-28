package com.zsg.dcvps.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zsg.dcvps.dao.DetailDao;
import com.zsg.dcvps.dao.OrderDAO;
import com.zsg.dcvps.entity.Details;
import com.zsg.dcvps.entity.Orders;
import com.zsg.dcvps.entity.User;
import com.zsg.dcvps.service.IOrderService;
import com.zsg.dcvps.util.Util;

@Component("orderBean")
@RequestScoped
public class OrderBean implements Serializable {

	private Orders order;
	private User user ;
	private Details details;

	private String page = "/crm/component/login";

	@Autowired
	private IOrderService orderService;

	@Autowired
	private RegisterBean registerBean;
	
	@Autowired
	private UserBean userBean;
	
	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private DetailDao detailsDao;
	
	private List<Orders> orderList;
	private List<Details> detailList;
	
	private LazyDataModel<Orders> orderModel;
	private LazyDataModel<Details> detailModel;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public LazyDataModel<Details> getDetailModel() {
		return detailModel;
	}

	public void setDetailModel(LazyDataModel<Details> detailModel) {
		this.detailModel = detailModel;
	}
	
	public LazyDataModel<Orders> getOrderModel() {
		return orderModel;
	}

	public void initializeDataModel() {
		orderModel = new OrderDataModel(orderDao);
		detailModel = new DetailsDataModel(detailsDao);
	}
	
	public void setOrderModel(LazyDataModel<Orders> orderModel) {
		this.orderModel = orderModel;
	}

	public List<Orders> getOrderList() {
			orderList = new ArrayList<Orders>();
			orderList.addAll(orderService.getOrders());
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	public String getPage() {
		if(Util.getSession() == null || user == null) {
			user = null;
			return "../component/login";
		}
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	
	public void editOrder(int id) {
		//int cid = Integer.parseInt(id);
		order = orderService.findOrder(id);
		page = "../order/editOrder";
	}
	
	public void deleteOrder(Orders orders) {
		orderService.deleteOrder(orders);
		page = "../order/order";
	}

	public boolean isSessionExist() {
		return (Util.getSession() != null && user != null);
	}
	
	public void logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		order = null;
		userBean.setUser(null);
		user = null;
		page = "../component/login";
	}
	
	public void save(){
		orderService.save(order);
		page = "../order/order";
	}
	public void cancel() {
		page = "../order/order";
	}

	public List<Details> getDetailList() {
		if(detailList == null) {
			detailList = new ArrayList<Details>();
			detailList.addAll(orderService.getDetails(order.getCheckId()));
		}
		return detailList;
	}

	public void setDetailList(List<Details> detailList) {
		this.detailList = detailList;
	}
	
	public void editDetails(int id) {
		//int did=Integer.parseInt(id);
		details = orderService.findDetail(id);
		page = "../order/editDetails";
	}
	
	public void deleteDetails(Details detail) {
		orderService.deleteDetail(detail);
		detailList = orderService.getDetails(detail.getCheckId());
		page = "../order/orderDetail";
	}
	
	public void showDetails(int id) {
		detailList = orderService.getDetails(id);
		page = "../order/orderDetail";
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}
	
	public void saveDetails(){
		orderService.saveDetails(details);
		page = "../order/orderDetail";
	}
	public void cancelDetails() {
		page = "../order/orderDetail";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
