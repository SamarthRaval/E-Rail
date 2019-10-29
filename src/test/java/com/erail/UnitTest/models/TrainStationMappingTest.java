package com.erail.UnitTest.models;

import com.erail.models.TrainStationMapping;

import junit.framework.TestCase;

public class TrainStationMappingTest extends TestCase {

	TrainStationMapping trainStationMapping = null;

	public TrainStationMappingTest(String name) {
		super(name);
	}

	public TrainStationMapping createInstance() throws Exception {
		return new TrainStationMapping();
	}

	protected void setUp() throws Exception {
		super.setUp();
		trainStationMapping = createInstance();
	}

	protected void tearDown() throws Exception {
		trainStationMapping = null;
		super.tearDown();
	}

	public void testSetGetId() {
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			trainStationMapping.setId(tests[i]);
			assertEquals(tests[i], trainStationMapping.getId());
		}
	}

	public void testSetGetTrainId() {
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			trainStationMapping.setTrainId(tests[i]);
			assertEquals(tests[i], trainStationMapping.getTrainId());
		}

	}

	public void testSetGetTime() {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			trainStationMapping.setTime(tests[i]);
			assertEquals(tests[i], trainStationMapping.getTime());
		}
	}

	public void testVault() throws Exception {

	}

	public static void main(String[] args) {

		junit.textui.TestRunner.run(TrainStationMappingTest.class);

	}
}
