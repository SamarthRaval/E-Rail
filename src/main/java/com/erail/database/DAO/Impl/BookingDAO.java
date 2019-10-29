//Author: Varun
package com.erail.database.DAO.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.erail.controller.BookingController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.erail.database.DatabaseConfig;
import com.erail.database.DAO.IBookingDAO;
import com.erail.models.Booking;
import com.mysql.cj.jdbc.CallableStatement;

@Component
public class BookingDAO implements IBookingDAO {

	private static final Logger logger = LogManager.getLogger(BookingDAO.class);

	@SuppressWarnings("null")
	@Override
	public long createBookingResponse(Booking booking) throws  Exception {
			logger.info("in create booking");
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			String SQL = "{call createBooking (?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setLong(1, booking.getBookingNumber());
			cstmt.setString(2, booking.getTrainName());
			cstmt.setString(3, booking.getSourceStationName());
			cstmt.setString(4, booking.getDestinationStationName());
			cstmt.setString(5, booking.getClassType());
			cstmt.setLong(6, booking.getUserId());
			cstmt.setString(7, booking.getArrivalTime());
			cstmt.setString(8, booking.getReachTime());
			cstmt.setTimestamp(9,booking.getTimestamp());
			cstmt.setLong(10,booking.getFare());
			cstmt.setTimestamp(11,booking.getBookingDate());
			cstmt.setString(12,booking.getStatus());
			long bookingId = cstmt.executeUpdate();
            logger.info(" booking created ");
			return bookingId;
	}

	@Override
	public Booking getBookingByBookingNumber(long bookingNumber) throws  Exception {

			logger.info("get booking for booking number " + bookingNumber);
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			String SQL = "{call getBookingByBookingNumber (?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setLong(1, bookingNumber);
			ResultSet rs = cstmt.executeQuery();
			List<Booking> bookingList = rowMapper(rs);
			if(bookingList != null && bookingList.size() != 0){
                logger.info(" booking found for  booking number " + bookingNumber);
				return bookingList.get(0);
			}else{
                logger.info(" no booking found for  booking number " + bookingNumber);
				return null;
			}
	}

	@Override
	public List<Booking> getBookingByUserId(long userId) throws  Exception{

			logger.info("get booking for user " + userId);
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			String SQL = "{call getBookingByUserId (?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setLong(1, userId);
			ResultSet rs = cstmt.executeQuery();
        List<Booking> bookingList = rowMapper(rs);
        if(bookingList != null && bookingList.size() != 0){
            logger.info(" booking found for  userid " + userId );
            return bookingList;
        }else{
            logger.info(" no booking found for userid  " + userId );
            return null;
        }

	}

	@Override
	public boolean deleteBookingById(long bookingId) throws Exception {

			logger.info("delete booking by bookingId" + bookingId);
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			String SQL = "{call deleteBookingById (?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setLong(1, bookingId);
			ResultSet rs = cstmt.executeQuery();
			return true;


	}

	@Override
	public Booking getBookingById(long id) throws Exception {

			logger.info("get booking by id" + id);
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			String SQL = "{call getBookingById (?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setLong(1, id);
			ResultSet rs = cstmt.executeQuery();
			List<Booking> bookingList = rowMapper(rs);
			if(bookingList != null && bookingList.size() != 0){

				logger.info(" booking found by id" + id);
				return bookingList.get(0);
			}else{
				logger.info(" booking not found by id" + id);
				return null;
			}
	}


	private List<Booking> rowMapper(ResultSet rs) throws Exception{
		List<Booking> bookingList = new ArrayList<>();
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setBookingNumber(rs.getLong("booking_number"));
				booking.setArrivalTime(rs.getString("arrivalTime"));
				booking.setClassType(rs.getString("classType"));
				booking.setDestinationStationName(rs.getString("destinationStationName"));
				booking.setSourceStationName(rs.getString("sourceStationName"));
				booking.setReachTime(rs.getString("reachTime"));
				booking.setTrainName(rs.getString("trainName"));
				booking.setUserId(rs.getLong("userId"));
				booking.setId(rs.getLong("id"));
				booking.setTimestamp(rs.getTimestamp("createdAt"));
				booking.setFare(rs.getLong("fare"));
				booking.setBookingDate(rs.getTimestamp("bookingDate"));
				booking.setStatus(rs.getString("status"));
				bookingList.add(booking);
			}
			return bookingList;

	}

	@Override
	public boolean updateBookingById(long id) throws Exception {
		logger.info("get booking by id" + id);
		Booking booking =  getBookingById(id);
		booking.setStatus("Cancelled");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call UpdateBookingById (?,?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		cstmt.setLong(1, id);
		cstmt.setString(2, booking.getStatus());
		ResultSet rs = cstmt.executeQuery();
		logger.info(" booking updated successfully");
		return true;
	}

	@Override
	public long getTotalBooking() throws Exception {
		long count = 0;
		logger.info("get Total bookings" );
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call bookingCount()}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		ResultSet rs = cstmt.executeQuery();
		while(rs.next())
		{
			count =	rs.getLong(1);
		}
		return count;
	}

	@Override
	public long getTotalCancellation() throws Exception {
		long count = 0;
		logger.info("get Total booking Cancellation" );
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call cancelledCount()}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		ResultSet rs = cstmt.executeQuery();
		while(rs.next())
		{
			count =	rs.getLong(1);
		}
		return count;
	}

	@Override
	public List<Booking> getBookingByTrainNameAndStatus(String status, Timestamp bookingDate, String trainName, String sourceStation) throws Exception {
		logger.info("get Total booking by train Name and status" );
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call getBookingByTrainNameAndStatus(?,?,?,?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		cstmt.setString(1,status);
		cstmt.setTimestamp(2,bookingDate);
		cstmt.setString(3,trainName);
		cstmt.setString(4,sourceStation);
		ResultSet rs = cstmt.executeQuery();
		List<Booking> bookingList = rowMapper(rs);
		if(bookingList != null && bookingList.size() != 0){
			logger.info("get booking list found by train name and status" );
			return  bookingList;
		}else{
			logger.info("get booking list not found by train name and status" );
			return null;
		}
	}

	@Override
	public boolean updateBookingById(long id, String arrivalTime) throws Exception {
		logger.info("update arrival time");
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
		Connection con = dbConfig.getConnection();
		String SQL = "{call updateBookingArrivalTime (?,?)}";
		CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		cstmt.setLong(1, id);
		cstmt.setString(2, arrivalTime);
		ResultSet rs = cstmt.executeQuery();
		logger.info(" booking updated successfully");
		return true;
	}
}
