package com.erail.UnitTest.validatePassword;

import com.erail.validatePassword.UniqueUsername;

import junit.framework.TestCase;

public class UniqueUsernameTest extends TestCase {

	UniqueUsername uniqueUsername = null;

	public UniqueUsernameTest(String name) {
		super(name);
	}

	public UniqueUsername createInstance() throws Exception {
		return new UniqueUsername();
	}

	protected void setUp() throws Exception {
		super.setUp();
		uniqueUsername = createInstance();
	}

	protected void tearDown() throws Exception {
		uniqueUsername = null;
		super.tearDown();
	}

	public void testVault() throws Exception {

	}

	public void testNotUniqueUsername() throws Exception {
		assertEquals(false, uniqueUsername.uniqueUsername("samarth.v.raval@gmail.com"));
	}

	public void testUniqueUsername() throws Exception {
		assertEquals(true, uniqueUsername.uniqueUsername("s.v.raval@gmail.com"));
	}

	public void testValidUsername() throws Exception {
		assertEquals(true, uniqueUsername.validateUsername("samarth.v.raval@gmail.com"));
	}

	public void testInValidUsername1() throws Exception {
		assertEquals(false, uniqueUsername.validateUsername("samarth.v.raval"));
	}

	public void testInValidUsername2() throws Exception {
		assertEquals(false, uniqueUsername.validateUsername("samarth#gamil.com"));
	}

	public void testInValidUsername3() throws Exception {
		assertEquals(false, uniqueUsername.validateUsername("samarth@gamil"));
	}
	
	public void testInValidUsername4() throws Exception {
		assertEquals(false, uniqueUsername.validateUsername("samarth#$%@gamil@yahoo.com"));
	}

	public void testValidUsername2() throws Exception {
		assertEquals(true, uniqueUsername.validateUsername("1@yahoo.com"));
	}

	public void testValidUsername3() throws Exception {
		assertEquals(true, uniqueUsername.validateUsername("sam@1.com"));
	}

	public static void main(String[] args) {

		junit.textui.TestRunner.run(UniqueUsernameTest.class);

	}
}
