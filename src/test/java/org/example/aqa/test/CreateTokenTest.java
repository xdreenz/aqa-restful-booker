package org.example.aqa.test;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.example.aqa.data.EndPoints;
import org.junit.jupiter.api.*;
import org.example.aqa.data.DataHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
                .post(EndPoints.GET_TOKEN)
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
                .post(EndPoints.GET_TOKEN)
                .then()
                .spec(responseSpec)
                .assertThat()
                .body(matchesJsonSchema(DataHelper.getTokenSchema));
    }

    @Test
    @DisplayName("Authentication Test with correct username and password. Checking the presence of the payload")
    @Epic("Authentication")
    @Feature("Happy Path")
    @Story("Without optional params")
    public void AuthTestWithCredentials_CheckJSONContent() {
        String token = given()
                .spec(requestSpec)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .post(EndPoints.GET_TOKEN)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .jsonPath()
                .getString("token");
        assertFalse(StringUtils.isEmpty(token));
    }

    @Test
    @DisplayName("Authentication Test with another ContentType (XML). Checking the content type of the answer")
    @Epic("Authentication")
    @Feature("Sad Path")
    @Severity(SeverityLevel.MINOR)
    public void AuthTestWithCredentials_CheckXMLContent() {
        given()
                .spec(requestSpec)
                .accept(ContentType.XML)
                .body(DataHelper.getCorrectCredentials())
                .when()
                .post(EndPoints.GET_TOKEN)
                .then()
                .spec(responseSpec)
                .assertThat()
                .contentType(ContentType.XML);
    }

    static Stream<DataHelper.AuthInfo> authInfoProvider() {
        return Stream.of(
                new DataHelper.AuthInfo("user", ""),
                new DataHelper.AuthInfo("", "pass"),
                new DataHelper.AuthInfo("", "")
        );
    }
    @ParameterizedTest
    @MethodSource("authInfoProvider")
    @DisplayName("Authentication Test with empty username or/and password. Checking the status of the answer")
    @Epic("Authentication")
    @Feature("Sad Path")
    public void AuthTest_EmptyCredentials(DataHelper.AuthInfo authInfo) {
        given()
                .spec(requestSpec)
                .body(authInfo)
                .when()
                .post(EndPoints.GET_TOKEN)
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_FORBIDDEN);

    }
}
