package com.erail.service;

import java.util.List;

import com.erail.models.User;

public interface IUserService {

	public long addUser(User User) throws Exception;
	public long updateUserDetail(long userId,User user) throws Exception;
	public long changePassword(long userId, User user) throws Exception;
	public long deleteUserById(long userId) throws Exception;
	public User findUserByEmail(String email) throws Exception;
	public User findUserByPhone(long phone,String name) throws Exception;
	public User findPasswordByEmail(String email) throws Exception;
	public User findUserById(long UserId) throws Exception;
	public List<User> getAllUsers() throws Exception;
	public Long getcountOfUsers() throws Exception;
	//public User findUserBycontactNumber(String contactNo);
	
}
