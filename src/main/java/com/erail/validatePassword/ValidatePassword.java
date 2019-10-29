package com.erail.validatePassword;

import java.util.HashMap;
import java.util.List;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.models.PasswordRule;

public class ValidatePassword {

	private IServiceFactory iServiceFactory = new ServiceFatory();;
	HashMap<String, IRuleValidator> hMap = new HashMap<String, IRuleValidator>();

	public ValidatePassword() {
		hMap.put("minLength", new Length());
		hMap.put("minNoOfDigits", new Digits());
		hMap.put("minNoOfSpecialChar", new SpecialCharacter());
		hMap.put("minUpperCase", new UpperCase());
		hMap.put("minLowerCase", new LowerCase());
	}

	public String validateMyPassword(String password) throws Exception {

		List<PasswordRule> ruleList = iServiceFactory.createPasswordValueService().getPasswordInfo();
		IRuleValidator iRuleValidatore;

		for (int i = 0; i < ruleList.size(); i++) {

			PasswordRule rule = ruleList.get(i);

			if (rule.getStatus() == 1) {
				iRuleValidatore = hMap.get(rule.getRuleName());

				if (!iRuleValidatore.isValid(password, (int) rule.getRuleValue())) {
					return rule.getRuleDes() + " " + rule.getRuleValue();
				}
			}
		}
		return "success";
	}
}
