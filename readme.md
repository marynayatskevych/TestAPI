### TestAPI Project

Project is created for practicing automated API testing using (https://jsonplaceholder.typicode.com).

The project is implemented with **Java + Rest Assured + TestNG + Allure**.

### _Implemented Test Scenarios_

**Main Scenarios:**

1. TC_API_01	Verify HTTP status code 200 OK is returned by GET /users.
2. TC_API_02	Verify Content-Type header is present and correct.
3. TC_API_03	Verify response body contains an array of 10 users

**Scenarios (CRUD):**

1. Create	POST /users	Create a new user.
3. Read	GET /users/{id}	Get user by ID.
5. Update	PUT /users/{id}	Update user by ID.
7. Delete	DELETE /users/{id}	Delete user by ID.

### Test Classes Description
**VerifyHTTPStatusCode.java**
* Sends GET /users.
* Logs and asserts that response status is 200 OK.

**VerifyHTTPHeader.java**
* Sends GET /users.
* Checks if the Content-Type header exists and equals application/json; charset=utf-8.

**VerifyHTTPBody.java**
* Sends GET /users.
* Parses response body and asserts that the user list contains exactly 10 users.

**UserCrudTests.java**
Implements full CRUD testing for the /users resource:
* testCreateUser() — sends POST to create user and verifies returned fields
* testGetUserById() — retrieves user with ID 1 and checks presence of name/email
* testUpdateUser() — sends PUT with updated fields and validates the response
* testDeleteUser() — sends DELETE and checks for status 200

**User.java**
* Plain Java model (POJO) used to represent user data for serialization/deserialization with Rest Assured and Jackson

### Run
1. pow.xml for parallel execution
2. mvn clean install
3. allure generate allure-results --clean -o target/allure-report
#### See result
4. allure open target/allure-report

