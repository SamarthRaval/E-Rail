//Author:Dhruvi
package com.erail.models;

import java.util.List;

public class Train {
	
	private long id;
	private long trainNumber;
	private String trainName;
	private List<TrainStationMapping> trainStation;
	
	
	
	public Train() {
		super();
	}
	public Train(long id, long trainNumber, String trainName) {
		super();
		this.id = id;
		this.trainNumber = trainNumber;
		this.trainName = trainName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(long trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public List<TrainStationMapping> getTrainStation() {
		return trainStation;
	}
	public void setTrainStation(List<TrainStationMapping> trainStation) {
		this.trainStation = trainStation;
	}
	
	
}
