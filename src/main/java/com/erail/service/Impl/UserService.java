//Author:Dhruvi,Samarth
package com.erail.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.models.User;
import com.erail.service.IUserService;

@Service
public class UserService implements IUserService {

	private IDAOFactory idaoFactory;

	public UserService() {
		idaoFactory = new DAOFactory();
	}

	@Override
	public long addUser(User User) throws Exception {
		return idaoFactory.createUserDAO().addUser(User);
	}

	@Override
	public long updateUserDetail(long userId, User user) throws Exception {
		return idaoFactory.createUserDAO().updateUserDetail(userId, user);
	}

	@Override
	public long deleteUserById(long userId) throws Exception {
		return idaoFactory.createUserDAO().deleteUserById(userId);
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		return idaoFactory.createUserDAO().findUserByEmail(email);
	}

	@Override
	public User findUserById(long UserId) throws Exception {
		return idaoFactory.createUserDAO().findUserById(UserId);
	}

	@Override
	public User findPasswordByEmail(String email) throws Exception {
		return null;
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		return idaoFactory.createUserDAO().getAllUsers();
	}

	@Override
	public long changePassword(long userId, User user) throws Exception {
		return idaoFactory.createUserDAO().changePassword(userId, user);
	}

	@Override
	public User findUserByPhone(long phone, String name) throws Exception {
		return idaoFactory.createUserDAO().findUserByPhone(phone, name);
	}

	@Override
	public Long getcountOfUsers() throws Exception {
		return idaoFactory.createUserDAO().getcountOfUsers();
	}

}
