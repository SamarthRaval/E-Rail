//Author : Varun
package com.erail.controller;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.Utility.Utility;
import com.erail.models.*;
import com.erail.sendMail.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class TrainStationController {

    private static final Logger logger = LogManager.getLogger(TrainStationController.class);


    private IServiceFactory iServiceFactory;
    TrainStationController(){
        iServiceFactory = new ServiceFatory();
    }

    @RequestMapping(value = "/getTrainStationByTrainId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTrainStationByTrainId(@RequestParam("trainId") long trainId) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            logger.info("get trains station by trainId");
            response.put("data", iServiceFactory.createTrainStationService().getTrainStationMappingByTrainId(trainId));
            response.put("status", 200);
            logger.info(" trains station by trainId fetched successfully");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(" error in getting trains station  by Id " + ex.getLocalizedMessage() );
            response.put("Error", "Booking failed");
            return response;
        }
    }

    @RequestMapping(value = "/saveTrainStationMapping", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveTrainStationMapping(@RequestBody List<TrainStationMapping> trainStationList) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            logger.info("get trains station by trainId");
            containsSameTime(trainStationList);
            if(  containsSameTime(trainStationList)){
                for (int i = 0; i < trainStationList.size(); i++) {
                    List<TrainStationMapping> tsmList = iServiceFactory.createTrainStationService().getTrainStationMappingByTrainId(trainStationList.get(i).getTrainId());
                    for (int j = 0; j < tsmList.size(); j++) {
                        iServiceFactory.createTrainStationService().deleteTrainStationMapping(tsmList.get(j).getId());
                    }
                }
                for (int i = 0; i < trainStationList.size(); i++) {
                    TrainStationMapping tsm = trainStationList.get(i);
                    if (Utility.validateTime(tsm.getTime())) {
                        Train train = iServiceFactory.createTrainService().findTrainById(tsm.getTrainId());
                        Station station = iServiceFactory.createStationService().getStationById(tsm.getStationId().getId());
                        List<Booking> bookingList = iServiceFactory.createBookingService().getBookingByTrainNameAndStatus("Booked", new Timestamp(new Date().getTime()), train.getTrainName(), station.getStationName());
                        iServiceFactory.createTrainStationService().saveTrainStationMapping(tsm);
                       if(bookingList != null){
                           for (Booking book : bookingList) {
                               if (book.getArrivalTime() != tsm.getTime()) {
                                   User user = iServiceFactory.createUserService().findUserById(book.getUserId());
                                   String emailBody = "The timing of train <b> : " + book.getTrainName() + " </b> has been updated. The updated time of arrival is "+ tsm.getTime() +" <br> Your train booking number is  " + book.getBookingNumber()
                                           +". Source Station is " + book.getSourceStationName();
                                   iServiceFactory.createBookingService().updateBookingById(book.getId(),tsm.getTime());
                                   SendMail.getInstance().sendMail(user.getUsername(), "Train Time changed", emailBody);
                               }
                           }
                       }

                        response.put("data", true);
                        response.put("status", 200);
                        logger.info(" trains station added successfully");
                    }else{
                        response.put("data", " Invalid time");
                        response.put("status", 2001);
                        logger.info(" Invalid time ");
                    }
                }

            }else{
                response.put("status" , 1001);
                response.put("data","Duplicate arrival time for stations");
            }
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(" error in getting trains station  by Id " + ex.getLocalizedMessage() );
            response.put("Error", "Booking failed");
            return response;
        }
    }

    private  boolean containsSameTime(Collection<TrainStationMapping> c) {
        for (TrainStationMapping object : c) {
            for (TrainStationMapping obj : c) {
                if (object != null && obj != null && !object.equals(obj)&& object.getTime().equals(obj.getTime())) {
                    return false;
                }
            }
        }
        return true;
    }
}
