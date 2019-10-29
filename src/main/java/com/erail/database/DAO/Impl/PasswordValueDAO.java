//Author: Samarth
package com.erail.database.DAO.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erail.database.DatabaseConfig;
import com.erail.database.DAO.IPasswordValueDAO;
import com.erail.models.PasswordRule;
import com.mysql.cj.jdbc.CallableStatement;

public class PasswordValueDAO implements IPasswordValueDAO {

	private static final Logger logger = LogManager.getLogger(PasswordValueDAO.class);

	@Override
	public List<PasswordRule> getPasswordInfo() throws Exception{
		
			logger.info(" Getting all the password information");
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();

			String SQL = "{call getPasswordInfo()}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			ResultSet rs = cstmt.executeQuery();

		List<PasswordRule> passwordRuleList = new ArrayList<PasswordRule>();
		PasswordRule passRule = null;

		while (rs.next()) {
			passRule = new PasswordRule();
			passRule.setRuleName(rs.getString("rulename"));
			passRule.setRuleValue(rs.getLong("value"));
			passRule.setStatus(rs.getLong("status"));
			passRule.setRuleDes(rs.getString("message"));
			System.out.println(
					rs.getString("rulename") + rs.getLong("value") + rs.getLong("status") + rs.getString("message"));
			passwordRuleList.add(passRule);
		}
		logger.info(" Got all the information related to the password information");
		return passwordRuleList;

	}

}
