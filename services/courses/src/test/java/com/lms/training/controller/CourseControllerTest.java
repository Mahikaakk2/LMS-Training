package com.lms.training.controller;

import com.lms.course.dto.CourseDto;
import com.lms.course.dto.ResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class CourseControllerTest {
    @BeforeAll
    public static void setup() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://localhost:8090/api";
    }



    @Test
    public void testHelloWorld() {
        given().
                when().
                get("/hello").
                then().
                statusCode(200).
                body(equalTo("Api is working"));
    }

    @Test
    public void testCreateAccount() {
        // JSON request body
        String requestBody = """
                {
                      "courseId" : 1,
                      "courseTitle": "Introduction to Java Programming",
                      "courseDescription": "Learn the basics of Java programming language.",
                      "courseDuration": "12:00:00",
                      "courseResource": "https://example.com/course/java-programming",
                      "courseCategory": "Programming",
                      "createdBy": 23,
                      "isApproved": "false"
                }""";

        // Sending POST request using RestAssured
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/create")
                .then()
                .statusCode(201)
                .extract().response();

        // Extracting values from the response
        String statusCode = response.jsonPath().getString("statusCode");
        String message = response.jsonPath().getString("message");

        // Asserting the response values
        assertEquals("201", statusCode);
        assertEquals("Created Successfully", message);
    }

    @Test
    public void testFetchAllPending() {
        // Perform GET request to fetch all pending courses
        List<CourseDto> courses = given()
                .baseUri(RestAssured.baseURI)
                .when()
                .get("/fetchAllPending")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList(".", CourseDto.class);
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }
    @Test
    public void testApproveCourse() {
        int courseId = 1; // Replace with a valid course ID
        Response response = given()
                .baseUri(baseURI)
                .when()
                .put("/approve?courseId={courseId}", courseId)
                .then()
                .statusCode(200)
                .extract().response();

        String statusCode = response.jsonPath().getString("statusCode");
        String message = response.jsonPath().getString("message");

        assertEquals("200", statusCode);
        assertEquals("Course approved successfully", message);
    }

    @Test
    public void testUpdateCourse() {
        int courseId = 1; // Replace with a valid course ID
        String requestBody = """
                {
                      "courseId" : 1,
                      "courseTitle": "Introduction to Java Development",
                      "courseDescription": "Learn the basics of Java programming language.",
                      "courseDuration": "12:00:00",
                      "courseResource": "https://example.com/course/java-programming",
                      "courseCategory": "Programming",
                      "createdBy": 23,
                      "isApproved": "false"
                }""";

        Response response = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/update?courseId={courseId}", courseId)
                .then()
                .statusCode(200)
                .extract().response();

        String statusCode = response.jsonPath().getString("statusCode");
        String message = response.jsonPath().getString("message");

        assertEquals("200", statusCode);
        assertEquals("Course updated successfully", message);
    }

    @Test
    public void testDeleteCourse() {
        int courseId = 1; // Replace with a valid course ID
        Response response = given()
                .baseUri(baseURI)
                .when()
                .delete("/delete?courseId={courseId}", courseId)
                .then()
                .statusCode(200)
                .extract().response();

        String statusCode = response.jsonPath().getString("statusCode");
        String message = response.jsonPath().getString("message");

        assertEquals("200", statusCode);
        assertEquals("Course deleted successfully", message);
    }


//    @Test
////    @Transactional
//    public void testFetchAllCourses() {
//        List<CourseDto> courses = given()
//                .baseUri(baseURI)
//                .when()
//                .get("/fetchAllCourses")
//                .then()
//                .statusCode(200)
//                .extract().jsonPath().getList(".", CourseDto.class);
//
//        assertNotNull(courses);
//        assertFalse(courses.isEmpty());
//    }

}
