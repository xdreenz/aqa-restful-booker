package org.example.aqa.data;

import lombok.Value;

import java.util.Date;
import java.util.Random;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        String username;
        String password;
    }

    @Value
    public static class TokenInfo {
        String token;
    }

    @Value
    public static class BookingId {
        Number bookingid;
    }

    @Value
    public static class BookingInfo {
        String firstname;
        String lastname;
        Number totalprice;
        boolean depositpaid;
        BookingDates bookingdates;
        String additionalneeds;

        public static class BookingDates {
            private Date checkin;
            private Date checkout;
        }
    }

    @Value
    public static class APICardInfo {
        String id;
        String number;
        Integer balance;
    }

    @Value
    public static class APITransferInfo {
        String from;
        String to;
        int amount;
    }

    public static AuthInfo getCorrectCredentials() {
        return new AuthInfo(Config.username, Config.password);
    }
}

