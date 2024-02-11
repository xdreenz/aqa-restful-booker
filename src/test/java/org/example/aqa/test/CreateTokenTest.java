package org.example.aqa.test;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.example.aqa.data.DataHelper;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.apache.http.HttpStatus.*;
import static org.example.aqa.data.EndPoints.GET_TOKEN;

public class CreateTokenTest extends BaseTest {

    private static final File schema = new File("src/test/resources/get_token_schema.json");

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
                .assertThat()
                .body(matchesJsonSchema(schema));
    }

    @Test
    @DisplayName("Authentication Test with correct username and password. Checking the body of the answer")
    @Epic("Authentication")
    @Feature("Happy Path")
    @Story("Without optional params")
    public void AuthTestWithCredentials_CheckJSONContent() {
        given()
                .spec(requestSpec)
                .body(new DataHelper.AuthInfo("qwerty", "54498987987"))
                .when()
                .post(GET_TOKEN)
                .then()
                .assertThat()
                .body(matchesJsonSchema(schema));
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
                .assertThat()
                .contentType(ContentType.XML);
    }
}
