package com.erail.UnitTest.models;

import com.erail.models.PasswordRule;

import junit.framework.TestCase;

public class PasswordRuleTest extends TestCase {

	PasswordRule passwordRule = null;

	public PasswordRuleTest(String name) {
		super(name);
	}

	public PasswordRule createInstance() throws Exception {
		return new PasswordRule();
	}

	protected void setUp() throws Exception {
		super.setUp();
		passwordRule = createInstance();
	}

	protected void tearDown() throws Exception {
		passwordRule = null;
		super.tearDown();
	}

	public void testSetGetRuleName() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			passwordRule.setRuleName(tests[i]);
			assertEquals(tests[i], passwordRule.getRuleName());
		}
	}

	public void testSetGetRuleDes() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			passwordRule.setRuleDes(tests[i]);
			assertEquals(tests[i], passwordRule.getRuleDes());
		}
	}

	public void testSetGetRuleValue() throws Exception {

		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			passwordRule.setRuleValue(tests[i]);
			assertEquals(tests[i], passwordRule.getRuleValue());
		}
	}

	public void testSetGetStatus() throws Exception {

		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			passwordRule.setStatus(tests[i]);
			assertEquals(tests[i], passwordRule.getStatus());
		}
	}

	public void testVault() throws Exception {

	}

	public static void main(String[] args) {

		junit.textui.TestRunner.run(PasswordRuleTest.class);

	}

}
