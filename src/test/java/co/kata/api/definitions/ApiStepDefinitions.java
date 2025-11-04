package co.kata.api.definitions;

import co.kata.api.pojos.ApiPojo;
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

    @Given("I want create a post with title {string} and body {string} for user with ID {int}")
    public void iWantCreateAPostWithTitleAndBodyForUserWithID(String title, String body, int userId) {
        modelBody.setTitle(title);
        modelBody.setBody(body);
        modelBody.setUserId(userId);
        request = RestAssured.given().log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(modelBody)
                .basePath("/posts");
    }

    @When("I send request to create the post")
    public void iSendRequestToCreateThePost() {
        baseTest.response = request.when().post();
    }

    @Given("I want update the title of post with id {int} to {string}")
    public void iWantUpdateTheTitleOfPostWithIdTo(int id, String newTitle) {
        modelBody.setTitle(newTitle);
        request = RestAssured.given().log().all()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(modelBody)
                .basePath("/posts/" + id);
    }

    @When("I send request to update the post")
    public void iSendRequestToUpdateThePost() {
        baseTest.response = request.when().patch();
    }

    @Given("I want delete a post with id {int}")
    public void iWantDeleteAPostWithId(int id) {
        request = RestAssured.given().log().all()
                .baseUri(BASE_URL)
                .basePath("/posts/" + id);
    }

    @When("I send request to delete the post")
    public void iSendRequestToDeleteThePostWithId() {
        baseTest.response = request.when().delete();
    }

    @Given("I want read a post of user with ID {int}")
    public void iWantReadAPostOfUserWithId(int id) {
        request = RestAssured.given().log().all()
                .baseUri(BASE_URL)
                .param("userId", id)
                .basePath("/posts");
    }

    @When("I send request to read the post")
    public void iSendRequestToReadThePost() {
        baseTest.response = request.when().get();
    }
}
