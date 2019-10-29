//Author:Dhruvi
package com.erail.mockDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.erail.database.DAO.ITrainDAO;
import com.erail.models.Train;

public class MockTrainDAO implements ITrainDAO{

	
	List<Train> trainList = new ArrayList<>();
    
	public MockTrainDAO() throws Exception
	{
		generateTrainList();
	}
	
	@Override
	public long addTrain(Train train) throws Exception
	{
		trainList.add(train);
        return trainList.size();
	}

	@Override
	public long updateTrainDetail(long trainId, Train train) throws Exception {
		  for (Train trainAdded: trainList) {
	            if (trainAdded.getId() == trainId){
	            trainAdded.setId(train.getId());
	            trainAdded.setTrainName(train.getTrainName());
	            trainAdded.setTrainNumber(train.getTrainNumber());
	            trainList.set(trainList.indexOf(trainAdded), trainAdded);
	            return trainId;
	            }
	            }
		  return 0;
	}

	@Override
	public boolean deleteTrainById(long trainId) throws Exception{
		   for (Train train: trainList) {
	            if (train.getId() == trainId){
	            	trainList.remove(train);
	                return true;
	            }
	        }
	        return false;
	}

	@Override
	public Train findTrainByTrainName(String trainName) throws Exception {
	    for (Train train: trainList) {
	          if (train.getTrainName() == trainName){
	              return train;
	          }
	      }
	      return null;
	}

	@Override
	public Train findTrainByTrainNumber(long trainNumber)throws Exception {
		   for (Train train: trainList) {
		          if (train.getTrainNumber() == trainNumber){
		              return train;
		          }
		      }
		      return null;
	}

	//varun
	@Override
	public Map<String, Object> findTrainBetweenStation(long sourceStationId, long destinationStationId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Train findTrainById(long id) throws Exception {
		   for (Train train: trainList) {
		          if (train.getId() == id){
		              return train;
		          }
		      }
		      return null;
	}

	@Override
	public List<Train> getAllTrains() throws Exception {
		return trainList;
	}
	
	public void generateTrainList() throws Exception
	{
		Train train = new Train();
		train.setId(1);
		train.setTrainName("Halifax Express Test");
		train.setTrainNumber(102938);
		trainList.add(train);
	}
	

}
