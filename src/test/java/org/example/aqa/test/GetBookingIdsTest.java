package org.example.aqa.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.aqa.data.EndPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.example.aqa.data.DataHelper.getBookingIdsSchema;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetBookingIdsTest extends BaseTest {

    @Test
    @DisplayName("Get booking Ids without optional params. Checking the body of the answer")
    @Epic("Booking")
    @Feature("Happy Path")
    @Story("Without optional params")
    public void GetBookingIds_CheckList() {
        List<Integer> responseList = given()
                .spec(requestSpec)
                .when().get(EndPoints.BOOKING)
                .then()
                .extract()
                .response().jsonPath().getList("bookingid");
        assertFalse(responseList.isEmpty());
    }

    @Test
    @DisplayName("Get booking Ids without optional params. Checking the body of the answer")
    @Epic("Booking")
    @Feature("Happy Path")
    @Story("Without optional params")
    public void GetBookingIds_CheckSchema() {
        given()
                .spec(requestSpec)
                .when()
                .post(EndPoints.BOOKING)
                .then()
                .assertThat()
                .body(matchesJsonSchema(getBookingIdsSchema));
    }
}
