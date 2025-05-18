package tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.User;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

@Slf4j
public class UserCrudTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Test
    public void testCreateUser() {
        log.info("Sending POST request to create user");

        User user = new User();
        user.setName("Mia");
        user.setUsername("Mia_test");
        user.setEmail("test@example.com");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(BASE_URL);

        log.info("Response:\n{}", response.asPrettyString());

        assertEquals(response.statusCode(), 201, "Expected status code 201 created");
        assertEquals(response.jsonPath().getString("name"), user.getName());
        assertEquals(response.jsonPath().getString("email"), user.getEmail());
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        log.info("Sending GET request to /users/{}", userId);

        Response response = given()
                .pathParam("id", userId)
                .when()
                .get(BASE_URL + "/{id}");

        log.info("Response:\n{}", response.asPrettyString());

        assertEquals(response.statusCode(), 200, "Expected status code 200");

        String name = response.jsonPath().getString("name");
        String email = response.jsonPath().getString("email");

        assertNotNull(name, "Name should not be null");
        assertFalse(name.isEmpty(), "Name should not be empty");
        assertNotNull(email, "Email should not be null");
        assertFalse(email.isEmpty(), "Email should not be empty");
    }

    @Test
    public void testUpdateUser() {
        int userId = 1;

        log.info("Sending PUT request to /users/{}", userId);

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("Updated User");
        updatedUser.setUsername("updatedusername");
        updatedUser.setEmail("updated@example.com");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", userId)
                .body(updatedUser)
                .when()
                .put(BASE_URL + "/{id}");

        log.info(" Response:\n{}", response.asPrettyString());

        assertEquals(response.statusCode(), 200, "Expected status code 200");
        assertEquals(response.jsonPath().getString("name"), updatedUser.getName());
        assertEquals(response.jsonPath().getString("username"), updatedUser.getUsername());
        assertEquals(response.jsonPath().getString("email"), updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;

        log.info("Sending DELETE request to /users/{}", userId);

        Response response = given()
                .pathParam("id", userId)
                .when()
                .delete(BASE_URL + "/{id}");

        log.info("Response status: {}", response.getStatusCode());
        assertEquals(response.statusCode(), 200, "Expected status code 200");
    }

}

