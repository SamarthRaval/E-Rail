package com.erail.validatePassword;

public class Digits implements IRuleValidator {

	@Override
	public boolean isValid(String password, int ruleValue) {
		
		int count_digits = password.replaceAll("\\D", "").length();
		if (count_digits >= ruleValue) {
			return true;
		}
		return false;
	}

}
