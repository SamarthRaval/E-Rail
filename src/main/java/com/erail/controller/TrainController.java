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
import com.erail.models.Train;

@Controller
public class TrainController {

	private static final Logger logger = LogManager.getLogger(TrainController.class);

	private IServiceFactory iServiceFactory;

	TrainController() {
		iServiceFactory = new ServiceFatory();
	}

	@RequestMapping(value = "/getTrainsByStation", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getStationList(@RequestParam("sourceStation") long sourceStation,
			@RequestParam("destinationStation") long destinationStation) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info(
					"getting Trains by Stations for source" + sourceStation + " and destination " + destinationStation);
			Map<String, Object> trainList = iServiceFactory.createTrainService().findTrainBetweenStation(sourceStation,
					destinationStation);
			if (trainList == null) {
				response.put("Message", "No Train found");
				logger.info("No Trains found for Stations between source" + sourceStation + " and destination "
						+ destinationStation);

			} else {
				response.put("status", 200);
				response.put("data", trainList.get("data"));
				response.put("Message", "data found");
				logger.info("Trains found for Stations between source" + sourceStation + " and destination "
						+ destinationStation);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "No Train found");
			logger.error("Error in getting Train list by Station" + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/getTrainList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTrainList() {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("getting Train list");
			List<Train> trainList = iServiceFactory.createTrainService().getAllTrain();
			if (trainList == null) {
				response.put("Message", "No Trains found");
				logger.info("No Trains found");
			} else {
				response.put("status", 200);
				response.put("data", trainList);
				response.put("Message", "Successful");
				logger.info(trainList.size() + " Trains found");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "No Trains found");
			logger.error("Error in fetching all trains " + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/addTrain", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addTrain(@RequestBody Train train) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Adding Train by name " + train.getTrainName());
			if (iServiceFactory.createTrainService().findTrainByTrainName(train.getTrainName()) == null
					&& iServiceFactory.createTrainService().findTrainByTrainNumber(train.getTrainNumber()) == null) {
				long traindetail = iServiceFactory.createTrainService().addTrain(train);
				if (traindetail == 0) {
					response.put("Message", "Train not Added");
					logger.info("Could not add Train by name " + train.getTrainName());
				} else {
					response.put("status", 200);
					response.put("Message", "Added successfully");
					logger.info("Added Train by name " + train.getTrainName());
				}
			} else {
				response.put("Message", "Data already Exist");
				logger.info("Could not add Train it already Exists");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Train not Added");
			logger.error("error in Adding Train by name " + train.getTrainName() + "--" + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/updateTrainDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateTrainDetails(@RequestParam(value = "trainId", required = true) long trainId,
			@RequestBody Train train) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			if (iServiceFactory.createTrainService().findTrainByTrainName(train.getTrainName()) == null
					|| iServiceFactory.createTrainService().findTrainByTrainNumber(train.getTrainNumber()) == null) {
				logger.info("Updating Train by name" + train.getTrainName());
				long trainDetail = iServiceFactory.createTrainService().updateTrainDetail(trainId, train);
				if (trainDetail == 0) {
					response.put("Message", "Train not Updated");
					logger.info("Could not Update Train by name " + train.getTrainName());
				} else {
					response.put("status", 200);
					response.put("data_id", trainDetail);
					response.put("Message", "Updated successfully");
					logger.info("Updating Train by name " + train.getTrainName());
				}
			} else {
				response.put("Message", "Data already Exist");
				logger.info("Could not Update Train because duplicate data is entered");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Train not Updated");
			logger.error("Error in Updating Train by name " + train.getTrainName() + " -- " + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/deleteTrainbyId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteTrainbyId(@RequestParam("trainId") long trainId) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Deleting Train by Id" + trainId);
			boolean trainDetail = iServiceFactory.createTrainService().deleteTrainById(trainId);
			if (trainDetail == false) {
				response.put("Message", "Train not deleted");
				logger.info("Could not delete Train by Id" + trainId);
			} else {
				response.put("status", 200);
				response.put("Message", "delete Successfully");
				logger.info("Deleted Train by Id" + trainId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Train not deleted");
			logger.error("Error in Deleting Train by id " + trainId + " -- " + ex.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/findTrainByTrainNumber", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findTrainByTrainNumber(@RequestParam("trainNumber") long trainNumber) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Finding Train by TrainNumber " + trainNumber);
			Train trainDetails = iServiceFactory.createTrainService().findTrainByTrainNumber(trainNumber);
			if (trainDetails == null) {
				response.put("Message", "Train not found");
				logger.info("could not Find Train by TrainNumber " + trainNumber);
			} else {
				response.put("status", 200);
				response.put("data", trainDetails);
				response.put("Message", "Successful");
				logger.info("Found Train by TrainNumber " + trainNumber);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Train not found");
			logger.error("Error in fetching Train by TrainNumber " + trainNumber + " -- " + ex.getLocalizedMessage());
		}
		return response;

	}

	@RequestMapping(value = "/findTrainBytrainName", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findTrainByTrainNumber(@RequestParam("trainName") String trainName) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			logger.info("Finding Train by trainName " + trainName);
			Train trainDetails = iServiceFactory.createTrainService().findTrainByTrainName(trainName);
			if (trainDetails == null) {
				response.put("Message", "Train not found");
				logger.info("could not Find Train by trainName " + trainName);
			} else {
				response.put("status", 200);
				response.put("data", trainDetails);
				response.put("Message", "Successful");
				logger.info("could not Find Train by trainName " + trainName);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.put("Message", "Train not found");
			logger.error("Error in fetching Train by TrainName " + trainName + " -- " + ex.getLocalizedMessage());
		}
		return response;
	}
}
