
package com.erail.service;

import java.util.List;
import java.util.Map;

import com.erail.models.Station;
import com.erail.models.Train;
import com.erail.models.TrainStationMapping;

public interface ITrainService {

	public long addTrain(Train train) throws Exception;

	public long updateTrainDetail(long trainId, Train train) throws Exception;

	public boolean deleteTrainById(long trainId) throws Exception;

	public Train findTrainByTrainNumber(long trainNumber) throws Exception;

	public Train findTrainByTrainName(String trainName) throws Exception;
	
	public List<Train> getAllTrain() throws Exception;
	//public long addTrainStationMapping(long trainId , Station station , String ArrivalTime );
	
	public Map<String,Object> findTrainBetweenStation(long sourceStationId, long destinationStationId) throws Exception;

	public Train findTrainById(long trainId) throws Exception;


}

