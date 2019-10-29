//Author:Dhruvi
package com.erail.service.Impl;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.database.DAO.IStationDAO;
import com.erail.database.DAO.ITrainDAO;
import com.erail.models.Train;
import com.erail.service.IStationService;
import com.erail.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrainService implements ITrainService {
	
	private IDAOFactory idaoFactory;

	public TrainService(){
		 idaoFactory = new DAOFactory();
	}

	@Override
	public long addTrain(Train train) throws Exception{
			return idaoFactory.createTrainDAO().addTrain(train); 
	}

	@Override
	public long updateTrainDetail(long trainId, Train train) throws Exception {
			trainId = idaoFactory.createTrainDAO().updateTrainDetail(trainId, train);
			return trainId;
	}


	@Override
	public Map<String,Object> findTrainBetweenStation(long sourceStationId, long destinationStationId) throws Exception {
		Map<String,Object> response = new HashMap<String, Object>();
			Map<String, Object> trainList = idaoFactory.createTrainDAO().findTrainBetweenStation(sourceStationId, destinationStationId);
			response.put("data",trainList);
			return  response;

	}

	@Override
	public Train findTrainById(long trainId) throws Exception {
		return idaoFactory.createTrainDAO().findTrainById(trainId);
	}

	@Override
	public boolean deleteTrainById(long trainId) throws Exception{
		return	idaoFactory.createTrainDAO().deleteTrainById(trainId);
	}

	@Override
	public Train findTrainByTrainNumber(long trainNumber) throws Exception{
			Train train = idaoFactory.createTrainDAO().findTrainByTrainNumber(trainNumber);
			return train;
	}

	@Override
	public Train findTrainByTrainName(String trainName) throws Exception{
			Train train = idaoFactory.createTrainDAO().findTrainByTrainName(trainName);
			return train;
	}

//	@Override
//	public long addTrainStationMapping(long trainId, Station station, String ArrivalTime) {
//		try {
//		return query.addTrainStationMapping(trainId, station,ArrivalTime);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return 0;
//		}
//	}

	@Override
	public List<Train> getAllTrain() throws Exception{
			List<Train> trainList = new ArrayList<>();
				trainList = idaoFactory.createTrainDAO().getAllTrains();
			return trainList;
	}
	
}

