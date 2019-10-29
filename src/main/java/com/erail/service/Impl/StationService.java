//Author:Dhruvi
package com.erail.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.database.DAO.IStationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erail.models.Station;
import com.erail.service.IStationService;
@Service
public class StationService implements IStationService {

	private IDAOFactory idaoFactory;

	public StationService(){
		 idaoFactory = new DAOFactory();
	}

	@Override
	public List<Station> getAllStations() throws Exception{
			List<Station> stationList = new ArrayList<Station>();
			stationList = idaoFactory.createStationDAO().getAllStations();

			return stationList;
	}

	@Override
	public long addStation(Station station)throws Exception {
		long stationId = 0;
			stationId = idaoFactory.createStationDAO().addStation(station);
			return stationId;
	}

	@Override
	public long updateStationDetail(long stationId, Station station)throws Exception {
			stationId = idaoFactory.createStationDAO().updateStationDetail(stationId, station);
			return stationId;
	}

	@Override
	public boolean deleteStationById(long stationId) throws Exception {
			 return idaoFactory.createStationDAO().deleteStationById(stationId);
	}

	@Override
	public Station findStationByStationNumber(String stationNumber) throws Exception {
			Station station = idaoFactory.createStationDAO().findStationByStationNumber(stationNumber);
			return station;
	}

	@Override
	public Station findStationByStationName(String stationName) throws Exception {
			Station station =  idaoFactory.createStationDAO().findStationByStationName(stationName);
			return station;
	}

	@Override
	public Station getStationById(long id) throws Exception{
			Station station = idaoFactory.createStationDAO().getStationById(id);
			return station;
	}

	@Override
	public long stationCountBetweenStations(String sourceStation, String destinationStation)throws Exception {
			long count = idaoFactory.createStationDAO().stationCountBetweenStations(sourceStation,destinationStation);
			return count;
	}
}

