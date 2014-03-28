package com.zsg.dcvps.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsg.dcvps.dao.AddressDao;
import com.zsg.dcvps.dao.UserDao;
import com.zsg.dcvps.entity.Address;
import com.zsg.dcvps.entity.User;
import com.zsg.dcvps.util.Util;


@Service("userService")
public class UserService implements IUserService, Serializable {
    
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private AddressDao addressDao;
    
    @Override
    @Transactional
    public void save(User user) throws Exception {
    	Address address = user.getAddress();
        
    	user.setPassword(Util.getMD5Password(user.getPassword()));
        user.getAddressList().add(address);
        userDao.update(user);
        
        address.setUser(user);
        addressDao.update(address);
    }
    
    /**
     * This method has been used for finding the corresponding user from the database based on
     * the input user name.
     */
    @Override
    @Transactional(readOnly = true)
    public User findUser(String username) {
    	User user = null;
        List<User> result = userDao.findAll("User.findByUserName", new Object[]{username});
        if(result!=null) {
            if(result.size()>1) {
                LOG.error("More than one user found with login: " + username);
                throw new RuntimeException("More than one user found.");
            }
            if(!result.isEmpty()) {
                user = result.get(0);
                if(user.getAddressList() != null && !user.getAddressList().isEmpty()) {
                	Address add = user.getAddressList().get(0);
                	user.setAddress(add);
                }
            }
        }
        return user;
    }
    
    /**
     * This method has been used for getting the user details for the corresponding 
     * user name and password from the database.
     */
    @Override
    @Transactional(readOnly = true)
    public User findUserDetails(String username, String password) {
        User user = null;
        List<User> result = userDao.findAll("User.findByUserDetails", new Object[]{username, password});
        if(result!=null) {
            if(!result.isEmpty()) {
                user = result.get(0);
                /*if(user != null && user.getRoleList() != null && !"ADMIN".equalsIgnoreCase(user.getRoleList().get(0).getRoleName())) {
                	Util.addErrorMsg("Permission ",	" Denied !");
                }*/
            }
        }
        return user;
    }

    /**
     * This method has been used for checking if the user is available in the database.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean isUsernameAvailable(String username) {
        List<User> userList = userDao.findAll("User.findByUserName", new Object[]{username});
        return userList==null || userList.isEmpty();
    }

    /**
     * This method has been used for checking if the email has been already present
     * in the database or not.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email) {
        List<User> userList = userDao.findAll("User.findByEmail", new Object[]{email});
        return userList==null || userList.isEmpty();
    }

	
    /**
     * This method has been used for getting all the users from the database.
     */
    @Override
	@Transactional(readOnly = true)
	public List<User> getUsers() {
		List<User> userList = userDao.findAll("User.findAllUsers", new Object[]{});
		return userList;
	}

	/**
	 * This method has been used for getting the user records based on the input 
	 * parameters.
	 */
    @Override
	public List<User> searchResults(String searchId1, String searchId2, String searchId3) {
		List<User> searchList = userDao.findAll("User.searchResults", new Object[]{"%"+searchId1+"%", "%"+searchId2+"%", "%"+searchId3+"%"});
		return searchList;
	}

}
