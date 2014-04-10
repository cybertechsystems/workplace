package com.ctech.service;

import java.util.List;

import com.ctech.entity.User;

public interface IUserService {
    
    void save(User user) throws Exception;
    
    User findUser(String username);
    
    boolean isUsernameAvailable(String username);
    
    boolean isEmailAvailable(String email);

	User findUserDetails(String login, String password);

	List<User> getUsers();

	List<User> searchResults(String searchId1, String searchId2, String searchId3);
}
