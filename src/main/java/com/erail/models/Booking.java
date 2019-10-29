//Author:Varun 
package com.erail.models;

import java.sql.Timestamp;

public class Booking {

	private long id;
	private Timestamp timestamp;
	private long bookingNumber;
	private String trainName;
	private String sourceStationName;
	private String destinationStationName;
	private String classType;
	private long userId;
	private String arrivalTime;
	private String reachTime;
	private Long fare;
	private String Status;
	private Timestamp bookingDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(long bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getSourceStationName() {
		return sourceStationName;
	}
	public void setSourceStationName(String sourceStationName) {
		this.sourceStationName = sourceStationName;
	}
	public String getDestinationStationName() {
		return destinationStationName;
	}
	public void setDestinationStationName(String destinationStationName) {
		this.destinationStationName = destinationStationName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getReachTime() {
		return reachTime;
	}
	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}

	public Long getFare() {
		return fare;
	}

	public void setFare(Long fare) {
		this.fare = fare;
	}

	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Booking{" +
				"id=" + id +
				", timestamp=" + timestamp +
				", bookingNumber=" + bookingNumber +
				", trainName='" + trainName + '\'' +
				", sourceStationName='" + sourceStationName + '\'' +
				", destinationStationName='" + destinationStationName + '\'' +
				", classType='" + classType + '\'' +
				", userId=" + userId +
				", arrivalTime='" + arrivalTime + '\'' +
				", reachTime='" + reachTime + '\'' +
				", Status='" + Status + '\'' +
				'}';
	}
}
