package com.erail.UnitTest.models;

import com.erail.models.Train;

import junit.framework.TestCase;

public class TrainTest extends TestCase{
	
	Train train = null;
	
	public TrainTest(String name) {
		super(name);
	}

	public Train createInstance() throws Exception {
		return new Train();
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		train = createInstance();
	}
	
	
	protected void tearDown() throws Exception {
		train = null;
		super.tearDown();
	}
	
	
	public void testSetGetTrainNumber() throws Exception {
	
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			train.setTrainNumber(tests[i]);
			assertEquals(tests[i], train.getTrainNumber());
		}

	}
	
	public void testSetGetTrainId() throws Exception {
		
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			train.setId(tests[i]);
			assertEquals(tests[i], train.getId());
		}

	}
	
	public void testSetGetTrainName() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			train.setTrainName(tests[i]);
			assertEquals(tests[i], train.getTrainName());
		}
	}
	
	public void testVault() throws Exception {
	
	}

	public static void main(String[] args) {
		
		junit.textui.TestRunner.run(TrainTest.class);
	
	}
}

