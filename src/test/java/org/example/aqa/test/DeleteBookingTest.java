package org.example.aqa.test;

import io.qameta.allure.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.example.aqa.data.APIHelper;
import org.example.aqa.data.DataHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.example.aqa.data.APIHelper.*;
import static org.example.aqa.data.EndPoints.BOOKING_ID;

public class DeleteBookingTest extends BaseTest {

    final static String serverURL = System.getProperty("serverURI");
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(serverURL)
//            .setPort(3001)
            .log(LogDetail.ALL)
            .build();

    @Test
    @DisplayName("")
    @Epic("Booking")
    @Feature("Happy path")
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteBooking_WithCorrectToken_ByCookie() {
        var token = createAuthToken(DataHelper.getCorrectCredentials(), SC_OK);
        given()
                .spec(requestSpec)
                .cookie("token=" + token)
                .when()
                .delete(BOOKING_ID, 1)
                .then()
                .assertThat()
                .statusCode(SC_CREATED);
    }

    @Test
    @DisplayName("")
    @Epic("Booking")
    @Feature("Happy path")
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteBooking_WithCorrectToken_ByAuthorization() {
        var token = createAuthToken(DataHelper.getCorrectCredentials(), SC_OK);
        given()
                .spec(requestSpec)
                .header("Authorization", "Basic " + token)
                .when()
                .delete(BOOKING_ID, 1)
                .then()
                .assertThat()
                .statusCode(SC_CREATED);
    }

    @Test
    @DisplayName("")
    @Epic("Booking")
    @Feature("Sad path")
    @Story("Wrong Token")
    @Severity(SeverityLevel.CRITICAL)
    public void Delete_Booking_with_wrong_token_by_Cookie() {
        given()
                .spec(requestSpec)
                .cookie("token=000000000")
                .when()
                .delete(BOOKING_ID, 1)
                .then()
                .assertThat()
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("")
    @Epic("Booking")
    @Feature("Sad path")
    @Story("Wrong Token")
    @Severity(SeverityLevel.CRITICAL)
    public void Delete_Booking_with_wrong_token_by_Authorization() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Basic 0000000000")
                .when()
                .delete(BOOKING_ID, 1)
                .then()
                .assertThat()
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("")
    @Epic("Booking")
    @Feature("Sad path")
    @Story("No Token")
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteBooking_WithNoToken() {
        given()
                .spec(requestSpec)
                .when()
                .delete(BOOKING_ID, 1)
                .then()
                .assertThat()
                .statusCode(SC_FORBIDDEN);
    }

}
