package com.ctech.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctech.admin.beans.Paging;
import com.ctech.admin.beans.food_order.Orders;

/**
 * This class has been used for the implementations such as loading the order page for adding/editing the
 * order records, saving the order records into the database, deleting the order record from the 
 * database, validating the order detail record if it is present in database or not.
 * 
 * @author Cybertech
 *
 */
@Controller
public class OrderHandler extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(OrderHandler.class);
	public static final String PAGE_SUBMIT_LISTING = "/orderlist";
	public static final String ORDER_ADD = "orderadd";
	public static final String AJAX_SUBMIT_LISTING = "orderlist";
	@Autowired
	private DAO dao;
	
	/**
	 * This method has been used for loading the order page.
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/order.html", method=RequestMethod.GET)
	public String loadOrderPage(Model m) {
		log.info("Inside OrderHandler : loadOrderPage :");
		return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for saving the order value to the database.
	 * @param orders
	 * @param m
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/orderSave.html", method=RequestMethod.POST)
	public String saveOrderData(@ModelAttribute Orders orders, Model m) {
		log.info("Start of OrderHandler : saveOrderData :: orders"+orders);
		
		if(StringUtils.isEmpty(orders.getisOpen())) {
			orders.setisOpen("1");
		}
		if(StringUtils.isEmpty(orders.getIsUpdated())) {
			orders.setIsUpdated("0");
		}
		 dao.getSqlMapClientTemplate().update("update-orders", orders); 
	     m.addAttribute("message", "Successfully saved order: " + orders.toString());
	     log.info("orders updated successfully");
	     
	     loadOrderPage(m);
	     log.info("End of OrderHandler : saveOrderData");
	     return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading the edit order page based on the selected id
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@RequestMapping(value="/orderEdit.html", method=RequestMethod.GET)
	public String loadEdit(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of OrderHandler : loadEdit :: selectedId"+selectedId);
		
		@SuppressWarnings("deprecation")
		Orders orders = (Orders)dao.getSqlMapClientTemplate().
							queryForObject("retrieve-a-order", "" + selectedId);
		
		if(orders.getchkOpenTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date checkOpenTime = sdf.parse(orders.getchkOpenTime());
				orders.setchkOpenTime(sdf.format(checkOpenTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		m.addAttribute("order", orders);	
		log.info("End of OrderHandler : loadEdit :: orders : "+orders.toString());
		return ORDER_ADD;
	}
	
	/**
	 * This method has been used for deleting the order item from the database and refresh 
	 * the order list page.
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/orderDelete.html", method=RequestMethod.GET)
	public String delete(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of OrderHandler : delete :: selectedId : "+selectedId);
		dao.getSqlMapClientTemplate().delete("delete-order", "" + selectedId);
		loadOrderPage(m);
		log.info("End of OrderHandler : delete");
	    return AJAX_SUBMIT_LISTING;
	}

	@RequestMapping(value="/orderPageData.html")
	public @ResponseBody String loadPageData(HttpServletRequest req) throws IOException {
	    return managePaging(req);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<Orders> loadPageRecords(Paging input) {
		return dao.getSqlMapClientTemplate().queryForList("retrieve-order-item", input);
	}

	@SuppressWarnings("deprecation")
	protected String getTotalCount(Paging input) {
		return (String) dao.getSqlMapClientTemplate().queryForObject("retrieve-order-count",input);
	}
	
	/**
	 * This method has been used for validating if the order details no is present in the database or not.
	 * @param detailId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/validateDetails.html", method = RequestMethod.GET)
	public @ResponseBody String validate(@RequestParam("selectedId") String detailId) {
		log.info("Start of OrderHandler : validate :: detailId : "+detailId);
		int detailIdCount = (Integer) dao.getSqlMapClientTemplate().
				queryForObject("retrieve-detailId",""+ detailId);
		
		log.info("End of OrderHandler : validate :: detailIdCount : "+detailIdCount);
		if (detailIdCount > 0) {
			return "true";
		}
		return "false";
	}
}