//Author:Samarth
package com.erail.mockDAO;

import java.util.ArrayList;
import java.util.List;

import com.erail.database.DAO.IUserDAO;
import com.erail.models.User;

public class MockUserDAO implements IUserDAO {

	List<User> userList = new ArrayList<User>();

	public MockUserDAO() {
		generateUserList();
	}

	@Override
	public long addUser(User user) {
		userList.add(user);
		return userList.size();
	}

	@Override
	public long updateUserDetail(long userId, User user) {
		for (User userr : userList) {
			if (userr.getId() == userId) {
				userr.setUsername(user.getUsername());
				userr.setPassword(user.getPassword());
				return userr.getId();
			}
		}
		return 0;
	}

	@Override
	public long changePassword(long userId, User user) {
		for (User userr : userList) {
			if (userr.getId() == userId) {
				userr.setPassword(user.getPassword());
				return userr.getId();
			}
		}
		return 0;
	}

	@Override
	public long deleteUserById(long userId) {
		for (User user : userList) {
			if (user.getId() == userId) {
				userList.remove(user);
				return userId;
			}
		}
		return 0;
	}

	@Override
	public User findUserByEmail(String username) {
		for (User user : userList) {
			if (user.getUsername() == username) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findUserByPhone(long phone, String name) {
		for (User user : userList) {
			if (user.getName() == name && user.getPhone() == phone) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findUserById(long UserId) {
		for (User user : userList) {
			if (user.getId() == UserId) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		return userList;
	}

	@Override
	public List<String> getAllUsername() {
		List<String> usernames = new ArrayList<String>();
		for (User user : userList) {
			usernames.add(user.getUsername());
		}
		return usernames;
	}

	public void generateUserList() {
		User user = new User();
		user.setId(1);
		user.setUsername("sam@gmail.com");
		user.setPassword("sama123!@#");
		user.setName("samarth");
		user.setGender("male");
		user.setDateofbirth("1994/11/11");
		user.setPhone(1234567890);
		user.setStreetaddress("6411 south street");
		user.setCity("vadodara");
		user.setPostalcode("B3H 1R2");
		user.setProvince("NS");
		user.setRole(1);
		userList.add(user);
	}

	@Override
	public Long getcountOfUsers() throws Exception {
		long count=0;
		for (User user : userList) {
			if(user.getRole() == 0)
			{
				count ++;
			}
		}
		return count;
	}

}
