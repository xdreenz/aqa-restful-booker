package org.example.aqa.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;
import org.example.aqa.data.APIHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIHelper {
//    public static String createAuthToken(Integer statusCode) {
//        TokenInfo tokenInfo = given()
//                .spec(requestSpec)
//                .when()
//                .post(EndPoints.GET_TOKEN)
//                .then().log().all()
//                .statusCode(statusCode)
//                .extract()
//                .body()
//                .as(TokenInfo.class);
//        return tokenInfo.getToken();
//    }
//
//    public static String createAuthToken(DataHelper.AuthInfo authInfo, Integer statusCode) {
//        TokenInfo tokenInfo = given()
//                .spec(requestSpec)
//                .body(authInfo)
//                .when()
//                .post(EndPoints.GET_TOKEN)
//                .then().log().all()
//                .statusCode(statusCode)
//                .extract()
//                .body()
//                .as(TokenInfo.class);
//        return tokenInfo.getToken();
//    }


//    public static Map<String, Integer> sendQueryToGetCardsBalances(String token, Integer statusCode) {
//        APICardInfo[] cardsInfo = given()
//                .spec(requestSpec)
//                .header("Authorization", "Bearer " + token)
//                .when()
//                .get("/api/cards")
//                .then().log().all()
//                .statusCode(statusCode)
//                .extract()
//                .body()
//                .as(APICardInfo[].class);
//        Map<String, Integer> cardsBalances = new HashMap<>();
//        for (APICardInfo cardInfo : cardsInfo) {
//            cardsBalances.put(cardInfo.getId(), cardInfo.getBalance());
//        }
//        return cardsBalances;
//    }

//    public static BookingId[] GetBookingIds(Integer statusCode) {
//        return given()
//                .spec(requestSpec)
//                .when()
//                .get(EndPoints.booking)
//                .then()
//                .statusCode(statusCode)
//                .extract()
//                .body()
//                .as(BookingId[].class);
//    }
//
//    public static BookingId[] GetBookingIds(String firstname, String lastname, Date checkin, Date checkout, Integer statusCode) {
//        return given()
//                .spec(requestSpec)
//                .when()
//                .get(EndPoints.booking)
//                .then()
//                .statusCode(statusCode)
//                .extract()
//                .body()
//                .as(BookingId[].class);
//    }
//
//    public static BookingInfo GetBooking(String id, Integer statusCode) {
//        return given()
//                .spec(requestSpec)
//                .when()
//                .get(EndPoints.booking, id)
//                .then()
//                .statusCode(statusCode)
//                .extract()
//                .body()
//                .as(BookingInfo.class);
//    }

//    public static void SendPing(Integer statusCode) {
//        given()
//                .spec(requestSpec)
//                .when()
//                .get(EndPoints.PING)
//                .then()
//                .statusCode(statusCode);
//    }

    @Value
    public static class TokenInfo {
        String token;
    }

    @Value
    public static class BookingId {
        Number bookingid;
    }

    @Value
    public static class BookingInfo {
        String firstname;
        String lastname;
        Number totalprice;
        boolean depositpaid;
        BookingDates bookingdates;
        String additionalneeds;

        public static class BookingDates {
            private Date checkin;
            private Date checkout;
        }
    }

    @Value
    public static class APICardInfo {
        String id;
        String number;
        Integer balance;
    }

    @Value
    public static class APITransferInfo {
        String from;
        String to;
        int amount;
    }
}

