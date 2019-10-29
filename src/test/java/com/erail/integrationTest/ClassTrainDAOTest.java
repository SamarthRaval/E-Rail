//Author:Varun
package com.erail.integrationTest;

import com.erail.database.DAO.IClassTrainDAO;
import com.erail.mockDAO.MockClassTrainDAO;
import com.erail.models.ClassTrain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassTrainDAOTest {

   private IClassTrainDAO iClassTrainDAO = null;

    @Before
    public void init() {
         iClassTrainDAO = new MockClassTrainDAO();
    }

    @Test
    public void save() throws  Exception{
        ClassTrain classTrain = new ClassTrain();
        classTrain.setId(3);
        classTrain.setType("Executive");
        classTrain.setFare(50);
        Assert.assertEquals(3,iClassTrainDAO.save(classTrain));
    }

    @Test
    public void getAll() throws  Exception{
        Assert.assertNotNull(iClassTrainDAO.getAll());
    }

    @Test
    public void getClassTrainByType() throws  Exception{
        Assert.assertNotNull(iClassTrainDAO.getClassTrainByType("economy"));
    }

@Test
    public void deleteClassTrain()throws  Exception{
    Assert.assertTrue(iClassTrainDAO.deleteClassTrain(1));
    }

}
