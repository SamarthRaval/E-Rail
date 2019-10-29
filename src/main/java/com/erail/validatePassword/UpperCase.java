package com.erail.validatePassword;

public class UpperCase implements IRuleValidator {

	int upperCase = 0;

	@Override
	public boolean isValid(String password, int ruleValue) {

		for (int k = 0; k < password.length(); k++) {
			if (Character.isUpperCase(password.charAt(k))) {
				upperCase++;
			}
		}

		if (upperCase >= ruleValue) {
			return true;
		}

		return false;
	}
}
