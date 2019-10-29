//Author: Dhruvi
package com.erail.database.DAO.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.erail.controller.BookingController;
import com.erail.database.DatabaseConfig;
import com.erail.database.DAO.IReportDAO;
import com.mysql.cj.jdbc.CallableStatement;
@Component
public class ReportDAO implements IReportDAO{

	private static final Logger logger = LogManager.getLogger(ReportDAO.class);
	
	@Override
	public Map<String, Integer> getBookingCountByYear(String Year) throws Exception{
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call Procedure getBookingCountByYear for year " + Year);
			String SQL = "{call getBookingCountByYear (?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setString(1,Year);
			ResultSet rs = cstmt.executeQuery();
			Map<String,Integer> list = new HashMap<String, Integer>();
			while (rs.next())
			{
				list.put(rs.getString("trainName"),rs.getInt("count"));
			}
			return list;
	}

	@Override
	public Map<String, Integer> getBookingCountByMonthYear(String Year, String Month)throws Exception {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call Procedure getBookingCountByMonthYear for year,month " + Year +" , " + Month);
			String SQL = "{call getBookingCountByMonthYear (?,?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setString(1,Year);
			cstmt.setString(2,Month);
			ResultSet rs = cstmt.executeQuery();
			Map<String,Integer> list = new HashMap<String, Integer>();
			
			while (rs.next())
			{
				list.put(rs.getString("trainName"),rs.getInt("count"));
			}
			return list;
		}
}