//Author:Samarth
package com.erail.mockDAO;

import java.util.ArrayList;
import java.util.List;

import com.erail.database.DAO.IPasswordValueDAO;
import com.erail.models.PasswordRule;

public class MockPasswordValueDAO implements IPasswordValueDAO {

	List<PasswordRule> rules = new ArrayList<PasswordRule>();

	public MockPasswordValueDAO() {
		generatePasswordValues();
	}

	@Override
	public List<PasswordRule> getPasswordInfo() {

		return rules;
	}

	public void generatePasswordValues() {

		PasswordRule passwordRule = new PasswordRule();
		passwordRule.setRuleName("minLength");
		passwordRule.setRuleValue(10);
		passwordRule.setStatus(1);
		rules.add(passwordRule);

		PasswordRule passwordRule1 = new PasswordRule();
		passwordRule1.setRuleName("minNoOfDigits");
		passwordRule1.setRuleValue(2);
		passwordRule1.setStatus(1);
		rules.add(passwordRule1);

	}

}
