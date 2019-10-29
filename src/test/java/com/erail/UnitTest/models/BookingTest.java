package com.erail.UnitTest.models;

import com.erail.models.Booking;

import junit.framework.TestCase;

public class BookingTest extends TestCase{
	
	Booking booking = null;
	
	public BookingTest(String name) {
		super(name);
	}

	public Booking createInstance() throws Exception {
		return new Booking();
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		booking = createInstance();
	}
	
	
	protected void tearDown() throws Exception {
		booking = null;
		super.tearDown();
	}
	
	
	public void testSetGetBookingNumber() throws Exception {
	
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			booking.setBookingNumber(tests[i]);
			assertEquals(tests[i], booking.getBookingNumber());
		}

	}
	
	public void testSetGetUserId() throws Exception {
		
		long[] tests = { Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			booking.setUserId(tests[i]);
			assertEquals(tests[i], booking.getUserId());
		}

	}
	
	public void testSetGetTrainName() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			booking.setTrainName(tests[i]);
			assertEquals(tests[i], booking.getTrainName());
		}
	}
	
	public void testSetGetSourceStationName() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			booking.setSourceStationName(tests[i]);
			assertEquals(tests[i], booking.getSourceStationName());
		}
	}
	
	
	public void testSetGetDestinationStationName() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			booking.setDestinationStationName(tests[i]);
			assertEquals(tests[i], booking.getDestinationStationName());
		}
	}
	
	public void testSetGetClassType() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			booking.setClassType(tests[i]);
			assertEquals(tests[i], booking.getClassType());
		}
	}
	
	public void testSetGetArrivalTime() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			booking.setArrivalTime(tests[i]);
			assertEquals(tests[i], booking.getArrivalTime());
		}
	}
	
	public void testSetGetReachTime() throws Exception {
		String[] tests = { new String(), null };
		for (int i = 0; i < tests.length; i++) {
			booking.setReachTime(tests[i]);
			assertEquals(tests[i], booking.getReachTime());
		}
	}
	
	public void testVault() throws Exception {
	
	}

	public static void main(String[] args) {
		
		junit.textui.TestRunner.run(BookingTest.class);
	
	}
}
