package com.lms.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.training.dto.UserDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:9000/api";
    }

    @Test
    public void testHelloWorld() {
        given()
                .when()
                .get("/hello")
                .then()
                .statusCode(200)
                .body(equalTo("Hello World"));
    }

    @Test
    public void testCreateUser() {
        String requestBody = """
              {
                  "userId": 12,
                  "firstName":"John",
                  "lastName":"Sharma",
                  "email":"john.sharma@ukg.com",
                  "mobileNumber":"9876543210",
                  "password": "password",
                  "role": "mentor"
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
    public void testFetchUser() {
        String email = "john.sharma@ukg.com";

        given()
                .queryParam("email", email)
                .when()
                .get("/fetch")
                .then()
                .statusCode(200)
                .body("email", equalTo(email));
    }

    @Test
    public void testFetchAllUsers() {
        given()
                .when()
                .get("/fetchAll")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testUpdateUser() {
        String requestBody = """
                {
                  "userId": 12,
                      "firstName":"John",
                      "lastName":"Sharma",
                      "email":"john.sharma@ukg.com",
                      "mobileNumber":"9876543211",
                      "password": "password",
                      "role": "mentor"
                }""";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .queryParam("email", "john.sharma@example.com")
                .when()
                .put("/update")
                .then()
                .statusCode(200)
                .body("statusCode", equalTo("204"))
                .body("message", equalTo("Updated Successfully"));
    }

//    @Test
//    public void testDeleteUser() {
//        given()
//                .queryParam("email", "john.doe@example.com")
//                .when()
//                .delete("/delete")
//                .then()
//                .statusCode(200)
//                .body("statusCode", equalTo("204"))
//                .body("message", equalTo("Deleted Successfully"));
//    }
//

//
//    // Helper method to convert objects to JSON string
//    private String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//@AfterAll
//public static void cleanupTestData() {
//    // Delete test data after each test
//    given()
//            .queryParam("email", "john.sharma@ukg.com")
//            .when()
//            .delete("/delete")
//            .then()
//            .statusCode(200);
//}
}

