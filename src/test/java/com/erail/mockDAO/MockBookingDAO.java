//Author:varun
package com.erail.mockDAO;

import com.erail.database.DAO.IBookingDAO;
import com.erail.models.Booking;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBookingDAO implements IBookingDAO {

    List<Booking> bookingList = new ArrayList<>();

    public MockBookingDAO () {
        generateBookingList();

    }

    @Override
    public long createBookingResponse(Booking booking) {
        bookingList.add(booking);
        return bookingList.size();
    }

    @Override
    public Booking getBookingByBookingNumber(long bookingNumber) {
        for (Booking book: bookingList) {
            if (book.getBookingNumber() == bookingNumber){
                return book;
            }
        }

        return null;
    }

    @Override
    public List<Booking> getBookingByUserId(long userId) {
        List<Booking> bookingList = new ArrayList<>();
        for (Booking book: bookingList) {
            if (book.getUserId() == userId){
               bookingList.add(book);
            }
        }
        return bookingList;
    }

    @Override
    public boolean deleteBookingById(long bookingId) {
        for (Booking book: bookingList) {
            if (book.getId() == bookingId){
                bookingList.remove(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public Booking getBookingById(long id) {
        for (Booking book: bookingList) {
            if (book.getId() == id){
                return book;
            }
        }
    return null;
    }


    public void generateBookingList(){
        Booking booking = new Booking();
        booking.setId(1);
        booking.setFare(40L);
        booking.setTimestamp(new Timestamp(new Date().getTime()));
        booking.setBookingNumber(34324);
        booking.setTrainName("test1");
        booking.setUserId(1);
        booking.setReachTime("10:40");
        booking.setSourceStationName("testSource");
        booking.setDestinationStationName("testDestination");
        booking.setClassType("economy");
        booking.setArrivalTime("09:40");
        booking.setStatus("Booked");
        bookingList.add(booking);
        booking = new Booking();
        booking.setId(2);
        booking.setFare(40L);
        booking.setTimestamp(new Timestamp(new Date().getTime()));
        booking.setBookingNumber(3324);
        booking.setTrainName("test1");
        booking.setUserId(1);
        booking.setReachTime("10:40");
        booking.setSourceStationName("testSource");
        booking.setDestinationStationName("testDestination");
        booking.setClassType("economy");
        booking.setArrivalTime("09:40");
        booking.setStatus("Cancellation");
        bookingList.add(booking);
    }

	@Override
	public boolean updateBookingById(long id) throws Exception {
		return true;
	}

	@Override
	public long getTotalBooking() throws Exception {
		long count =0;
		for (Booking book: bookingList) {
            if (book.getStatus() == "Booked"){
                 count++;
            }
        }
    return count;
	}

	@Override
	public long getTotalCancellation() throws Exception {
		long count =0;
		for (Booking book: bookingList) {
            if (book.getStatus() == "Cancelled"){
                 count++;
            }
        }
    return count;
	}

    @Override
    public List<Booking> getBookingByTrainNameAndStatus(String status, Timestamp bookingDate, String trainName, String sourceStation) throws Exception {
        List<Booking> bookingList = new ArrayList<>();
        for (Booking book: bookingList) {
            if (book.getStatus().equals(status) && book.getBookingDate().after( bookingDate) && book.getTrainName().equals(trainName) && book.getSourceStationName().equals(sourceStation)){
                bookingList.add(book);
            }
        }
        return bookingList;
    }

    @Override
    public boolean updateBookingById(long id, String arrivalTime) throws Exception {
        return false;
    }
}
