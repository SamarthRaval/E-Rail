//Author:Varun
package com.erail.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import org.springframework.stereotype.Service;
import com.erail.models.Booking;
import com.erail.service.IBookingService;

@Service
public class BookingService implements IBookingService{

	private IDAOFactory idaoFactory;

	public BookingService(){
		 idaoFactory = new DAOFactory();
	}

	@Override
	public long createBookingResponse(Booking booking) throws Exception {
		return idaoFactory.createBookingDAO().createBookingResponse(booking);
	}

	@Override
	public Booking getBookingByBookingNumber(long bookingNumber)  throws Exception {
		
		return idaoFactory.createBookingDAO().getBookingByBookingNumber(bookingNumber);
	}

	@Override
	public List<Booking> getBookingByUserId(long userId)  throws Exception{
		return idaoFactory.createBookingDAO().getBookingByUserId(userId);
	}

	@Override
	public boolean deleteBookingById(long bookingId)  throws Exception{
		return idaoFactory.createBookingDAO().deleteBookingById(bookingId);
	}

	@Override
	public Booking getBookingById(long id)  throws Exception {
		return idaoFactory.createBookingDAO().getBookingById(id);
	}

	@Override
	public boolean UpdateBookingById(long id) throws Exception {
		return idaoFactory.createBookingDAO().updateBookingById(id);
	}

	@Override
	public long getTotalBooking() throws Exception {
		return idaoFactory.createBookingDAO().getTotalBooking();
	}

	@Override
	public long getTotalCancellation() throws Exception {
		return idaoFactory.createBookingDAO().getTotalCancellation();
	}

	@Override
	public List<Booking> getBookingByTrainNameAndStatus(String status, Timestamp bookingDate, String trainName, String sourceStation) throws Exception {
		return idaoFactory.createBookingDAO().getBookingByTrainNameAndStatus(status,bookingDate,trainName, sourceStation);
	}

	@Override
	public boolean updateBookingById(long id, String arrivalTime) throws Exception {
		return idaoFactory.createBookingDAO().updateBookingById(id,arrivalTime);
	}


}