package tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

@Slf4j
public class VerifyHTTPHeader {

    @Test
    public void testContentTypeHeaderExistsAndCorrect() {
        log.info("Sending GET request to /users");

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        String contentType = response.getHeader("Content-Type");

        log.info("Received Content-Type header: {}", contentType);

        assertNotNull(contentType, "Content-Type header must be present");

        assertEquals(contentType, "application/json; charset=utf-8",
                "Unexpected Content-Type value");
    }
}
