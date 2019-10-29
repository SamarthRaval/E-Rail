//Author:Varun
package com.erail.mockDAO;

import com.erail.database.DAO.IClassTrainDAO;

import com.erail.models.ClassTrain;

import java.util.ArrayList;
import java.util.List;

public class MockClassTrainDAO implements IClassTrainDAO {


    List<ClassTrain> classTrainList = new ArrayList<>();

    public MockClassTrainDAO () {
        generateClassTrainList();

    }

    private void generateClassTrainList(){

        ClassTrain classTrain = new ClassTrain();
        classTrain.setId(1);
        classTrain.setType("economy");
        classTrain.setFare(20);

        classTrainList.add(classTrain);
        classTrain = null;
        classTrain = new ClassTrain();
        classTrain.setId(2);
        classTrain.setType("business");
        classTrain.setFare(40);
        classTrainList.add(classTrain);

    }

    @Override
    public long save(ClassTrain classTrain) {
        classTrainList.add(classTrain);
        return classTrainList.size();
    }

    @Override
    public long update(ClassTrain classTrain) {
        for (ClassTrain classTrainObj: classTrainList) {
            if(classTrainObj.getId() == classTrain.getId()){
                classTrainObj.setFare(classTrain.getFare());
                classTrainObj.setType(classTrain.getType());
                classTrainList.add(classTrainList.indexOf(classTrainObj),classTrainObj);
            }
        }
        return classTrainList.size();
    }

    @Override
    public List<ClassTrain> getAll() {
        return classTrainList;
    }

    @Override
    public ClassTrain getClassTrainByType(String type) {
        for (ClassTrain classTrain: classTrainList ) {
            if (classTrain.getType().equals(type)){
                return classTrain;
            }
        }
        return null;
    }

    @Override
    public boolean deleteClassTrain(long id) {
        for (ClassTrain classTrain: classTrainList ) {
            if (classTrain.getId() == id){
               classTrainList.remove(classTrain);
               return true;
            }
        }
        return false;
    }
}
