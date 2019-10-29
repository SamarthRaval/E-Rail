//Author:Varun
package com.erail.controller;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.Utility.Utility;
import com.erail.models.Booking;
import com.erail.models.ClassTrain;
import com.erail.models.Station;
import com.erail.sendMail.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookingController {

	private static final Logger logger = LogManager.getLogger(BookingController.class);

	private IServiceFactory iServiceFactory;
	BookingController(){
		iServiceFactory = new ServiceFatory();
	}

	@RequestMapping(value = "/createTrainBooking", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createBooking(HttpServletRequest req,@RequestBody Booking booking, @RequestParam(value = "bookingDate") String bookingDate) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {

			logger.info("In booking controller : create booking");
			HttpSession session=req.getSession();

			Timestamp ts = new Timestamp(new Date().getTime());
			booking.setTimestamp(ts);

			long bookingNumber = Utility.generateRandomNumber();

			Booking book = iServiceFactory.createBookingService().getBookingByBookingNumber(bookingNumber);
			if(book == null){
				booking.setBookingNumber(bookingNumber);
				if(bookingDate.equals("") || new SimpleDateFormat("MM/dd/yyyy").parse(bookingDate).before(new Date())){
					response.put("message","please enter valid boarding date");
					response.put("status", 3001);
					return response;
				}
				 else if(!bookingDate.equals("") && new SimpleDateFormat("MM/dd/yyyy").parse(bookingDate).after(new Date())){
					Date date=new SimpleDateFormat("MM/dd/yyyy").parse(bookingDate);
					Timestamp bookingTime = new Timestamp(date.getTime());
					booking.setBookingDate(bookingTime);
					ClassTrain classTrain = iServiceFactory.createClassTrainService().getClassTrainByType(booking.getClassType());
					long count = iServiceFactory.createStationService().stationCountBetweenStations(booking.getSourceStationName(),booking.getDestinationStationName());
					long fare = count * classTrain.getFare();
					booking.setFare(fare);
					booking.setStatus("Booked");
					response.put("data", iServiceFactory.createBookingService().createBookingResponse(booking));

					List<Map<String,Object>> emailConfigList = iServiceFactory.createEmailConfigService().getEmailConfig();
					String emailBody = emailConfigList.get(0).get("body").toString();
					SendMail sendMail = SendMail.getInstance();
					sendMail.sendMail(session.getAttribute("username").toString(), "Ticket Confirmation", Utility.getEmailBody(emailBody,bookingNumber,booking.getTrainName(),booking.getArrivalTime()));

					response.put("status", 200);
					logger.info("In booking controller : Booking Created  successfully");
					return response;
			}
				 else{
					response.put("status", 3001);
					response.put("data" , "Booking Unsuccessful");
					logger.info("In booking controller : Booking Created  unsuccessful");
					return response;
				}

			}else{
				response.put("status", 3001);
				response.put("data" , " Invalid Date");
				logger.info("Date is invalid");
				return response;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Booking failed");
			logger.error("In booking controller : Message in booking " + ex.getLocalizedMessage());
			return response;
		}
	}

	@RequestMapping(value = "/getBookingById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBookingById(@RequestParam("bookingId") long bookingId) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("In booking controller :get booking by id");
			response.put("data", iServiceFactory.createBookingService().getBookingByBookingNumber(bookingId));
			response.put("status", 200);
			logger.info("In booking controller : booking by id fetched successfully");
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("In booking controller : error in getting booking by Id " + ex.getLocalizedMessage() );
			response.put("Message", "Booking failed");
			return response;
		}
	}

	@RequestMapping(value = "/getBookingByUserId", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBookingByUserId(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("In booking controller : get Booking by userId");
			HttpSession session = req.getSession();
			System.out.println(session.getAttribute("userId"));
			long userId = (long)session.getAttribute("userId");
			response.put("data", iServiceFactory.createBookingService().getBookingByUserId(userId));
			response.put("status", 200);
			logger.info("In booking controller : get Booking by userId successful");
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("In booking controller : error Booking by userId " + ex.getLocalizedMessage());
			response.put("Message", "Booking failed");
			return response;
		}
	}


	@RequestMapping(value = "/deleteBooking", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBookingByUserId(HttpServletRequest req, HttpServletResponse res,@RequestParam(value = "bookingId") long bookingId) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("In booking controller : delete Booking by id");
			Booking ticket = iServiceFactory.createBookingService().getBookingById(bookingId);
			HttpSession session=req.getSession();
			//response.put("data", iServiceFactory.createBookingService().deleteBookingById(bookingId));
			response.put("data", iServiceFactory.createBookingService().UpdateBookingById(bookingId));
			response.put("status", 200);
			logger.info("In booking controller : send mail to the user");
			List<Map<String,Object>> emailConfigList = iServiceFactory.createEmailConfigService().getEmailConfig();
			String emailBody = emailConfigList.get(1).get("body").toString();
			SendMail sendMail = SendMail.getInstance();
			sendMail.sendMail(session.getAttribute("username").toString(), "Cancel Ticket Confirmation", Utility.getEmailBody(emailBody,ticket.getBookingNumber(),ticket.getTrainName(),ticket.getArrivalTime()));
			logger.info("In booking controller : mail sent successfully");
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("In booking controller : error in deleting booking " + ex.getLocalizedMessage());
			response.put("Message", "Booking failed");
			return response;
		}
	}
	
	@RequestMapping(value = "/getTotalBooking" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getTotalBooking() {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("getting Count of total Booking");
			Long totalBooking = iServiceFactory.createBookingService().getTotalBooking();
			if(totalBooking == 0)
			{
				response.put("Message", "No Bookings");
				logger.info("No Bookings");
			}
			else
			{
				response.put("status", 200);
				response.put("count", totalBooking);
				response.put("Message", " Total Bookings " +totalBooking);
				logger.info("Total Bookings " +totalBooking);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "No Bookings");
			logger.info("No Bookings");
			logger.error("Error in fetching Booking Count " + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/getTotalCancellation" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getTotalCancellation() {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("getting Count of total Booking Cancellations");
			Long totalBookingCancellation = iServiceFactory.createBookingService().getTotalCancellation();
			if(totalBookingCancellation == 0)
			{
				response.put("Message", "No Bookings Cancellation");
				logger.info("No Bookings Cancellation");
			}
			else
			{
				response.put("status", 200);
				response.put("count", totalBookingCancellation);
				response.put("Message", " Total Bookings Cancellation " +totalBookingCancellation);
				logger.info("Total Bookings Cancellation " +totalBookingCancellation);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "No Bookings Cancellation");
			logger.info("No Bookings Cancellation");
			logger.error("Error in fetching Booking Cancellation Count " + ex.getLocalizedMessage());
		}
		return response;
	}

	
	
}
