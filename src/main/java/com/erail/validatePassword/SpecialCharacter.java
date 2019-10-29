package com.erail.validatePassword;

public class SpecialCharacter implements IRuleValidator {

	@Override
	public boolean isValid(String password, int ruleValue) {
		String SPECIAL_CHARS_REGEX = "[!@#$%^&*()\\[\\]|;',./{}\\\\:\"<>?]";
		int specials = password.split(SPECIAL_CHARS_REGEX, -1).length - 1;
		if (specials >= ruleValue) {
			return true;
		}
		return false;
	}

}
