package org.example.aqa.test;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.example.aqa.data.APIHelper;
import org.junit.jupiter.api.*;
import org.example.aqa.data.DataHelper;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.apache.http.HttpStatus.*;
import static org.example.aqa.data.EndPoints.GET_TOKEN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateTokenTest extends BaseTest {

    @Test
    @DisplayName("Authentication Test with correct username and password. Checking the body of the answer")
    @Epic("Authentication")
    @Feature("Happy Path")
    public void AuthTestWithCredentials_CheckStatus() {
        given()
                .spec(requestSpec)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .post(GET_TOKEN)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Authentication Test with correct username and password. Checking the body of the answer")
    @Epic("Authentication")
    @Feature("Happy Path")
    public void AuthTestWithCredentials_CheckSchema() {
        given()
                .spec(requestSpec)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .post(GET_TOKEN)
                .then()
                .spec(responseSpec)
                .assertThat()
                .body(matchesJsonSchema(DataHelper.getTokenSchema));
    }

    @Test
    @DisplayName("Authentication Test with correct username and password. Checking the presence of the body")
    @Epic("Authentication")
    @Feature("Happy Path")
    @Story("Without optional params")
    public void AuthTestWithCredentials_CheckJSONContent() {
        DataHelper.TokenInfo tokenInfo = given()
                .spec(requestSpec)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .post(GET_TOKEN)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(DataHelper.TokenInfo.class);
        assertFalse(StringUtils.isEmpty(tokenInfo.getToken()));
    }

    @Test
    @DisplayName("Authentication Test with another ContentType. Checking the content type of the answer")
    @Severity(SeverityLevel.MINOR)
    public void AuthTestWithCredentials_CheckXMLContent() {
        given()
                .spec(requestSpec)
                .accept(ContentType.XML)
                .when()
                .post(GET_TOKEN)
                .then()
                .spec(responseSpec)
                .assertThat()
                .contentType(ContentType.XML);
    }
}
