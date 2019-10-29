//Author:Varun 
package com.erail.database.DAO;

import com.erail.models.Booking;

import java.sql.Timestamp;
import java.util.List;

public interface IBookingDAO {

	public long createBookingResponse(Booking booking) throws  Exception;
	public Booking getBookingByBookingNumber(long bookingNumber) throws  Exception;
	public List<Booking> getBookingByUserId(long userId) throws  Exception;
	public boolean deleteBookingById(long bookingId) throws  Exception;
	public Booking getBookingById(long id) throws  Exception;
	public boolean updateBookingById(long id) throws  Exception;
	public long getTotalBooking() throws  Exception;
	public long getTotalCancellation() throws  Exception;
	public List<Booking> getBookingByTrainNameAndStatus(String status, Timestamp bookingDate, String trainName, String sourceStation) throws  Exception;
	public boolean updateBookingById(long id, String arrivalTime) throws Exception;
}
