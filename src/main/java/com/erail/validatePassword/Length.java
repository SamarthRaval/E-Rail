package com.erail.validatePassword;

public class Length implements IRuleValidator {

	@Override
	public boolean isValid(String password, int ruleValue) {
		if (password.length() >= ruleValue) {
			return true;
		}
		return false;
	}

}
