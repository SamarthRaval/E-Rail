//Author: Varun
package com.erail.database.DAO.Impl;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.controller.BookingController;
import com.erail.database.DAO.ITrainStationDAO;
import com.erail.database.DatabaseConfig;
import com.erail.models.Station;
import com.erail.models.TrainStationMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrainStationDAO implements ITrainStationDAO {

	private static final Logger logger = LogManager.getLogger(BookingController.class);

    public IDAOFactory idaoFactory;


    public TrainStationDAO(){
        idaoFactory = new DAOFactory();
    }

    @Override
    public List<TrainStationMapping> getTrainStationMappingByTrainId(long trainId) throws Exception {
        List<TrainStationMapping> trainStationMappingList = new ArrayList<>();

            DatabaseConfig dbConfig = DatabaseConfig.getInstance();

            Connection con = dbConfig.getConnection();
            logger.info("Database call to getTrainStationMappingByTrainId for id " + trainId);
            String SQL = "{call getTrainStationMappingByTrainId (?)}";

            CallableStatement cstmt =  con.prepareCall(SQL);
            cstmt.setLong(1,trainId);
            ResultSet result = cstmt.executeQuery();
            TrainStationMapping tsm ;
            while (result.next()){
                tsm = new TrainStationMapping();
                tsm.setId(result.getLong("id"));
                Station station = idaoFactory.createStationDAO().getStationById(result.getLong("stationId"));
                tsm.setStationId(station);
                tsm.setTrainId(result.getLong("trainId"));
                tsm.setTime(result.getString("arrivalTime"));
                trainStationMappingList.add(tsm);
            }
            return  trainStationMappingList;
    }

    @Override
    public TrainStationMapping getTrainStationMappingByTrainIdAndStationId(long trainId, long stationId) throws Exception {

            DatabaseConfig dbConfig = DatabaseConfig.getInstance();
            Connection con = dbConfig.getConnection();
            String SQL = "{call getTrainStationMappingByTrainId (?,?)}";
            logger.info("Database call to getTrainStationMappingByTrainId for trainId,StationId " + trainId + " , " +stationId);
            CallableStatement cstmt = con.prepareCall(SQL);
            cstmt.setLong(1, trainId);
            cstmt.setLong(2, stationId);
            ResultSet result = cstmt.executeQuery();
            TrainStationMapping tsm = null;
            while (result.next()) {
                tsm = new TrainStationMapping();
                tsm.setId(result.getLong("id"));
                Station station = idaoFactory.createStationDAO().getStationById(result.getLong("stationId"));
                tsm.setStationId(station);
                tsm.setTrainId(result.getLong("trainId"));
                tsm.setTime(result.getString("arrivalTime"));
            }
            return tsm;
    }

    @Override
    public boolean saveTrainStationMapping(TrainStationMapping tsm) throws Exception{
            DatabaseConfig dbConfig = DatabaseConfig.getInstance();
            Connection con = dbConfig.getConnection();
            String SQL = "{call saveTrainStationMapping (?,?,?)}";
            logger.info("Database call to saveTrainStationMapping ");
            CallableStatement cstmt = con.prepareCall(SQL);
            cstmt.setLong(1, tsm.getTrainId());
            cstmt.setLong(2, tsm.getStationId().getId());
            cstmt.setString(3,tsm.getTime());
            cstmt.executeQuery();
            return true;

    }

    @Override
    public boolean deleteTrainStationMapping(long id) throws Exception{

            DatabaseConfig dbConfig = DatabaseConfig.getInstance();
            Connection con = dbConfig.getConnection();
            String SQL = "{call deleteTrainStationMappingById (?)}";
            logger.info("Database call to deleteTrainStationMappingById ");
            CallableStatement cstmt =  con.prepareCall(SQL);
            cstmt.setLong(1, id);
            cstmt.executeQuery();
            return true;
    }
}
