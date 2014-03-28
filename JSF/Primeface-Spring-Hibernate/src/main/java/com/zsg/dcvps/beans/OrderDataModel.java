package com.zsg.dcvps.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.zsg.dcvps.dao.OrderDAO;
import com.zsg.dcvps.entity.Orders;
import com.zsg.dcvps.util.LazySorter;

public class OrderDataModel extends LazyDataModel<Orders> implements Serializable{
	 
    private List<Orders> orderList;
    private OrderDAO orderDao;
 
    public OrderDataModel(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }
 
    @Override
    public List<Orders> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
    	orderList = orderDao.findWithNamedQuery("Orders.findAllOrders", first, first + pageSize);
    	if(sortField != null) {  
            Collections.sort(orderList, new LazySorter(sortField, sortOrder));  
        } 
    	setPageSize(pageSize);
    	setRowCount(orderDao.countTotalRecord("Orders.totalNoOfOrders"));
        return orderList;
    }
 

	@Override
    public Object getRowKey(Orders order) {
        return order.getCheckId().toString();
    }
 
    @Override
    public Orders getRowData(String rowKey) {
        if(orderList == null)
            return null;
       for(Orders order : orderList) {
           if(order.getCheckId().toString().equals(rowKey))
           return order;
       }
       return null;
    }

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
}