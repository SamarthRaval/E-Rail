//Author : Dhruvi Shah
package com.erail.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;

@Controller
public class ReportController {
	private IServiceFactory iServiceFactory;
	
	ReportController(){
		iServiceFactory = new ServiceFatory();
	}
	 private static final Logger logger = LogManager.getLogger(ReportController.class);

	@RequestMapping(value = "/bookingByMonth" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDataForBookingByMonth(@RequestParam("Year") String Year,@RequestParam("Month") String Month) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("get Booking by month - for " + Month +"-" +Year);
			Map<String, Integer> reportData=  iServiceFactory.createReportService().getBookingCountByMonthYear(Year, Month);
			if(reportData.isEmpty())
			{
				logger.info("get Booking by month - Data not found for " + Month +"-" +Year);
				response.put("Message", "No bookings");
			}
			else
			{
				response.put("status", 200);
				response.put("data_id", reportData);
				response.put("Message", "Data Found");
				logger.info("get Booking by month - Data found for " + Month +"-" +Year);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "No data found");
			logger.error("error in getting Booking by month " + ex.getLocalizedMessage() );
		}
		return response;
}
	
	@RequestMapping(value = "/bookingByYear" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getDataForBookingByYear(@RequestParam("Year") String Year) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("get Booking by Year - for " +Year);
			Map<String, Integer> reportData=  iServiceFactory.createReportService().getBookingCountByYear(Year);
			if(reportData.isEmpty())
			{
				logger.info("get Booking by Year -Data not found for " +Year);
				response.put("Message", "No bookings");
			}
			else
			{
				response.put("status", 200);
				response.put("data_id", reportData);
				response.put("Message", "Data found");
				logger.info("get Booking by Year -Data found for " +Year);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			logger.error("error in getting Booking by Year " + ex.getLocalizedMessage() );
			response.put("Message", "No data found");
		}
		return response;
	}
}
