package com.zsg.dcvps.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zsg.dcvps.dao.OrderDAO;
import com.zsg.dcvps.entity.User;
import com.zsg.dcvps.service.IUserService;
import com.zsg.dcvps.util.Util;

@Component("userBean")
@RequestScoped
public class UserBean implements Serializable {

	private User user;

	private String page = "/crm/component/login";

	@Autowired
	private IUserService userService;

	@Autowired
	private RegisterBean registerBean;
	
	private List<User> userList;
	private String searchId;

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	/*private List<User> searchList;
	public List<User> getSearchList() {
		if (getSearchId() == null) {
			return userList;
		} else {
			if (searchList == null) {
				searchList = new ArrayList<User>();
				searchList.addAll(userService.searchResults(getSearchId()));
			}
		}
		return searchList;
	}

	public void setSearchList(List<User> searchList) {
		this.searchList = searchList;
	}*/

	public List<User> getUserList() {
		if (userList == null) {
			userList = new ArrayList<User>();
			userList.addAll(userService.getUsers());
		}
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getPage() {
		if(Util.getSession() == null) {
			return "../component/login";
		}
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getOk() {
		return getUser() != null && getUser().getId() != null;
	}

	public void editUser() {
		user = userService.findUser(user.getLogin());
		registerBean.setUser(user);
		registerBean.setPassword2(user.getPassword());
		page = "../user/profile";
	}

	public void logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		user = null;
		page = "../component/login";
	}
	
	public void searchResults() {
		if (searchId != null && !searchId.isEmpty()) {
			userList = userService.searchResults(searchId, searchId, searchId);
		} else {
			userList = userService.getUsers();
		}
	}
}
