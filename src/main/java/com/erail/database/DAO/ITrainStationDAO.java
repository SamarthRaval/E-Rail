//Author:Varun
package com.erail.database.DAO;

import com.erail.models.TrainStationMapping;

import java.util.List;

public interface ITrainStationDAO {

    public List<TrainStationMapping> getTrainStationMappingByTrainId(long trainId)throws  Exception;
    public TrainStationMapping getTrainStationMappingByTrainIdAndStationId(long trainId,long stationId) throws Exception;
    public boolean saveTrainStationMapping(TrainStationMapping tsm) throws Exception;
    public boolean deleteTrainStationMapping(long id) throws Exception;
}
