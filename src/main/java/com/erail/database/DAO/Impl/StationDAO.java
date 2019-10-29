//Author: Dhruvi
package com.erail.database.DAO.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.erail.database.DatabaseConfig;
import com.erail.database.DAO.IStationDAO;
import com.erail.models.ClassTrain;
import com.erail.models.Station;

@Component
public class StationDAO implements IStationDAO{

	private static final Logger logger = LogManager.getLogger(StationDAO.class);
	
	@Override
	public long addStation(Station station) throws Exception{
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to insert Station");
			  String SQL = "{call createStation (?,?)}";
		     CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		     cstmt.setString(1, station.getStationNumber());
		     cstmt.setString(2, station.getStationName());
		     long id = cstmt.executeUpdate();
		     logger.info("Station Created successfully ");
	        return id;
	}

	@Override
	public long updateStationDetail(long stationId, Station station) throws Exception{
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to Update Station");
			  String SQL = "{call updateStation (?,?,?)}";
		        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		        cstmt.setLong(1, stationId);
		        cstmt.setString(2, station.getStationName());
		        cstmt.setString(3, station.getStationNumber());
		        long id = cstmt.executeUpdate();
		        logger.info("Station updated successfully ");
		        return id;
	}

	@Override
	public boolean deleteStationById(long stationId) throws Exception{
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to Delete Station");
			 String SQL = "{call deleteStation (?)}";
		        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		        cstmt.setLong(1,stationId);
		        cstmt.executeQuery();
		        logger.info("Deleted successfully Station by id ");
		        return true;
	}

	@Override
	public Station findStationByStationNumber(String stationNumber)throws Exception {
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to Find distinct Station by StationNumber " + stationNumber);
			String SQL = "{call getStationByStationNumber (?)}";
		    CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		    cstmt.setString(1, stationNumber);
		        ResultSet rs = cstmt.executeQuery();
		        List<Station> StationList = rowMapper(rs);
		        if (StationList.size() > 0) {
		            logger.info("Station found by stationNumber " +stationNumber);
		            return StationList.get(0);
		        } else {
		            logger.info("Station not found by stationNumber" + stationNumber);
		            return null;
		        }
	}

	@Override
	public Station findStationByStationName(String stationName) throws Exception {
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to Find distinct Station by stationName "+ stationName );
			String SQL = "{call getStationByStationName (?)}";
		    CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		    cstmt.setString(1, stationName);    
		    ResultSet rs = cstmt.executeQuery();
		        List<Station> StationList = rowMapper(rs);
		        if (StationList.size() > 0) {
		            logger.info("Station found by stationName " +stationName);
		            return StationList.get(0);
		        } else {
		            logger.info("Station not found by stationName" + stationName);
		            return null;
		        }
		 }

	@Override
	public List<Station> getAllStations() throws Exception {
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
				String SQL = "{call getAllStations()}";
				CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
				ResultSet rs = cstmt.executeQuery();
		        List<Station> StationList = rowMapper(rs);
		        if (StationList.size() > 0) {
		            logger.info("Stations found ");
		            return StationList;
		        } else {
		            logger.info("Station not found");
		            return null;
		        }
		}

	@Override
	public Station getStationById(long id) throws Exception{
		DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to Find Station by id "+ id);
			String SQL = "{call getStationById (?)}";
		    CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
		    cstmt.setLong(1, id);
		        ResultSet rs = cstmt.executeQuery();
		        List<Station> StationList = rowMapper(rs);
		        if (StationList.size() > 0) {
		            logger.info("Station found by id " +id);
		            return StationList.get(0);
		        } else {
		            logger.info("Station not found by id" + id);
		            return null;
		        }

	}

	@Override
	public long stationCountBetweenStations(String sourceStation, String destinationStation) throws Exception {
			DatabaseConfig dbConfig = DatabaseConfig.getInstance();
			Connection con = dbConfig.getConnection();
			logger.info("Database call to getStationCountBetweenStationByStationName");
			String SQL = "{call getStationCountBetweenStationByStationName (?,?)}";
			CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
			cstmt.setString(1,sourceStation);
			cstmt.setString(2,destinationStation);
			ResultSet resultSet = cstmt.executeQuery();
			while(resultSet.next()){
				long count = resultSet.getInt("countStation");
				return count;
			}
			return 0;
		}
	
	  private List<Station> rowMapper(ResultSet rs) throws Exception {
	        List<Station> StationList = new ArrayList<>();
	        while (rs.next()) {
	            Station station = new Station();
	            station.setId(rs.getLong("id"));
				station.setStationName(rs.getString("StationName"));
				station.setStationNumber(rs.getString("stationNumber"));
				StationList.add(station);
	        }
	        return StationList;
	    }
}
