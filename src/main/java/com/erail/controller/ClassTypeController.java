//Author:Varun
package com.erail.controller;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.models.ClassTrain;
import com.erail.service.IClassTrainService;
import com.erail.service.Impl.ClassTrainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ClassTypeController {

    private static final Logger logger = LogManager.getLogger(ClassTypeController.class);

   private IServiceFactory iServiceFactory;
    ClassTypeController(){
         iServiceFactory = new ServiceFatory();
    }

    @RequestMapping(value = "/saveClassType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveClassType(@RequestBody ClassTrain classTrain) {
        Map<String, Object> response = new HashMap<>();
        try {
            logger.info("save new classType");
            ClassTrain classTrain1 = iServiceFactory.createClassTrainService().getClassTrainByType(classTrain.getType());
            if(classTrain1 == null){
                response.put("data", iServiceFactory.createClassTrainService().save(classTrain));
                response.put("status", 200);
                logger.info("saved successfully");
            }else{
                response.put("status" , 2001);
                response.put("data", "Class already exists");
                logger.info("class already exists");
            }
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error in saving new class type " + ex.getLocalizedMessage() );
            response.put("Message", "saving failed");
            return response;
        }
    }

    @RequestMapping(value = "/updateClassType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateClassType(@RequestBody ClassTrain classTrain) {
        Map<String, Object> response = new HashMap<>();
        try {
            logger.info("update new classType");
            ClassTrain classTrain1 = iServiceFactory.createClassTrainService().getClassTrainByType(classTrain.getType());
            if(classTrain1.getId() == classTrain.getId()) {
                response.put("data", iServiceFactory.createClassTrainService().update(classTrain));
                response.put("status", 200);
                logger.info("updated successfully");
            }else{
                response.put("status" , 2001);
                response.put("data", "Class already exists");
                logger.info("class already exists");
            }
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error in updating new class type " + ex.getLocalizedMessage() );
            response.put("Message", "update failed");
            return response;
        }
    }


    @RequestMapping(value = "/getAllClassType", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> saveClassType() {
        Map<String, Object> response = new HashMap<>();
        try {
            logger.info("get all classType");
            response.put("data", iServiceFactory.createClassTrainService().getAll());
            response.put("status", 200);
            logger.info("getting all class type successfully");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error in getting all class type " + ex.getLocalizedMessage() );
            response.put("Message", "fetching class type unsuccessful");
            return response;
        }
    }


    @RequestMapping(value = "/getClassTypeByType", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> saveClassType(@RequestParam(value = "type") String type) {
        Map<String, Object> response = new HashMap<>();
        try {
            logger.info("get classType by type");
            response.put("data", iServiceFactory.createClassTrainService().getClassTrainByType(type));
            response.put("status", 200);
            logger.info("getting class type successfully");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error in getting class type " + ex.getLocalizedMessage() );
            response.put("Message", "fetching class type unsuccessful");
            return response;
        }
    }


    @RequestMapping(value = "/deleteClassType", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteClassType(@RequestParam(value = "id") long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            logger.info("delete classType by id");
            response.put("data", iServiceFactory.createClassTrainService().deleteClassTrain(id));
            response.put("status", 200);
            logger.info("deleted class type successfully");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error in deleting class type " + ex.getLocalizedMessage() );
            response.put("Message", "deleting class type unsuccessful");
            return response;
        }
    }


}
