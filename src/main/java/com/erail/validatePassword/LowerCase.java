package com.erail.validatePassword;

public class LowerCase implements IRuleValidator {

	int lowerCase = 0;

	@Override
	public boolean isValid(String password, int ruleValue) {

		for (int k = 0; k < password.length(); k++) {
			if (Character.isLowerCase(password.charAt(k))) {
				lowerCase++;
			}
		}

		if (lowerCase >= ruleValue) {
			return true;
		}

		return false;
	}

}
