package co.kata.api.definitions;

import co.kata.api.pojos.ApiPojo;
import io.cucumber.java.PendingException;
import io.restassured.specification.RequestSpecification;
import co.kata.api.utilities.EnvironmentValuesTask;
import io.restassured.RestAssured;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ApiStepDefinitions {

    static EnvironmentValuesTask env = new EnvironmentValuesTask();
    ApiPojo modelBody = new ApiPojo();
    private static final String BASE_URL = env.getenv("BASE_URL_APITEST");
    private RequestSpecification request;
    private final BaseTest baseTest;

    public ApiStepDefinitions(BaseTest baseTest) {
        this.baseTest = baseTest;
    }

    @Given("i want to get all users")
    public void iWantToGetAllUsers() {
        request = RestAssured.given()
            .baseUri(BASE_URL)
            .basePath("/users");
    }

    @When("I send request to get all users")
    public void iSendRequestToGetAllUsers() {
        baseTest.response = request.when().get();
    }

    @Given("i want to get a user with a invalid path in url")
    public void iWantToGetAUserWithAInvalidPathInUrl() {
        request = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath("/error");

    }

    @Given("i want to add a user with id {int} username {string} email {string} password {string}")
    public void iWantToAddAUserWithIdUsernameEmailPassword(int id, String username, String email, String password) {
        modelBody.setId(id);
        modelBody.setUsername(username);
        modelBody.setEmail(email);
        modelBody.setPassword(password);
        request = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(modelBody)
                .basePath("/users/");
    }

    @When("I send request to add user")
    public void iSendRequestToAddUser() {
        baseTest.response = request.when().post();
    }

    @Given("i want tp update a user with id {int} username {string} email {string} password {string}")
    public void iWantTpUpdateAUserWithIdUsernameEmailPassword(int id, String username, String email, String password) {
        modelBody.setId(id);
        modelBody.setUsername(username);
        modelBody.setEmail(email);
        modelBody.setPassword(password);
        request = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(modelBody)
                .basePath("/users/"+id);
    }

    @When("I send request to update a user")
    public void iSendRequestToUpdateAUser() {
        baseTest.response =  request.when().put();
    }

    @Given("i want to delete a user with id {int}")
    public void iWantToDeleteAUserWithId(int id) {
        request =  RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(modelBody)
                .basePath("/users/"+id);
    }

    @When("I send request to delete a user")
    public void iSendRequestToDeleteAUser() {
        baseTest.response =  request.when().delete();
    }

    @Given("i want to delete a user with id {string}")
    public void iWantToDeleteAUserWithId(String id) {
        request =  RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(modelBody)
                .basePath("/users/"+id);
    }
}
