//Author:Dhruvi
package com.erail.integrationTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erail.database.DAO.IStationDAO;
import com.erail.mockDAO.MockStationDAO;
import com.erail.models.Station;

@SpringBootTest(classes = com.erail.Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class StationDAOTest{
	
	@Test
	public void addStation() throws Exception
	{
		IStationDAO stationDAO = new MockStationDAO();
		Station station = new Station();
		station.setId(1);
		station.setStationName("Halifax-Test");
		station.setStationNumber("002");
		stationDAO.addStation(station);
	}

	public void deleteStationById() throws Exception
	{ 
		IStationDAO stationDAO = new MockStationDAO();
		Assert.assertTrue(stationDAO.deleteStationById(1));
	}

	@Test
	public void findStationByStationNumber() throws Exception
	{
		IStationDAO stationDAO = new MockStationDAO();
		   Assert.assertNotNull(stationDAO.findStationByStationNumber("001"));
	}

	@Test
	public void findStationByStationName() throws Exception
	{
		IStationDAO stationDAO = new MockStationDAO();
		 Assert.assertNotNull(stationDAO.findStationByStationName("Halifax"));
	}

	@Test
	public void  getAllStations() throws Exception
	{
		IStationDAO stationDAO = new MockStationDAO();
	    Assert.assertNotNull(stationDAO.getAllStations());
	}

	@Test
	public void getStationById() throws Exception
	{
		IStationDAO stationDAO = new MockStationDAO();
		 Assert.assertNotNull(stationDAO.getStationById(1));
	}


}
