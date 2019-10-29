package com.erail.UnitTest.validatePassword;

import com.erail.validatePassword.ValidatePassword;

import junit.framework.TestCase;

public class ValidatePasswordTest extends TestCase {

	ValidatePassword valiPass = null;

	public ValidatePasswordTest(String name) {
		super(name);
	}

	public ValidatePassword createInstance() throws Exception {
		return new ValidatePassword();
	}

	protected void setUp() throws Exception {
		super.setUp();
		valiPass = createInstance();
	}

	protected void tearDown() throws Exception {
		valiPass = null;
		super.tearDown();
	}

	public void testSuccessfulPassword() throws Exception {
		String check = valiPass.validateMyPassword("SAmarth123!@#");
		assertEquals("success", check);
	}

	public void testMinSpecialCharPassword() throws Exception {
		String check = valiPass.validateMyPassword("Samarth123!");
		assertEquals("Minimum number of special character for password is 2", check);
	}

	public void testMinDigitsPassword() throws Exception {
		String check = valiPass.validateMyPassword("Samarth1!@#");
		assertEquals("Minimum number of digits for password is 2", check);
	}

	public void testMinNoOfUpperCase() throws Exception {
		String check = valiPass.validateMyPassword("Samarth123!@#");
		assertEquals("Minimum number of upper case character for password is 2", check);
	}

	public void testMinNoOfLowerCase() throws Exception {
		String check = valiPass.validateMyPassword("SAMARTH123!@#");
		assertEquals("Minimum number of lower case character for password is 2", check);
	}

	public void testVault() throws Exception {

	}

	public static void main(String[] args) {

		junit.textui.TestRunner.run(ValidatePasswordTest.class);

	}
}
