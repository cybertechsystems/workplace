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
import com.ctech.admin.beans.food_order.Details;
import com.ctech.admin.util.Util;

/**
 * This class has been used for the implementations such as loading the order details page, storing the order details
 * value to the database, loading the order details page for editing purpose, delete operation on the list page,
 * validating the input fields etc.
 *  
 * @author Cybertech
 *
 */
@Controller
public class OrderDetailsHandler extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(OrderDetailsHandler.class);
	public static final String PAGE_SUBMIT_LISTING = "/orderdetailslist";
	public static final String ORDER_DETAIL_ADD = "orderdetailsadd";
	public static final String AJAX_SUBMIT_LISTING = "orderdetailslist";
	@Autowired
	private DAO dao;
	private String CheckIDE;
	
	/**
	 * This method has been used for loading the order details page.
	 * @param m
	 * @param CheckID
	 * @return
	 */
	@RequestMapping(value="/orderDetails.html", method=RequestMethod.POST)
	public String loadOrderDetailPage(Model m, @RequestParam("CheckId") String CheckID) {
		log.info("Inside OrderDetailsHandler : loadOrderDetailPage :: CheckID : "+CheckID);
		CheckIDE = CheckID;
		return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for saving the order details value to the database.
	 * @param details
	 * @param m
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/orderDetailsSave.html", method=RequestMethod.POST)
	public String saveOrderDetailsData(@ModelAttribute Details details, Model m) {
		log.info("Start of OrderDetailsHandler : saveOrderDetailsData :: details : "+details);
		if(StringUtils.isEmpty(details.getMenuItemNo())) {
			details.setMenuItemNo(null);
		}
		if(StringUtils.isEmpty(details.getIsCondiment())) {
			details.setIsCondiment(null);
		}
		if(StringUtils.isEmpty(details.getQuantity())) {
			details.setQuantity(null);
		}
		if(StringUtils.isEmpty(details.getIsUpdated())) {
			details.setIsUpdated("0");
		}
		if(details != null && Util.isNumeric(details.getDetailId()) 
				&& Integer.parseInt(details.getDetailId()) > 0 ) {
			dao.getSqlMapClientTemplate().update("update-orders-detail", details); 
		}
	     m.addAttribute("message", "Successfully Updated Order Details: " + details.toString());
	     log.info("End of OrderDetailsHandler : saveOrderDetailsData");
	     
	     return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading the edit order details page based on the selected id
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/orderDetailsEdit.html", method=RequestMethod.GET)
	public String loadEdit(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of OrderDetailsHandler : loadEdit :: selectedId : " + selectedId);
		
		Details details = (Details)dao.getSqlMapClientTemplate().
							queryForObject("retrieve-a-order-detail-item", "" + selectedId);
		if(details.getChkOpenTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date checkOpenTime = sdf.parse(details.getChkOpenTime());
				details.setTimestamp(sdf.format(checkOpenTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}
		m.addAttribute("details", details);
		log.info("End of OrderDetailsHandler : loadEdit :: details : " + details.toString());
		return ORDER_DETAIL_ADD;
	}
	
	/**
	 * This method has been used for deleting the order details item from the database and refresh 
	 * the order details list page.
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/orderDetailsDelete.html", method=RequestMethod.GET)
	public String delete(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of OrderDetailsHandler : delete :: selectedId : " + selectedId);
		dao.getSqlMapClientTemplate().delete("delete-order-detail-item", "" + selectedId);
		log.info("End of OrderDetailsHandler : delete");
		return AJAX_SUBMIT_LISTING;
	}

	@RequestMapping(value="/orderDetailsPageData.html")
	public @ResponseBody String loadPageData(HttpServletRequest req) throws IOException {
		req.setAttribute("CheckIDE", CheckIDE);
	    return managePaging(req);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<Details> loadPageRecords(Paging input) {
		input.setCheckID(CheckIDE);
		return dao.getSqlMapClientTemplate().queryForList("retrieve-order-detail-item", input);
	}

	@SuppressWarnings("deprecation")
	protected String getTotalCount(Paging input) {
		input.setCheckID(CheckIDE);
		return (String) dao.getSqlMapClientTemplate().queryForObject("retrieve-order-detail-count",input);
	}
	
	/**
	 * This method has been used for validating if the menu-item no is present in the database or not.
	 * @param menuItemNo
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/validateMenuItem.html", method = RequestMethod.GET)
	public @ResponseBody String validate(@RequestParam("selectedno") String menuItemNo) {
		log.info("Start of OrderDetailsHandler : validate :: menuItemNo : " + menuItemNo);
		int menuitemCount = (Integer) dao.getSqlMapClientTemplate().
				queryForObject("retrieve-menuitem-no", menuItemNo);
		
		log.info("End of OrderDetailsHandler : validate :: menuitemCount : " + menuitemCount);
		if (menuitemCount > 0) {
			return "true";
		}
		return "false";
	}
}