package ru.netology.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import lombok.Value;

import java.util.Random;

public class DataHelper {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    // в качестве параметра метод принимает порядковый номер карты, как ее видит пользователь на странице, например, 1, 2 и т.д.
    public static Card getCardInfoJson(int cardIndex) {

        Response cardsInfo = given() // "дано"
                .spec(requestSpec)
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6InZhc3lhIn0.JmhHh8NXwfqktXSFbzkPohUb90gnc3yZ9tiXa0uUpRY")
        .when()
                .get("/api/cards");

        JsonPath jsonPathEvaluator = cardsInfo.jsonPath();

        String id = jsonPathEvaluator.get(String.format("[%d].id", cardIndex - 1));
        String cardNumberMask = jsonPathEvaluator.get(String.format("[%d].number", cardIndex - 1));
        String cardNumberFull = cardNumberMask.replaceAll("\\W+", "555900000000");
        String cardLastSymbols = cardNumberMask.replaceAll("\\W+", "");
        double balance = Double.parseDouble(Integer.toString(jsonPathEvaluator.get(String.format("[%d].balance", cardIndex - 1))));

        return new Card(id, cardNumberFull, balance, cardLastSymbols);
    }

    public static double generateRandomDouble(double max) {
        Random random = new Random();
        int num = random.nextInt((int) Math.round(max));
        return num;
    }
}
