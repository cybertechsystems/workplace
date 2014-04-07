package com.ctech.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctech.admin.beans.Paging;
import com.ctech.admin.beans.menu_item.MenuItem;

/**
 * This class has been used for the implementations such as loading the promotions page, promotions 
 * add page etc.
 * 
 * @author Cybertech
 *
 */
@Controller
public class MenuItemDetailsHandler extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(MenuItemDetailsHandler.class);

	public static final String PAGE_SUBMIT_LISTING = "/promotionlist";
	public static final String PROMOTIONS_ADD = "promotionsadd";
	public static final String AJAX_SUBMIT_LISTING = "promotionlist";
	
	@SuppressWarnings("unused")
	@Value("#{systemProps['menu.image.folder.path']}")
	private String imageFolder;
	
	@Autowired
	private DAO dao;
	
	@SuppressWarnings("unused")
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value="/promotions.html", method=RequestMethod.GET)
	public String loadPromotionsPage(Model m) {
		log.info("Inside MenuItemDetailsHandler : loadPromotionsPage");
		return AJAX_SUBMIT_LISTING;
	}
	
	@RequestMapping(value="/promotionsAdd.html", method=RequestMethod.GET)
	public String loadPromotionsAddPage(Model m) {
		log.info("Inside MenuItemDetailsHandler : loadPromotionsAddPage");
		MenuItem menuitem = new MenuItem();
		
		m.addAttribute("menuitem", menuitem);
		
		return PROMOTIONS_ADD;
	}
	
	@RequestMapping(value="/promotionPageData.html")
	public @ResponseBody String loadPageData(HttpServletRequest req) throws IOException {
	    return managePaging(req);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<MenuItem> loadPageRecords(Paging input) {
		return dao.getSqlMapClientTemplate().queryForList("retrieve-menu-items", input);
	}

	@SuppressWarnings("deprecation")
	protected String getTotalCount(Paging input) {
		return (String) dao.getSqlMapClientTemplate().queryForObject("retrieve-menu-count", input);
	}
	
	
}