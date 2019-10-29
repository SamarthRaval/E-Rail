//Author:Samarth
package com.erail.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.models.User;
import com.erail.sendMail.SendMail;
import com.erail.validatePassword.ValidatePassword;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	private IServiceFactory iServiceFactory;

	LoginController() {
		iServiceFactory = new ServiceFatory();
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userLogin(@RequestBody User user, HttpServletResponse res, HttpServletRequest req) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("In user login controller: to login into system");
			System.out.println("in login controller");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
			User userObj = iServiceFactory.createUserService().findUserByEmail(user.getUsername());
			System.out.println(user.getUsername());
			if (userObj == null) {
				response.put("status", 401);
				response.put("Message", "Bad Credentials");
				logger.info("Bad request user object is null: No such data exists for given username");
			} else if (passwordEncoder.matches(user.getPassword(), userObj.getPassword())) {
				System.out.println("in login controller");
				response.put("status", 200);
				response.put("Message", "Successful Login");
				response.put("role", userObj.getRole());
				HttpSession session = req.getSession();
				session.setAttribute("name", userObj.getName());
				session.setAttribute("userId", userObj.getId());
				session.setAttribute("role", userObj.getRole());
				session.setAttribute("username", userObj.getUsername());
				logger.info("Successful login has been done");
			} else {
				response.put("status", 502);
				response.put("Error", "Login failed");
				System.out.println("password not matched");
				logger.info("Login failed: password doesn't match");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "login failed");
			logger.error("Error in user login controller:" + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changePassword(@RequestBody User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		ValidatePassword validatePassword = new ValidatePassword();
		String passwordMsg;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			logger.info("In change password controller: To change the password");
			passwordMsg = validatePassword.validateMyPassword(user.getPassword());
			if (passwordMsg.equals("success")) {
				System.out.println("password fullfilled all rules");
				logger.info("Password has fullfilled all the validations");
				String hashedPassword = passwordEncoder.encode(user.getPassword());
				user.setPassword(hashedPassword);
				long userDetail = iServiceFactory.createUserService().changePassword(user.getId(), user);
				if (userDetail != 0) {
					response.put("status", 200);
					response.put("data_id", userDetail);
					response.put("Message", "Successful");
					logger.info("User's password changed successfully");
				} else {
					response.put("status", 401);
					response.put("Message", "User not Updated");
					logger.info("User not updated");
				}
			} else {
				System.out.println("Minimum Length is not satisfied");
				response.put("status", 402);
				response.put("Message", passwordMsg);
				logger.info("User's password changed successfully");
				response.put("Error", passwordMsg);
				logger.info(passwordMsg);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "User not Updated");
			logger.error("Error: changed password unsuccessful:" + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	@ResponseBody

	public Map<String, Object> forgetPassword(HttpServletRequest req, @RequestBody User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Forget password controller");
			User userObj = iServiceFactory.createUserService().findUserByEmail(user.getUsername());
			if (userObj.getUsername() == null) {
				response.put("message", "No data found");
				return response;
			}
			String emailBody = "";
			if (req.getServerName().equals("localhost")) {
				emailBody = "http://" + req.getServerName() + ":9000/resetPassword?id=" + userObj.getId();
			} else {
				emailBody = "https://" + req.getServerName() + "/resetPassword?id=" + userObj.getId();
			}
			SendMail sendMail = SendMail.getInstance();
			sendMail.sendMail(userObj.getUsername(), "Reset Password Link", emailBody);
			logger.info("Email sent for redirecting to change password page");
			response.put("status", 200);
			response.put("userid", userObj.getId());
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "Forget Password failed");
			logger.error("Error: Forget Password unsuccessful" + ex.getLocalizedMessage());
			return response;
		}
	}

	@RequestMapping(value = "/forgetUserId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forgetUserId(@RequestBody User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Forget user id controller");
			User userObj = iServiceFactory.createUserService().findUserByEmail(user.getUsername());
			if (userObj == null) {
				response.put("message", "No data found");
				logger.info("No data exists for the given username");
				return response;
			} else if (user.getName().equals(userObj.getName()) && (user.getPhone() == userObj.getPhone())) {
				response.put("status", 200);
				response.put("username", userObj.getUsername());
				logger.info("Username found for the given details");
				return response;
			} else {
				response.put("status", 404);
				response.put("error", "Wrong details");
				logger.info("Forget password controller");
				return response;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "retrieving userid failed");
			logger.error("Error: While getting user-id" + ex.getLocalizedMessage());
			return response;
		}
	}

	//Varun
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Inside Logout controller");
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("name");
			if (username != null) {
				session.invalidate();
				logger.info("Session invalid ");
				return "redirect:/login";
			} else {
				return "redirect:/login";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Error", "Logout Failed");
			logger.error("Error: Logout Failed" + ex.getLocalizedMessage());
			return null;
		}
	}
}
