//Author:Dhruvi
package com.erail.integrationTest;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.erail.controller.StationController;

@SpringBootTest(classes = com.erail.Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebAppConfiguration	
public class StationControllerTest {


	@Autowired
	StationController stationController;
	
	@Test
	public void getStationListTest() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.stationController).build();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getStationList")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}
}
