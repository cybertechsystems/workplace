package com.ctech.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ctech.admin.beans.Paging;

public class LogoutHandler extends BaseController {
	private final static String AUTH_ID = "fishcoAuthId";

	@RequestMapping(value="/logout.html", method=RequestMethod.GET)
	public String loadCategoryPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute(AUTH_ID, null);
		return "logout";
	}
	
	@Override
	protected Object loadPageRecords(Paging input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getTotalCount(Paging input) {
		// TODO Auto-generated method stub
		return null;
	}

}