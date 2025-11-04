package co.kata.api.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CommonSteps {

    private final BaseTest baseTest;

    public CommonSteps(BaseTest baseTest) {
        this.baseTest = baseTest;
    }

    @Then("I should see the response with status {int}")
    public void iShouldSeeTheResponseWithStatus(int statusCodeExpected) {
        baseTest.response.then().statusCode(statusCodeExpected);
    }

    @And("the response body should be match with the schema {string}")
    public void theResponseBodyShouldBeMatchWithTheSchema(String pathSchema) {
        baseTest.response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + pathSchema));
    }

    @And("the property {string} must be equal to {string}")
    public void thePropertyMustBeEqualTo(String nameProperty, String valueExpected) {
        baseTest.response.then().body(nameProperty, equalTo(valueExpected));
    }

    @And("the property {string} must be equal to {int}")
    public void thePropertyMustBeEqualTo(String nameProperty, int valueExpected) {
        baseTest.response.then().body(nameProperty, equalTo(valueExpected));
    }

}
