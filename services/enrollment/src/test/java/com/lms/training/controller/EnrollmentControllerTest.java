package com.lms.training.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrollmentControllerTest {
    @BeforeAll
    public static void setup() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://localhost:8080/api";
    }
    @BeforeEach
    public void setupTestData() {
        // Create first enrollment record
        String requestBody1 = """
                {
                  "mentorID": "110",
                  "courseID": "199",
                  "newJoinID": "1100"
                }""";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody1)
                .when()
                .post("/create")
                .then()
                .statusCode(201);

        // Create second enrollment record
        String requestBody2 = """
                {
                  "mentorID": "906",
                  "courseID": "899",
                  "newJoinID": "1200"
                }""";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody2)
                .when()
                .post("/create")
                .then()
                .statusCode(201);
    }
    @Test
    public void testHelloWorld() {
        given().
                when().
                get("/hello").
                then().
                statusCode(200).
                body(equalTo("Hello World"));
    }
    @Test
    public void testCreateAccount() {
        String requestBody = """
                {
                  "mentorID": "416",
                  "courseID": "799",
                  "newJoinID": "1900"
                }""";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/create")
                .then()
                .statusCode(201)
                .extract().response();

        String status = response.jsonPath().getString("statusCode");
        String message = response.jsonPath().getString("message");

        assertEquals("201", status);
        assertEquals("Created Successfully", message);
    }
    @Test
    public void testFetchAllAccounts() {
        given()
                .when()
                .get("/fetchAll")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(notNullValue()))  // Ensure the response is a list
                .body("[0].mentorID", notNullValue())
                .body("[0].courseID", notNullValue())
                .body("[0].newJoinID", notNullValue());
    }
}
