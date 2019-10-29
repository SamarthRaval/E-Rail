//Author:Varun
package com.erail.service.Impl;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.models.TrainStationMapping;
import com.erail.service.ITrainStationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainStationService implements ITrainStationService {

    private IDAOFactory idaoFactory;

    public TrainStationService(){
        idaoFactory = new DAOFactory();
    }

    @Override
    public List<TrainStationMapping> getTrainStationMappingByTrainId(long trainId) throws Exception {
        return idaoFactory.createTrainStationDAO().getTrainStationMappingByTrainId(trainId);
    }

    @Override
    public TrainStationMapping getTrainStationMappingByTrainIdAndStationId(long trainId, long stationId) throws Exception {
        return idaoFactory.createTrainStationDAO().getTrainStationMappingByTrainIdAndStationId(trainId,stationId);
    }

    @Override
    public boolean deleteTrainStationMapping(long id) throws Exception {
        return idaoFactory.createTrainStationDAO().deleteTrainStationMapping(id);
    }

    @Override
    public boolean saveTrainStationMapping(TrainStationMapping tsm) throws Exception {
        return idaoFactory.createTrainStationDAO().saveTrainStationMapping(tsm);
    }
}

