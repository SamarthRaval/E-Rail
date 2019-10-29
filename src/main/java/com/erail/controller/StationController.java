//Author :Dhruvi Shah
package com.erail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.models.Station;

@Controller
public class StationController {

	private IServiceFactory iServiceFactory;

	StationController(){
		iServiceFactory = new ServiceFatory();
	}
	
	
	private static final Logger logger = LogManager.getLogger(StationController.class);

	@RequestMapping(value = "/getStationList" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getStationList() {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("getting list of Stations");
			List<Station> stationList = iServiceFactory.createStationService().getAllStations();
			if(stationList == null)
			{
				response.put("Message", "Stations not found");
				logger.info("No Stations found");
			}
			else
			{
				response.put("status", 200);
				response.put("data", stationList);
				response.put("Message", "Successful");
				logger.info(stationList.size()+" Stations found ");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Stations not found");
			logger.error("Error in fetching all stations " + ex.getLocalizedMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/addStation" , method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addStation(@RequestBody Station station) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			if(iServiceFactory.createStationService().findStationByStationName(station.getStationName()) == null &&
					iServiceFactory.createStationService().findStationByStationNumber(station.getStationNumber()) == null)
			{
				logger.info("Adding Station");
				long StationId =  iServiceFactory.createStationService().addStation(station);
				if(StationId == 0)
				{
					response.put("Message", "Station not Added");
					logger.info("Some Error in Adding Station");
				}
				else
				{
					response.put("status", 200);
					response.put("Message","Added Successfully");
					logger.info("Station Adding Sucessfully");
				}
			}
			else
			{
				response.put("Message", "Data already Exist");
				logger.info("Data already Exist for given Station Details");	
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Station not Added");
			logger.error("Error in Adding Station " + ex.getLocalizedMessage());
		}
		return response;
	}
	
	
	@RequestMapping(value = "/updateStationDetails" , method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateStationDetails(@RequestParam("stationId") long stationId,@RequestBody Station station) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			if(iServiceFactory.createStationService().findStationByStationName(station.getStationName()) == null ||
					iServiceFactory.createStationService().findStationByStationNumber(station.getStationNumber()) == null)
			{
				logger.info("Updating Station Details");
				long StationDetail=  iServiceFactory.createStationService().updateStationDetail(stationId, station);
				if(StationDetail == 0)
				{
					response.put("Message", "Station not Updated");
					logger.info("Error in Updating Station Details");
				}
				else
				{
					response.put("status", 200);
					response.put("data_id", StationDetail);
					response.put("Message", "Updated Successfully");
					logger.info("Updating Station Details for station " + station.getStationName()  );
				}
			}
			else
			{
				response.put("Message", "Data already Exist");
				logger.info("Data already Exist for given Station Details");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Station not Updated");
			logger.error("Error in Updating Station details " +  ex.getLocalizedMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/deleteStationbyId" , method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteStationbyId(@RequestParam("stationId") long stationId) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("Deleting Station by StationId  " +stationId);
			boolean StationDetail = iServiceFactory.createStationService().deleteStationById(stationId);
			if(StationDetail == false)
			{
				response.put("Message", "Station not deleted");
				logger.info("Could not delete Station by StationId  " +stationId);
			}
			else
			{
				response.put("status", 200);
				response.put("Message","delete Successfully");
				logger.info("Deleted Station by StationId  " +stationId);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Station not deleted");
			logger.error("Error in deleting Station by StationId "+ stationId +" -- "+ ex.getLocalizedMessage());
		}
		return response;
	}
	
	
	@RequestMapping(value = "/findStationByStationNumber" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> findStationByStationNumber(@RequestParam("stationNumber") String stationNumber) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("Finding Station by StationNumber " + stationNumber);
			Station stationDetails = iServiceFactory.createStationService().findStationByStationNumber(stationNumber);
			if(stationDetails == null)
			{
				response.put("Message", "Station not found");
				logger.info("Could not find Station for StationNumber " + stationNumber);
			}
			else
			{
				response.put("status", 200);
				response.put("data",stationDetails);
				response.put("Message ","Data found");
				logger.info("Found Station for StationNumber " + stationNumber);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Station not found");
			logger.error("Error in finding Station for StationNumber " +stationNumber + " -- " + ex.getLocalizedMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/findStationByStationName" , method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> findStationByStationName(@RequestParam("stationName") String stationName) {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			logger.info("Finding Station by StationName " + stationName);
			Station stationDetails = iServiceFactory.createStationService().findStationByStationName(stationName);
			if(stationDetails == null)
			{
				response.put("Message", "Station not found");
				logger.info("Could not find Station for StationName " + stationName);
			}
			else
			{
				response.put("status", 200);
				response.put("data",stationDetails);
				response.put("Message ","Data found");
				logger.info("Found Station for StationName " + stationName);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Station not found");
			logger.error("Error in finding Station for StationName " +stationName + " -- " + ex.getLocalizedMessage());
		}
		return response;
	}
}

