package com.erail.UnitTest.models;

import com.erail.models.ClassTrain;

import junit.framework.TestCase;

public class ClassTrainTest extends TestCase {

	ClassTrain classTrain = null;

	public ClassTrainTest(String name) {
		super(name);
	}

	public ClassTrain createInstance() throws Exception {
		return new ClassTrain();
	}

	protected void setUp() throws Exception {
		super.setUp();
		classTrain = createInstance();
	}

	protected void tearDown() throws Exception {
		classTrain = null;
		super.tearDown();
	}

	public void testSetGetId() {
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			classTrain.setId(tests[i]);
			assertEquals(tests[i], classTrain.getId());
		}
	}

	public void testSetGetType() {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			classTrain.setType(tests[i]);
			assertEquals(tests[i], classTrain.getType());
		}
	}

	public void testSetGetFare() {
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			classTrain.setFare(tests[i]);
			assertEquals(tests[i], classTrain.getFare());
		}

	}

	public void testVault() throws Exception {

	}

	public static void main(String[] args) {

		junit.textui.TestRunner.run(ClassTrainTest.class);

	}

}
