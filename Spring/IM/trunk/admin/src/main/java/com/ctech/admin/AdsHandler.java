package com.ctech.admin;

import java.io.IOException;

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
@Controller
public class AdsHandler extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(AdsHandler.class);
	public static final String PAGE_SUBMIT_LISTING = "/adslist";
	public static final String MENU_ADD = "menuadd";
	public static final String AJAX_SUBMIT_LISTING = "adslist";
	
	@Value("#{systemProps['menu.image.folder.path']}")
	private String imageFolder;
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value="/ads.html", method=RequestMethod.GET)
	public String loadAdsPage(Model m) {
		return PAGE_SUBMIT_LISTING;
	}
	
	@RequestMapping(value="/adsPageData.html")
	public @ResponseBody String loadPageData(HttpServletRequest req) throws IOException {
	    return managePaging(req);
	}

	@Override
	protected Object loadPageRecords(Paging input) {
		return dao.getSqlMapClientTemplate().queryForList("retrieve-ads-item", input);
	}

	@Override
	protected String getTotalCount(Paging input) {
		return (String) dao.getSqlMapClientTemplate().queryForObject("retrieve-ads-count", input);
	}
}