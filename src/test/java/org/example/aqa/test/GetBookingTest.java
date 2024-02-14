package org.example.aqa.test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.aqa.data.DataHelper;
import org.example.aqa.data.DataHelper.BookingInfo;
import org.example.aqa.data.EndPoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.apache.http.HttpStatus.SC_OK;
import static org.example.aqa.data.DataHelper.getBookingSchema;

public class GetBookingTest extends BaseTest {
    private int getFirstBookingId() {
        return given()
                .spec(requestSpec)
                .when().get(EndPoints.BOOKING)
                .then()
                .extract()
                .response().path("bookingid[0]");
    }
    @Test
    @DisplayName("Get booking with correct username and password. Checking the status of the answer")
    @Epic("Booking")
    @Feature("Happy Path")
    public void GetBooking_CheckStatus() {
        int bookingId = getFirstBookingId();
        given()
                .spec(requestSpec)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .get(EndPoints.BOOKING_ID, bookingId)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Get booking with correct username and password. Checking the presence of the answer's body")
    @Epic("Booking")
    @Feature("Happy Path")
    public void GetBooking_CheckPayload() {
        int bookingId = getFirstBookingId();
        BookingInfo response = given()
                .spec(requestSpec)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .get(EndPoints.BOOKING_ID, bookingId)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(BookingInfo.class);
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Get booking with correct username and password. Checking the body of the answer")
    @Epic("Booking")
    @Feature("Happy Path")
    public void GetBooking_CheckBody() {
        int bookingId = getFirstBookingId();
        given()
                .spec(requestSpec)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .get(EndPoints.BOOKING_ID, bookingId)
                .then()
                .assertThat()
                .body(matchesJsonSchema(getBookingSchema));
    }

    @Test
    @DisplayName("")
    @Epic("Booking")
    @Feature("Sad Path")
    public void GetBooking_NoId() {

    }

    @Test
    @DisplayName("")
    @Epic("Booking")
    @Feature("Sad Path")
    public void GetBooking_InvalidId() {

    }

    @Test
    @DisplayName("Get booking with correct username and password. Checking the body of the answer")
    @Epic("Booking")
    @Feature("Sad Path")
    public void GetBooking_InvalidIqd() {

    }
}
