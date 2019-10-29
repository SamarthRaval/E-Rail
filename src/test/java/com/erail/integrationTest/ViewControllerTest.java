//Author:Varun
package com.erail.integrationTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.erail.controller.ViewController;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewController.class)
public class ViewControllerTest {

	 @Autowired
	    private MockMvc mvc;
	 

	 @Test
	 public void trainTest()
	   throws Exception {
		 mvc.perform(get("/trains?source=1&destination=2")
	       .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(status().is3xxRedirection())
	       .andReturn();
		 
	 }
	 
	 @Test
	 public void paymentTest()
	   throws Exception {
		 mvc.perform(get("/payment")
	       .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(status().is3xxRedirection())
	       .andReturn();
		
	 }
	 
	 @Test
	 public void trainStationMapping()
	   throws Exception {
		 mvc.perform(get("/trainStationMapping")
	       .contentType(MediaType.APPLICATION_JSON))
	       .andExpect(status().is3xxRedirection())
	       .andReturn();
		
	 }
}
