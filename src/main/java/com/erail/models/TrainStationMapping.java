//Author: Varun
package com.erail.models;

public class TrainStationMapping {

	private long id;
	private long trainId;
	private Station stationId;
	private String time;
	
	
	public TrainStationMapping() {
		super();
	}
	public TrainStationMapping(long id, long trainId, Station stationId, String time) {
		super();
		this.id = id;
		this.trainId = trainId;
		this.stationId = stationId;
		this.time = time;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTrainId() {
		return trainId;
	}
	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}
	public Station getStationId() {
		return stationId;
	}
	public void setStationId(Station stationId) {
		this.stationId = stationId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return " { \"id\":\"" + id + "\",\"trainId\":\"" + trainId + "\",\"stationId\":\"" + stationId
				+ "\",\"time\":\"" + time + "}";
	}
	
}
