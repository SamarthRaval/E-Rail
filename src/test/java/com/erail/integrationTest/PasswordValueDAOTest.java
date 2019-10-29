//Author:Samarth
package com.erail.integrationTest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erail.database.DAO.IPasswordValueDAO;
import com.erail.mockDAO.MockPasswordValueDAO;
import com.erail.models.PasswordRule;

@SpringBootTest(classes = com.erail.Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class PasswordValueDAOTest {

	@Test
	public void getPasswordInfo() throws Exception {
		IPasswordValueDAO passwordValueDAO = new MockPasswordValueDAO();
		List<PasswordRule> passRule = passwordValueDAO.getPasswordInfo();
		Assert.assertNotNull(passRule);
	}
}
