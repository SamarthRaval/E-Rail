package com.erail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.models.User;
import com.erail.validatePassword.UniqueUsername;
import com.erail.validatePassword.ValidatePassword;

@Controller
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	private IServiceFactory iServiceFactory;

	UserController() {
		iServiceFactory = new ServiceFatory();
	}

	//Dhruvi
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserList() {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("get user list controller");
			List<User> userList = iServiceFactory.createUserService().getAllUsers();
			if (userList == null) {
				response.put("Message", "Users not found");
				logger.info("Error: User not found");
			} else {
				response.put("status", 200);
				response.put("data", userList);
				response.put("Message", "Successful");
				logger.info("All users found");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Users not found");
			logger.error("Error: User not found" + ex.getLocalizedMessage());
		}
		return response;
	}

	//Dhruvi,Samarth
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(@RequestBody User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		ValidatePassword validatePasswordPass = new ValidatePassword();
		UniqueUsername uniqueUsername = new UniqueUsername();
		String passwordMsg;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			logger.info("add user controller");
			if (uniqueUsername.validateUsername(user.getUsername()) && uniqueUsername.uniqueUsername(user.getUsername())) {
				logger.info("Username is unique and validated");
				passwordMsg = validatePasswordPass.validateMyPassword(user.getPassword());
				if (passwordMsg.equals("success")) {
					logger.info("Validation of my password is successfull");
					System.out.println("password has minimum length");
					String hashedPassword = passwordEncoder.encode(user.getPassword());
					user.setPassword(hashedPassword);
					long userId = iServiceFactory.createUserService().addUser(user);
					if (userId != 0) {
						response.put("status", 200);
						response.put("message", " User Added Successfully");
						logger.info("user has been added successfully");
					} else {
						response.put("Error", "User not Added");
						logger.info("Error User is not added");
					}
				} else {
					response.put("Error", passwordMsg);
					logger.info("Error: " + passwordMsg);
				}
			} else {
				response.put("status", 204);
				response.put("Error", " Username must be UNIQUE and VALID");
				logger.info("Error: Username must be UNIQUE and VALID");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "User not Added");
			logger.error("Error:" + ex.getLocalizedMessage());
		}
		return response;
	}

	//Dhruvi
	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserDetails(@RequestParam(value = "userId", required = true) long userId,
			@RequestBody User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("update user details");
			long userDetail = iServiceFactory.createUserService().updateUserDetail(userId, user);
			if (userDetail == 0) {
				response.put("Message", "User not Updated");
				logger.info("Error : User not updated");
			} else {
				response.put("status", 200);
				response.put("data_id", userDetail);
				response.put("Message", "Successful");
				logger.info("User is updated successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "User not Updated");
			logger.error("Error: User is not successfully updated" + ex.getLocalizedMessage());
		}
		return response;
	}

	//Dhruvi
	@RequestMapping(value = "/deleteUserbyId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteUserbyId(@RequestParam("userId") long userId) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("delete the user by id controller");
			long userDetail = iServiceFactory.createUserService().deleteUserById(userId);
			if (userDetail == 0) {
				response.put("Message", "User not deleted");
				logger.info("Error: User is not deleted");
			} else {
				response.put("status", 200);
				response.put("Message", "delete Successfully");
				logger.info("User is successfully deleted for given userid " + userId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "User not deleted");
			logger.error("Exception for deleting the user" + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/findUserByEmail", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findUserByEmail(@RequestParam("email") String email) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Finding user by emailId controller");
			User userDetails = iServiceFactory.createUserService().findUserByEmail(email);
			if (userDetails == null) {
				response.put("Message", "User not found");
				logger.info("Error: User not found");
			} else {
				response.put("status", 200);
				response.put("data", userDetails);
				response.put("Message", "Successful");
				logger.info("User details found successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "User not found");
			logger.error("Exception User not found" + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/findUserById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findUserById(@RequestParam("userId") long userId) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Find user by id controller");
			User userDetail = iServiceFactory.createUserService().findUserById(userId);
			if (userDetail == null) {
				response.put("Message", "User not found");
				logger.info("User not found for the given id " + userId);
			} else {
				response.put("status", 200);
				response.put("data", userDetail);
				response.put("Message", "Successful");
				logger.info("User found for given id " + userId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "User not found");
			logger.error("Exception while finding user using id" + ex.getLocalizedMessage());
		}
		return response;
	}


	//Samarth
	@RequestMapping(value = "/findUserByPhone", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUserByPhone(@RequestBody User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Finding user using phonenumber controller");
			User userDetail = iServiceFactory.createUserService().findUserByPhone(user.getPhone(), user.getName());
			if (userDetail.getUsername() == null) {
				response.put("Error", "User not found");
				logger.info("user not found for given phonenumber");
			} else {
				response.put("status", 200);
				response.put("data", userDetail.getUsername());
				logger.info("User found for given phonenumber");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "User not found");
			logger.error("Exception occured while finding user by phonenumber" + ex.getLocalizedMessage());
		}
		return response;
	}
	
	//Dhruvi
	@RequestMapping(value = "/getTotalNonAdminUser" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTotalUser() {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("getting Count of total Non-Admin Users");
			Long TotalNonAdminUser = iServiceFactory.createUserService().getcountOfUsers();
			if (TotalNonAdminUser == 0) {
				response.put("Message", "No NonAdminUsers");
				logger.info("No NonAdminUsers");
			} else {
				response.put("status", 200);
				response.put("count", TotalNonAdminUser);
				response.put("Message", " Total NonAdminUsers " + TotalNonAdminUser);
				logger.info("Total NonAdminUsers " + TotalNonAdminUser);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "No NonAdminUsers");
			logger.info("No NonAdminUsers");
			logger.error("Error in fetching NonAdminUsers Count " + ex.getLocalizedMessage());
		}
		return response;
	}
}
