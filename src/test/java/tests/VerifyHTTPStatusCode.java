package tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

@Slf4j
public class VerifyHTTPStatusCode {

    @Test
    public void testStatusCodeIs200() {
        log.info("Send request to /users");

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        int actualStatusCode = response.getStatusCode();
        log.info("Get status: {}", response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        assertEquals(actualStatusCode, 200, "Expected 200 OK");
    }
}
