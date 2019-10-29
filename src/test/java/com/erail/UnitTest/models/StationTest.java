package com.erail.UnitTest.models;

import com.erail.models.Station;

import junit.framework.TestCase;

public class StationTest extends TestCase {

	Station station = null;

	public StationTest(String name) {
		super(name);
	}

	public Station createInstance() throws Exception {
		return new Station();
	}

	protected void setUp() throws Exception {
		super.setUp();
		station = createInstance();
	}

	protected void tearDown() throws Exception {
		station = null;
		super.tearDown();
	}

	public void testSetGetStationNumber() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			station.setStationNumber(tests[i]);
			assertEquals(tests[i], station.getStationNumber());
		}
	}

	public void testSetGetStationId() throws Exception {

		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			station.setId(tests[i]);
			assertEquals(tests[i], station.getId());
		}

	}

	public void testSetGetStationName() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			station.setStationName(tests[i]);
			assertEquals(tests[i], station.getStationName());
		}
	}

	public void testVault() throws Exception {

	}

	public static void main(String[] args) {

		junit.textui.TestRunner.run(StationTest.class);

	}
}
