package org.example.aqa.test;

import io.qameta.allure.*;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.example.aqa.data.EndPoints.PING;

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
                .post(PING)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("Ping Test with wrong method. Checking if status code is NOT_FOUND")
    @Epic("Ping")
    @Feature("Sad path")
    @Story("Wrong method")
    @Severity(SeverityLevel.NORMAL)
    public void HealthCheck_WrongMethod() {
        given()
                .spec(requestSpec)
                .when()
                .get(PING)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
