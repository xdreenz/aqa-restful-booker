package org.example.aqa.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.example.aqa.data.Config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

        @BeforeAll
        void setup() {
            RestAssured.filters(new AllureRestAssured());
        }

        @AfterAll
        void tearDown() {
            RestAssured.reset();
        }

    private static final LogConfig logConfig = LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    private static final RestAssuredConfig config = RestAssuredConfig.config().logConfig(logConfig);

    static final RequestSpecification requestSpec = new RequestSpecBuilder()
        .setBaseUri(Config.url)
        .setPort(Config.port)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .setConfig(config)
        .build();

    static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
        .log(LogDetail.BODY)
        .build();
}
