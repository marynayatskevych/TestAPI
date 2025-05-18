package tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

@Slf4j
public class VerifyHTTPBody {

    @Test(description = "verify HTTP body")
    public void testResponseBodyHas10Users() {
        log.info("Sending GET request to /users");

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        log.info("Received response body:\n{}", response.getBody().asPrettyString());
        List<Object> users = response.jsonPath().getList("$");
        assertEquals(users.size(), 10, "Expected exactly 10 users in the response");
    }
}
