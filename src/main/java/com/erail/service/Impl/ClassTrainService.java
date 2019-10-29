//Author:Varun
package com.erail.service.Impl;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.database.DAO.IClassTrainDAO;
import com.erail.database.DAO.Impl.ClassTrainDAO;
import com.erail.models.ClassTrain;
import com.erail.service.IClassTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTrainService implements IClassTrainService {

   private IDAOFactory idaoFactory;

   public ClassTrainService(){
       idaoFactory = new DAOFactory();
   }

    @Override
    public long save(ClassTrain classTrain) throws Exception {
        return idaoFactory.createClassTrainDAO().save(classTrain);
    }

    @Override
    public List<ClassTrain> getAll() throws  Exception {
        return idaoFactory.createClassTrainDAO().getAll();
    }

    @Override
    public ClassTrain getClassTrainByType(String type)  throws  Exception{
        return idaoFactory.createClassTrainDAO().getClassTrainByType(type);
    }

    @Override
    public boolean deleteClassTrain(long id) throws Exception {
        return idaoFactory.createClassTrainDAO().deleteClassTrain(id);
    }

    @Override
    public long update(ClassTrain classTrain)throws Exception {
        return idaoFactory.createClassTrainDAO().update(classTrain);
    }
}
