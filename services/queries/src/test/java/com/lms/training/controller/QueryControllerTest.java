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

public class QueryControllerTest {

    @BeforeAll
    public static void setup() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://localhost:8070/api";
    }

    @BeforeEach
    public void setupTestData() {
        // Create some initial queries using POST /create endpoint
        String requestBody1 = """
                {
                  "newJoinerId": 1100,
                  "mentorId": 110,
                  "courseId": 199,
                  "queryText": "Query 1"
                }""";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody1)
                .when()
                .post("/create")
                .then()
                .statusCode(201);

        String requestBody2 = """
                {
                  "newJoinerId": 1200,
                  "mentorId": 906,
                  "courseId": 899,
                  "queryText": "Query 2"
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
    public void testCreateQuery() {
        String requestBody = """
                {
                  "newJoinerId": 1900,
                  "mentorId": 416,
                  "courseId": 799,
                  "queryText": "New Query"
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
    public void testUpdateQuery() {
        String updatedResponseText = "Updated response";

        given()
                .contentType(ContentType.TEXT)
                .body(updatedResponseText)
                .queryParam("queryId", 1)
                .when()
                .put("/update")
                .then()
                .statusCode(200)
                .body("statusCode", equalTo("204"))
                .body("message", equalTo("Updated Successfully"));
    }

    @Test
    public void testFetchAllQueries() {
        given()
                .when()
                .get("/fetchAll")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", notNullValue()) // Assuming at least 2 queries are created in setup
                .body("[0].mentorId", notNullValue())
                .body("[0].courseId", notNullValue())
                .body("[0].newJoinerId", notNullValue())
                .body("[0].queryText", notNullValue());
    }

    @Test
    public void testFetchByMentorId() {
        given()
                .queryParam("mentorId", 110)
                .when()
                .get("/fetchByMentorId")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", notNullValue())
                .body("[0].mentorId", equalTo(110))
                .body("[0].queryText", equalTo("Query 1"));
    }

    @Test
    public void testFetchByNewJoinerId() {
        given()
                .queryParam("newJoinerId", 1100)
                .when()
                .get("/fetchByNewJoinerId")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", notNullValue())
                .body("[0].newJoinerId", equalTo(1100))
                .body("[0].queryText", equalTo("Query 1"));
    }

}
