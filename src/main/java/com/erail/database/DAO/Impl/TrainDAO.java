//Author: Dhruvi
package com.erail.database.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.models.Station;
import com.erail.models.TrainStationMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.erail.database.DatabaseConfig;
import com.erail.database.DAO.ITrainDAO;
import com.erail.models.Train;

@Component
public class TrainDAO implements ITrainDAO{

	private static final Logger logger = LogManager.getLogger(TrainDAO.class);

	public IDAOFactory idaoFactory;

	public TrainDAO(){
		idaoFactory = new DAOFactory();
	}

	@Override
	public long addTrain(Train train) throws Exception {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to insert Train");
			  String SQL = "{call createTrain (?,?)}";
			     CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			     cstmt.setLong(1, train.getTrainNumber());
			     cstmt.setString(2, train.getTrainName());
			     long id = cstmt.executeUpdate();
			     logger.info("Train Created successfully ");
		        return id;
	}

	@Override
	public long updateTrainDetail(long trainId, Train train) throws Exception  {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to Update Train");
			 String SQL = "{call updateTrain (?,?,?)}";
		        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		        cstmt.setLong(1, trainId);
		        cstmt.setLong(2, train.getTrainNumber());
		        cstmt.setString(3, train.getTrainName());
		        long id = cstmt.executeUpdate();
		        logger.info("Train updated successfully ");
		        return id;
	}

	@Override
	public boolean deleteTrainById(long trainId) throws Exception  {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			 String SQL = "{call deleteTrain (?)}";
		        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		        cstmt.setLong(1,trainId);
		        cstmt.executeQuery();
		        logger.info("Deleted successfully Train by id ");
		        return true;
	}

	@Override
	public Train findTrainByTrainName(String trainName) throws Exception {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to find distinct Train By TrainName " +trainName);
			String SQL = "{call getTrainByTrainName (?)}";
		    CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		    cstmt.setString(1, trainName);
		        ResultSet rs = cstmt.executeQuery();
		        List<Train> TrainList = rowMapper(rs);
		        if (TrainList.size() > 0) {
		            logger.info("Train found by trainName " +trainName);
		            return TrainList.get(0);
		        } else {
		            logger.info("Train not found by trainName" + trainName);
		            return null;
		        }
	}

	@Override
	public Train findTrainByTrainNumber(long trainNumber) throws Exception  {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to find distinct Train By TrainNumber " +trainNumber);
			String SQL = "{call getTrainByTrainNumber (?)}";
		    CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		    cstmt.setLong(1, trainNumber);
		        ResultSet rs = cstmt.executeQuery();
		        List<Train> TrainList = rowMapper(rs);
		        if (TrainList.size() > 0) {
		            logger.info("Train found by trainNumber " +trainNumber);
		            return TrainList.get(0);
		        } else {
		            logger.info("Train not found by trainNumber" + trainNumber);
		            return null;
		        }
	}

	//varun
	@Override
	public Map<String, Object> findTrainBetweenStation(long sourceStationId, long destinationStationId) throws Exception {
		Map<String,Object> response = new HashMap<>();
			List<Train> trainList = new ArrayList<Train>();
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to getTrainByStations");
			String SQL = "{call getTrainByStations (? , ?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setLong(1, sourceStationId);
			cstmt.setLong(2, destinationStationId);
			ResultSet trainSchedule = cstmt.executeQuery();
			List<TrainStationMapping> tsmList = new ArrayList<>();
			List<List<TrainStationMapping>> tsmListArray = new ArrayList<>();

			while(trainSchedule.next()){
				TrainStationMapping tsm = new TrainStationMapping();
				tsm.setId(trainSchedule.getLong(1));
				tsm.setTrainId(trainSchedule.getLong(2));
				tsm.setTime(trainSchedule.getString(4));
				Station station = idaoFactory.createStationDAO().getStationById(trainSchedule.getLong(3));
				tsm.setStationId(station);
				tsmList.add(tsm);
				TrainStationMapping tsm1 = new TrainStationMapping();
				tsm1.setId(trainSchedule.getLong(5));
				tsm1.setTrainId(trainSchedule.getLong(6));
				Station  station2 = idaoFactory.createStationDAO().getStationById(trainSchedule.getLong(7));
				tsm1.setStationId(station2);
				tsm1.setTime(trainSchedule.getString(8));
				tsmList.add(tsm1);
				tsmListArray.add(tsmList);
				Train train = findTrainById(trainSchedule.getLong("trainId"));
				train.setTrainStation(tsmList);
				trainList.add(train);
			}
			response.put("trainList",trainList);
			return response;
}

	@Override
	public Train findTrainById(long id) throws Exception  {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to distinct train by id");
			String SQL = "{call getTrainById (?)}";
		    CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		    cstmt.setLong(1, id);
		        ResultSet rs = cstmt.executeQuery();
		        List<Train> TrainList = rowMapper(rs);
		        if (TrainList.size() > 0) {
		            logger.info("Train found by id " +id);
		            return TrainList.get(0);
		        } else {
		            logger.info("Train not found by id" + id);
		            return null;
		        }
	}

	@Override
	public List<Train> getAllTrains() throws Exception {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to getAllTrains");
			String SQL = "{call getAllTrains ()}";
			CallableStatement cstmt =  con.prepareCall(SQL);
			ResultSet result = cstmt.executeQuery();
		    List<Train> TrainList = rowMapper(result);
	        if (TrainList.size() > 0) {
	            logger.info("Trains found ");
	            return TrainList;
	        } else {
	            logger.info("Trains not found ");
	            return null;
	        }
	}
	
	  private List<Train> rowMapper(ResultSet rs) throws Exception {
	        List<Train> TrainList = new ArrayList<>();
	        while (rs.next()) {
	        	Train train = new Train();
	        	train.setId(rs.getLong("id"));
	        	train.setTrainNumber(rs.getLong("trainNumber"));
	        	train.setTrainName(rs.getString("trainName"));
				TrainList.add(train);
	        }
	        return TrainList;
	    }
	
}
