package com.ctech.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ctech.dao.OrderDAO;
import com.ctech.entity.Orders;
import com.ctech.util.LazySorter;

/**
 * This Class has been used for the implementation such as loading the 
 * Orders records, getting the selected row items.. 
 * @author Cybertech
 *
 */
@SuppressWarnings("serial")
public class OrderDataModel extends LazyDataModel<Orders> implements Serializable{
	 
    private List<Orders> orderList;
    private OrderDAO orderDao;
 
    public OrderDataModel(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }
 
    /**
     * This method has been used for loading all the order records after 
     * getting from the database.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
 
	/**
	 * This method has been used for getting the corresponding data for the 
	 * selected row.
	 */
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