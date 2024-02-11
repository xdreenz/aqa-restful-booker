package org.example.aqa.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.example.aqa.data.EndPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.example.aqa.data.EndPoints.GET_TOKEN;

public class GetBookingIdsTest {
    final static String serverURL = System.getProperty("serverURI");
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(serverURL)
//            .setPort(3001)
            .log(LogDetail.ALL)
            .build();

    @Test
    @DisplayName("Authentication Test without username and password. Checking the body of the answer")
    @Epic("Booking")
    @Feature("Happy Path")
    @Story("Without optional params")
    public void AuthTestWithNoCredentials_CheckSchema() {
        given()
                .spec(requestSpec)
                .when()
                .post(GET_TOKEN)
                .then()
                .assertThat()
                .body(matchesJsonSchema("src/test/resources/get_token_schema.json"));
    }
}
