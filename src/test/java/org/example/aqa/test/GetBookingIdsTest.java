package org.example.aqa.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.aqa.data.DataHelper;
import org.example.aqa.data.EndPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.example.aqa.data.DataHelper.getBookingIdsSchema;

public class GetBookingIdsTest extends BaseTest {

    @Test
    @DisplayName("Authentication Test without username and password. Checking the body of the answer")
    @Epic("Booking")
    @Feature("Happy Path")
    @Story("Without optional params")
    public void AuthTestWithNoCredentials_CheckSchema() {
        given()
                .spec(requestSpec)
                .when()
                .post(EndPoints.GET_TOKEN)
                .then()
                .assertThat()
                .body(matchesJsonSchema(getBookingIdsSchema));
    }
}
