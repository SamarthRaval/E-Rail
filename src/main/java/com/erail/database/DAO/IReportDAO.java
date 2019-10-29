//Author:Dhruvi 
package com.erail.database.DAO;

import java.util.Map;

public interface IReportDAO {
	
	public Map<String, Integer> getBookingCountByYear(String Year) throws Exception;
	
	public Map<String, Integer> getBookingCountByMonthYear(String Year, String Month) throws Exception;

}