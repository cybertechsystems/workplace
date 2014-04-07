package com.ctech.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ctech.admin.BaseController;
import com.ctech.admin.DAO;
import com.ctech.admin.beans.Paging;

@Controller
public class LoginFilter extends  BaseController implements Filter {
	private final static String ADMIN_USERNAME = "admin";
	private final static String LOGIN_PAGE = "login.html";
	private final static String AUTH_ID = "fishcoAuthId";
	private final static String USERNAME_FIELD = "username";
	private final static String PASSWORD_FIELD = "password";

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		SqlMapClientTemplate t = getDao().getSqlMapClientTemplate();
		String url = req.getRequestURL().toString();

		if ((url.matches("^.+/images/.+$") || url.matches("^.+/styles/.+$") || req.getRequestURI().endsWith(
				LOGIN_PAGE))
				&& "GET".equals(req.getMethod())) {
			chain.doFilter(request, response);
			return;
		}

		/*chain.doFilter(request, response);
		return;*/
		if (session == null || session.getAttribute(AUTH_ID) == null) {
			String username = req.getParameter(USERNAME_FIELD);
			String password = req.getParameter(PASSWORD_FIELD);

			String authId = null;
			if (username != null && password != null) {
				String hash = (String) t.queryForObject("retrieve-identity",
						username);
				if (hash != null && hash.equals(DigestUtils.md5Hex(password))) {
					authId = username;
				}
			}

			if (authId != null) {
				session.setAttribute(AUTH_ID, authId);
				if (ADMIN_USERNAME.equals(username)) {
					chain.doFilter(request, response);
				} else {
					req.getRequestDispatcher("/index.jsp").forward(request,
							response);
				}
			} else {
				res.sendRedirect(res.encodeRedirectURL(LOGIN_PAGE));
				return;
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String loginPage(Model m) {
		return "/login";
	}
	
	@Override
	public void destroy() {
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("thinkamas"));
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
