//Author:Varun
package com.erail.Utility;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    private static final String REGEX_TIME =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public static long generateRandomNumber(){
        long bookingNumber = 10000 + new Random().nextInt(90000);
        return bookingNumber;
    }

    public static String getEmailBody(String originalEmail, long bookingNumber, String trainName,String arrivalTime){
        String emailBody = originalEmail;
        emailBody = emailBody.replace("%BOOKING_NUMBER%", String.valueOf(bookingNumber));
        emailBody = emailBody.replace("%TRAIN_NAME%",trainName );
        emailBody = emailBody.replace("%ARRIVAL_TIME%",arrivalTime);
        return  emailBody;
    }


    public static boolean validateTime(String time){
           Pattern pattern = Pattern.compile(REGEX_TIME);
            Matcher matcher = matcher = pattern.matcher(time);
            return matcher.matches();


    }

}
