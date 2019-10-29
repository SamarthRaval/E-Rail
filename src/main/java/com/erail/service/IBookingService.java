package com.erail.service;


import java.sql.Timestamp;
import java.util.List;

import com.erail.models.Booking;

public interface IBookingService {

	//public List<Booking> getBookingDetails();
	public long createBookingResponse(Booking booking) throws Exception;
	public Booking getBookingByBookingNumber(long bookingNumber) throws Exception;
	public List<Booking> getBookingByUserId(long userId) throws Exception;
	public boolean deleteBookingById(long bookingId) throws Exception;
	public Booking getBookingById(long id) throws Exception;
	public boolean UpdateBookingById(long id) throws Exception;
	public long getTotalBooking() throws  Exception;
	public long getTotalCancellation() throws  Exception;
	public List<Booking> getBookingByTrainNameAndStatus(String status, Timestamp bookingDate, String trainName, String sourceStation) throws  Exception;
	public boolean updateBookingById(long id, String arrivalTime) throws Exception;


}
