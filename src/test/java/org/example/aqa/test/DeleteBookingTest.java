package org.example.aqa.test;

import io.qameta.allure.*;
import org.example.aqa.data.Config;
import org.example.aqa.data.EndPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.example.aqa.data.DataHelper.*;

public class DeleteBookingTest extends BaseTest {

    @Test
    @DisplayName("Delete booking test - with the correct token by cookie")
    @Epic("Booking")
    @Feature("Happy path")
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteBooking_WithCorrectToken_ByCookie() {
        String token = given()
                .spec(requestSpec)
                .body(getCorrectCredentials())
                .when()
                .post(EndPoints.GET_TOKEN)
                .then().log().all()
                .statusCode(SC_OK)
                .extract()
                .response()
                .jsonPath().getString("token");
        given()
                .spec(requestSpec)
                .cookie("token=" + token)
                .when()
                .delete(EndPoints.BOOKING_ID, 1)
                .then()
                .assertThat()
                .statusCode(SC_CREATED);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 11})
    @DisplayName("Delete booking test - with the correct credentials by basic authorization")
    @Epic("Booking")
    @Feature("Happy path")
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteBooking_WithBasicAuth(int bookingId) {
        given()
                .spec(requestSpec)
                .auth().basic(Config.username, Config.password)
                .when()
                .delete(EndPoints.BOOKING_ID, bookingId)
                .then()
                .assertThat()
                .statusCode(SC_CREATED);
    }

    @Test
    @DisplayName("Delete booking test - with the wrong token")
    @Epic("Booking")
    @Feature("Sad path")
    @Story("Wrong Token")
    @Severity(SeverityLevel.NORMAL)
    public void Delete_Booking_with_wrong_token_by_Cookie() {
        given()
                .spec(requestSpec)
                .cookie("token=000000")
                .when()
                .delete(EndPoints.BOOKING_ID, 1)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Delete booking test - with the wrong credentials by basic authorization")
    @Epic("Booking")
    @Feature("Sad path")
    @Story("Wrong Credentails")
    @Severity(SeverityLevel.NORMAL)
    public void DeleteBookingWithWrongCredentials_WithBasicAuth() {
        given()
                .spec(requestSpec)
                .auth().basic("err", "err")
                .when()
                .delete(EndPoints.BOOKING_ID, 1)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Delete booking test - with no any authorization")
    @Epic("Booking")
    @Feature("Sad path")
    @Story("No Token")
    @Severity(SeverityLevel.NORMAL)
    public void DeleteBooking_WithNoAuth() {
        given()
                .spec(requestSpec)
                .when()
                .delete(EndPoints.BOOKING_ID, 1)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_FORBIDDEN);
    }

}
