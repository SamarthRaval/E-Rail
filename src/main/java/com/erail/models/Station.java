//Author:Dhruvi 
package com.erail.models;

public class Station {
	
	private long id;
	private String stationName;
	private String stationNumber;
	
	public Station() {
		super();
	}
	public Station(long id, String stationName, String stationNumber) {
		super();
		this.id = id;
		this.stationName = stationName;
		this.stationNumber = stationNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

}
