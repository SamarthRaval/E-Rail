//Author: Dhruvi
package com.erail.database.DAO.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.erail.database.DatabaseConfig;
import com.erail.database.DAO.IUserDAO;
import com.erail.models.User;
import com.mysql.cj.jdbc.CallableStatement;

@Component
public class UserDAO implements IUserDAO {

	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	//Samarth
	@Override
	public long addUser(User user) throws Exception {
		long userId = 0;
		logger.info(" Inside the add user DAO method ");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();

		String sqll = "{call addUser (?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(sqll);
		cstmt.setString(1, user.getUsername());
		cstmt.setString(2, user.getPassword());
		cstmt.setString(3, user.getName());
		cstmt.setString(4, user.getGender());
		cstmt.setString(5, user.getDateofbirth());
		cstmt.setLong(6, user.getPhone());
		cstmt.setString(7, user.getStreetaddress());
		cstmt.setString(8, user.getCity());
		cstmt.setString(9, user.getProvince());
		cstmt.setString(10, user.getPostalcode());
		cstmt.setLong(11, user.getRole());

		userId = cstmt.executeUpdate();

		logger.info(" Successfully added all the user details ");
		return userId;
	}

	//Dhruvi
	@Override
	public long updateUserDetail(long userId, User user) throws Exception {
		logger.info(" Updating the user details");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();

		String SQL = "{call updateUserDetail(?,?,?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		cstmt.setString(1, user.getUsername());
		cstmt.setString(2, user.getPassword());
		cstmt.setLong(3, userId);
		long id = cstmt.executeUpdate();

		logger.info(" Successfully updated username for the given user id " + id);

		return id;

	}

	//Dhruvi
	@Override
	public long deleteUserById(long userId) throws Exception {
		logger.info(" Deleting user with givn user id ");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();

		String sql = "{call deleteUserById (?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(sql);
		cstmt.setLong(1, userId);
		userId = cstmt.executeUpdate();

		logger.info(" User deleted for the given user id ");
		return userId;

	}

	//Dhruvi
	@Override
	public User findUserByEmail(String username) throws Exception {
		logger.info(" Finding user for given email ");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call findUserByEmail(?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		cstmt.setString(1, username);
		ResultSet result = cstmt.executeQuery();
		 List<User> UserList = rowMapper(result);
	        if (UserList.size() > 0) {
	            logger.info("Users found");
	            logger.info(" Found user for given username " + username);
	            return UserList.get(0);
	        } else {
	            logger.info("User not found ");
	            return null;
	        }
	}

	//Dhruvi
	@Override
	public User findUserById(long UserId) throws Exception {
		logger.info(" Finding user for given user id ");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call findUserById(?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		cstmt.setLong(1, UserId);
		ResultSet result = cstmt.executeQuery();
		 List<User> UserList = rowMapper(result);
	        if (UserList.size() > 0) {
	            logger.info("Users found");
	            logger.info(" Found user for given user id " + UserId);
	            return UserList.get(0);
	        } else {
	            logger.info("User not found ");
	            return null;
	        }

	}

	//Dhruvi
	@Override
	public List<User> getAllUsers() throws Exception {
		logger.info(" Getting all the users");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();

		String SQL = "{call getAllUser()}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		ResultSet result = cstmt.executeQuery();

		 List<User> UserList = rowMapper(result);
	        if (UserList.size() > 0) {
	            logger.info("Users found");
	            return UserList;
	        } else {
	            logger.info("Users not found ");
	            return null;
	        }
}

	//Samarth
	@Override
	public long changePassword(long userId, User user) throws Exception {
		logger.info(" Updating the password for the user");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call changePassword(?,?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		cstmt.setString(1, user.getPassword());
		cstmt.setLong(2, userId);
		long id = cstmt.executeUpdate();

		logger.info(" Password successfully updated for the user id" + id);
		return id;

	}

	//Samarth
	@Override
	public User findUserByPhone(long phone, String name) throws Exception {
		logger.info(" Finding user by phonenumber");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();

		String sql = "{call findUserByPhone(?,?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(sql);
		cstmt.setLong(1, phone);
		cstmt.setString(2, name);
		ResultSet result = cstmt.executeQuery();

		User user = new User();
		while (result.next()) {
			user.setUsername(result.getString("username"));
			logger.info(" Found user for given phonenumber " + phone);
			return user;
		}
		return user;
	}

	//Samarth
	@Override
	public List<String> getAllUsername() throws Exception {

		logger.info(" Getting all the usernames");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();

		String SQL = "{call getAllUsername()}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		ResultSet result = cstmt.executeQuery();

		List<String> usernames = new ArrayList<String>();

		while (result.next()) {
			usernames.add(result.getString("username"));
		}
		logger.info(" Getting all the usernames");
		return usernames;
	}


	//Dhruvi
	@Override
	public Long getcountOfUsers() throws Exception {
		long Count = 0;
		logger.info(" Getting all the user which are not admin");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call countOfUsers()}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		ResultSet result = cstmt.executeQuery();
		
		while (result.next()) {
			Count = result.getLong(1);
		}
		return Count;
	}

		//Dhruvi
	  private List<User> rowMapper(ResultSet rs) throws Exception {
	        List<User> UserList = new ArrayList<>();
	        while (rs.next()) {
	        	User user = new User();
				user.setId(rs.getLong("id"));
				user.setCity(rs.getString("city"));
				user.setDateofbirth(rs.getString("dateOfBirth"));
				user.setGender(rs.getString("gender"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getLong("phoneNumber"));
				user.setPostalcode(rs.getString("postalCode"));
				user.setProvince(rs.getString("province"));
				user.setStreetaddress(rs.getString("streetAddress"));
				user.setUsername(rs.getString("username"));
				user.setRole(rs.getLong("role"));
	        	UserList.add(user);
	        }
	        return UserList;
	    }

}
