package com.erail.service;

import com.erail.models.ClassTrain;

import java.util.List;

public interface IClassTrainService {


    public long save(ClassTrain classTrain) throws  Exception;
    public List<ClassTrain> getAll() throws Exception;
    public ClassTrain getClassTrainByType(String type) throws Exception;
    public boolean deleteClassTrain(long id) throws Exception;
    public long update(ClassTrain classTrain) throws  Exception;

}
