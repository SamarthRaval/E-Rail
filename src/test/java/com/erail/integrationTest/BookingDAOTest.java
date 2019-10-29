//Author:Varun
package com.erail.integrationTest;

import com.erail.database.DAO.IBookingDAO;
import com.erail.mockDAO.MockBookingDAO;
import com.erail.models.Booking;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = com.erail.Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BookingDAOTest {

@Test
    public void createBookingResponse() throws Exception{
        IBookingDAO bookingDAO = new MockBookingDAO();
        Booking booking = new Booking();
        booking.setId(1);
        booking.setFare(40L);
        booking.setTimestamp(new Timestamp(new Date().getTime()));
        booking.setBookingNumber(11111);
        booking.setTrainName("test1");
        booking.setUserId(1);
        booking.setReachTime("10:40");
        booking.setSourceStationName("testSource");
        booking.setDestinationStationName("testDestination");
        booking.setClassType("economy");
        booking.setArrivalTime("09:40");
        booking.setStatus("Booked");
    Assert.assertEquals(3,bookingDAO.createBookingResponse(booking));
    }

    @Test
    public void getBookingByBookingNumber() throws Exception{
        IBookingDAO bookingDAO = new MockBookingDAO();
        Booking book = bookingDAO.getBookingByBookingNumber(34324);
        System.out.println(book.getBookingNumber());
        Assert.assertNotNull(book);
    }

    @Test
    public void getBookingByUserId() throws Exception{
        IBookingDAO bookingDAO = new MockBookingDAO();
        List<Booking> book = bookingDAO.getBookingByUserId(1);
        Assert.assertNotNull(book);
    }

    @Test
    public void deleteBookingById() throws Exception{
        IBookingDAO bookingDAO = new MockBookingDAO();
        Assert.assertTrue(bookingDAO.deleteBookingById(1));
    }

    @Test
    public void getBookingById() throws Exception{
        IBookingDAO bookingDAO = new MockBookingDAO();
        Assert.assertNotNull(bookingDAO.getBookingById(1));
    }
}
