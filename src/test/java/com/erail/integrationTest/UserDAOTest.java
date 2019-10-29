//Author:Samarth
package com.erail.integrationTest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erail.database.DAO.IUserDAO;
import com.erail.mockDAO.MockUserDAO;
import com.erail.models.User;

@SpringBootTest(classes = com.erail.Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class UserDAOTest {

	@Test
	public void addUser() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		User user = new User();
		user.setId(1);
		user.setUsername("parth@gmail.com");
		user.setPassword("parth123!@#");
		user.setName("parth");
		user.setGender("male");
		user.setDateofbirth("1994/09/17");
		user.setPhone(1234567770);
		user.setStreetaddress("6411 south street");
		user.setCity("vadodara");
		user.setPostalcode("B3H 1R2");
		user.setProvince("NS");
		user.setRole(0);
		Assert.assertEquals(2, userDAO.addUser(user));
	}

	@Test
	public void updateUserDetail() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		User user = new User();
		user.setId(1);
		user.setUsername("raval@gmail.com");
		user.setPassword("raval123!@#");
		long userId = userDAO.updateUserDetail(1, user);
		Assert.assertEquals(1, userId);
	}

	@Test
	public void changePassword() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		User user = new User();
		user.setId(1);
		user.setPassword("powerrangerSPD123!@#");
		long userId = userDAO.changePassword(1, user);
		Assert.assertEquals(1, userId);
	}

	@Test
	public void deleteUserById() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		long userId = userDAO.deleteUserById(1);
		Assert.assertEquals(1, userId);
	}

	@Test
	public void findUserByEmail() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		User user = userDAO.findUserByEmail("sam@gmail.com");
		Assert.assertNotNull(user);
	}

	@Test
	public void findUserByPhone() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		User user = userDAO.findUserByPhone(1234567890, "samarth");
		Assert.assertNotNull(user);
	}

	@Test
	public void findUserById() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		User user = userDAO.findUserById(1);
		Assert.assertNotNull(user);
	}

	@Test
	public void getAllUsers() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		List<User> users = userDAO.getAllUsers();
		Assert.assertNotNull(users);
	}

	@Test
	public void getAllUsername() throws Exception {
		IUserDAO userDAO = new MockUserDAO();
		List<String> usernames = userDAO.getAllUsername();
		Assert.assertNotNull(usernames);
	}
}
