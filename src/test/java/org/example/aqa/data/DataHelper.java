package org.example.aqa.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

import java.io.File;
import java.util.Date;

public class DataHelper {
    private DataHelper() {}

    public static final File getTokenSchema = new File("src/test/resources/get_token_schema.json");
    public static final File getBookingIdsSchema = new File("src/test/resources/get_booking_ids_schema.json");
    public static final File getBookingSchema = new File("src/test/resources/get_booking_schema.json");

    @Value
    public static class AuthInfo {
        String username;
        String password;
    }

    @Data
    public static class BookingInfo {
        @JsonProperty("firstname")
        String firstName;

        @JsonProperty("lastname")
        String lastName;

        @JsonProperty("totalprice")
        Integer totalPrice;

        @JsonProperty("depositpaid")
        boolean depositPaid;

        @JsonProperty("bookingdates")
        BookingDates bookingDates;

        @JsonProperty("additionalneeds")
        String additionalNeeds;

    }

    @Data
    public static class BookingDates {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @JsonProperty("checkin")
        Date checkIn;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @JsonProperty("checkout")
        Date checkOut;
    }

    public static AuthInfo getCorrectCredentials() {
        return new AuthInfo(Config.username, Config.password);
    }

}

