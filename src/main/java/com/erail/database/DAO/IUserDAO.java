//Author:Dhruvi
package com.erail.database.DAO;

import java.util.List;

import com.erail.models.User;

public interface IUserDAO {

	public long addUser(User user) throws Exception;

	public long updateUserDetail(long userId, User user) throws Exception;
	
	public long changePassword(long userId, User user) throws Exception;

	public long deleteUserById(long userId) throws Exception;

	public User findUserByEmail(String username) throws Exception;
	
	public User findUserByPhone(long phone,String name) throws Exception;

	public User findUserById(long UserId) throws Exception;
	
	public List<User> getAllUsers() throws Exception;
	
	public List<String> getAllUsername() throws Exception;
	
	public Long getcountOfUsers() throws Exception;

}
