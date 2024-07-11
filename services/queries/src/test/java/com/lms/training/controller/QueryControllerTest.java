package com.lms.training.controller;

import com.lms.training.dto.QueriesDto;
import com.lms.training.dto.ResponseDto;
import com.lms.training.service.IQueriesService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class QueryControllerTest {

    @Mock
    private IQueriesService queriesService;

    @InjectMocks
    private QueryController queryController;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8070; // Ensure this matches your application's port
        RestAssured.basePath = "/api";
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
    public void testCreateAccount() {
        // Mock data and behavior
        QueriesDto queriesDto = new QueriesDto(/* mock data */);
        doNothing().when(queriesService).createAccount(any(QueriesDto.class));

        // Perform the request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(queriesDto)
                .when()
                .post("/create")
                .then()
                .statusCode(201)
                .extract().response();

        String status = response.jsonPath().getString("status");
        String message = response.jsonPath().getString("message");

        assertEquals("201", status);
        assertEquals("Created Successfully", message);

        // Verify mock interactions
        verify(queriesService, times(1)).createAccount(any(QueriesDto.class));
    }

    @Test
    public void testUpdateQueries() {
        // Mock data and behavior
        int queryId = 123;
        when(queriesService.updateQuery(queryId)).thenReturn(true);

        // Perform the request
        Response response = given()
                .queryParam("queryId", queryId)
                .when()
                .put("/update")
                .then()
                .statusCode(200)
                .extract().response();

        String status = response.jsonPath().getString("status");
        String message = response.jsonPath().getString("message");

        assertEquals("204", status);
        assertEquals("Updated Successfully", message);

        // Verify mock interactions
        verify(queriesService, times(1)).updateQuery(queryId);
    }

    @Test
    public void testFetchAllQueries() {
        // Mock data and behavior
        List<QueriesDto> mockQueries = Arrays.asList(new QueriesDto(/* mock data */));
        when(queriesService.fetchAllQueries()).thenReturn(mockQueries);

        // Perform the request
        Response response = given()
                .when()
                .get("/fetchAll")
                .then()
                .statusCode(200)
                .extract().response();

        // Verify response
        List<QueriesDto> fetchedQueries = response.jsonPath().getList(".", QueriesDto.class);
        assertEquals(mockQueries.size(), fetchedQueries.size());

        // Verify mock interactions
        verify(queriesService, times(1)).fetchAllQueries();
    }

    @Test
    public void testFetchByMentorId() {
        // Mock data and behavior
        int mentorId = 456;
        List<QueriesDto> mockQueries = Arrays.asList(new QueriesDto(/* mock data */));
        when(queriesService.fetchByMentorId(mentorId)).thenReturn(mockQueries);

        // Perform the request
        Response response = given()
                .queryParam("mentorId", mentorId)
                .when()
                .get("/fetchByMentorId")
                .then()
                .statusCode(200)
                .extract().response();

        // Verify response
        List<QueriesDto> fetchedQueries = response.jsonPath().getList(".", QueriesDto.class);
        assertEquals(mockQueries.size(), fetchedQueries.size());

        // Verify mock interactions
        verify(queriesService, times(1)).fetchByMentorId(mentorId);
    }
}
