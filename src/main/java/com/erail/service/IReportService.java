package com.erail.service;

import java.util.Map;
public interface IReportService { 

	public Map<String,Integer> getBookingCountByYear(String Year) throws Exception;
	
	public Map<String, Integer> getBookingCountByMonthYear(String Year,String Month) throws Exception;
	
}
