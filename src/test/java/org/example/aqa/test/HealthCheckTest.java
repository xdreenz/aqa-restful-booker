package org.example.aqa.test;

import io.qameta.allure.*;
import org.apache.http.HttpStatus;
import org.example.aqa.data.EndPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckTest extends BaseTest {

    @Test
    @DisplayName("Ping Test. Checking if status code is CREATED")
    @Epic("Ping")
    @Feature("Happy path")
    @Severity(SeverityLevel.CRITICAL)
    public void HealthCheck() {
        given()
                .spec(requestSpec)
                .when()
                .post(EndPoints.PING)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("Ping Test with wrong method. Checking if status code is METHOD_NOT_ALLOWED")
    @Epic("Ping")
    @Feature("Sad path")
    @Story("Wrong method")
    @Severity(SeverityLevel.NORMAL)
    public void HealthCheck_WithWrongMethod() {
        given()
                .spec(requestSpec)
                .when()
                .get(EndPoints.PING)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }
}
