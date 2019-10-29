//Author:Dhruvi
package com.erail.mockDAO;

import java.util.ArrayList;
import java.util.List;

import com.erail.database.DAO.IStationDAO;
import com.erail.models.Station;


public class MockStationDAO implements IStationDAO
{
List<Station> stationList = new ArrayList<>();
    
	public MockStationDAO() throws Exception
	{
		generateStationList();
	}
	
	@Override
	public long addStation(Station station) throws Exception {
		stationList.add(station);
		return stationList.size();
	}

	@Override
	public long updateStationDetail(long stationId, Station station) throws Exception {
		 for (Station stationAdded: stationList) {
	            if (stationAdded.getId() == stationId){
	            	stationAdded.setId(station.getId());
	            	stationAdded.setStationName(station.getStationName());
	            	stationAdded.setStationNumber(station.getStationNumber());
	            	stationList.set(stationList.indexOf(stationAdded), stationAdded);
	            return stationId;
	            }
	            }
		  return 0;
	}

	@Override
	public boolean deleteStationById(long stationId) throws Exception {
		    for (Station station: stationList) {
	            if (station.getId() == stationId){
	            	stationList.remove(station);
	                return true;
	            }
	        }
		  return false;
	}

	@Override
	public Station findStationByStationNumber(String stationNumber) throws Exception {
		  for (Station station: stationList) {
	            if (station.getStationNumber() == stationNumber){
	                return station;
	            }
	        }
		  return null;
	}

	@Override
	public Station findStationByStationName(String stationName) throws Exception {
		  for (Station station: stationList) {
	            if (station.getStationName() == stationName){
	                return station;
	            }
	        }
		  return null;
	}

	@Override
	public List<Station> getAllStations() throws Exception{
		return stationList;
	}

	@Override
	public Station getStationById(long id) throws Exception {
		  for (Station station: stationList) {
	            if (station.getId() == id){
	                return station;
	            }
	        }
		  return null;
	}

	//varun
	@Override
	public long stationCountBetweenStations(String sourceStation, String destinationStation) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void generateStationList() throws Exception
	{
		Station station = new Station();
		station.setId(1);
		station.setStationName("Halifax");
		station.setStationNumber("001");
		stationList.add(station);
	}

}
