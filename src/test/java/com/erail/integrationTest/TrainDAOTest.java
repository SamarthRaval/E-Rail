//Author:Dhruvi
package com.erail.integrationTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erail.database.DAO.ITrainDAO;
import com.erail.mockDAO.MockTrainDAO;
import com.erail.models.Train;

@SpringBootTest(classes = com.erail.Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TrainDAOTest
{
	@Test
	public void addTrain() throws Exception
	{
		ITrainDAO trainDAO = new MockTrainDAO();
		Train train = new Train();
		train.setId(1);
		train.setTrainName("Halifax Express Test");
		train.setTrainNumber(102938);
		trainDAO.addTrain(train);
	}
	
	
@Test
	public void deleteTrainById() throws Exception
	{
		ITrainDAO trainDAO = new MockTrainDAO();
	    Assert.assertTrue(trainDAO.deleteTrainById(1));
	}

	@Test
	public void findTrainByTrainName() throws Exception
	{
		ITrainDAO trainDAO = new MockTrainDAO();
		Assert.assertNotNull(trainDAO.findTrainByTrainName("Halifax Express Test"));
	}

	@Test
	public void findTrainByTrainNumber() throws Exception
	{
		ITrainDAO trainDAO = new MockTrainDAO();
		Assert.assertNotNull(trainDAO.findTrainByTrainNumber(102938));
	}

	@Test
	public void findTrainById() throws Exception
	{
		ITrainDAO trainDAO = new MockTrainDAO();
		Assert.assertNotNull(trainDAO.findTrainById(1));
	}

	@Test
	public void getAllTrains() throws Exception
	{
		ITrainDAO trainDAO = new MockTrainDAO();
		Assert.assertNotNull(trainDAO.getAllTrains());
	}
}
