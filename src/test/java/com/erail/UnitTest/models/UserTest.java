package com.erail.UnitTest.models;

import com.erail.models.User;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	User user = null;
	
	public UserTest(String name) {
		super(name);
	}

	public User createInstance() throws Exception {
		return new User();
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		user = createInstance();
	}
	
	
	protected void tearDown() throws Exception {
		user = null;
		super.tearDown();
	}
	
	
	public void testSetGetPhonenumber() throws Exception {
	
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			user.setPhone(tests[i]);
			assertEquals(tests[i], user.getPhone());
		}
	}
	

	
	public void testSetGetUsername() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setUsername(tests[i]);
			assertEquals(tests[i], user.getUsername());
		}
	}
	
	public void testSetGetPassword() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setPassword(tests[i]);
			assertEquals(tests[i], user.getPassword());
		}
	}

	public void testSetGetName() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setName(tests[i]);
			assertEquals(tests[i], user.getName());
		}
	}
	
	public void testSetGetGender() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setGender(tests[i]);
			assertEquals(tests[i], user.getGender());
		}
	}
	
	public void testSetGetDateofbirth() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setDateofbirth(tests[i]);
			assertEquals(tests[i], user.getDateofbirth());
		}
	}
	

	
	public void testSetGetStreetaddress() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setStreetaddress(tests[i]);
			assertEquals(tests[i], user.getStreetaddress());
		}
	}

	public void testSetGetCity() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setCity(tests[i]);
			assertEquals(tests[i], user.getCity());
		}
	}
	
	public void testSetGetProvince() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setProvince(tests[i]);
			assertEquals(tests[i], user.getProvince());
		}
	}
	
	public void testSetGetPostalcode() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			user.setPostalcode(tests[i]);
			assertEquals(tests[i], user.getPostalcode());
		}
	}


	
	public void testVault() throws Exception {
	
	}

	public static void main(String[] args) {
		
		junit.textui.TestRunner.run(UserTest.class);
	
	}


}
