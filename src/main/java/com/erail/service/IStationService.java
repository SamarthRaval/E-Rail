

package com.erail.service;

import java.util.List;

import com.erail.models.Station;


public interface IStationService {

	
	public long addStation(Station station)throws Exception;
	public long updateStationDetail(long stationId,Station station)throws Exception;
	public boolean deleteStationById(long stationId) throws Exception;
	public Station findStationByStationNumber(String stationNumber) throws Exception;
	public Station findStationByStationName(String stationName) throws Exception;
	public List<Station> getAllStations() throws Exception;
	public Station getStationById(long id) throws Exception;
	public long stationCountBetweenStations (String sourceStation, String destinationStation) throws Exception;

}

