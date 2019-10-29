//Author:Dhruvi
package com.erail.database.DAO;

import com.erail.models.Train;

import java.util.List;
import java.util.Map;

public interface ITrainDAO {

	public long addTrain(Train train) throws Exception;
	
	public long updateTrainDetail(long trainId, Train train)throws Exception;

	public boolean deleteTrainById(long trainId)throws Exception;
	
	public Train findTrainByTrainName(String trainName) throws Exception;
	
	public Train findTrainByTrainNumber(long trainNumber) throws Exception;

	public Map<String,Object> findTrainBetweenStation(long sourceStationId, long destinationStationId) throws Exception;

	public Train findTrainById(long id) throws Exception;

	public List<Train> getAllTrains() throws Exception;
}
