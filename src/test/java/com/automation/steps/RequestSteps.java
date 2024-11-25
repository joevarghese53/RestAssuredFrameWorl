package com.automation.steps;

import com.automation.pojo.CreateUserRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class RequestSteps {

    @Given("user wants to call {string} endpoint")
    public void user_wants_to_call_endpoint(String endPoint) {
        RestAssuredUtils.clear();
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @Given("set request body from file {string} using pojo")
    public void set_request_body_from_file_using_pojo(String fileName) throws Exception {
        String content = RestAssuredUtils.getJsonDataFromFile(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateUserRequestPojo requestPojo = objectMapper.readValue(content, CreateUserRequestPojo.class);
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setObject("requestPojo", requestPojo);
    }

    @When("user performs post request")
    public void user_performs_post_request() {
        RestAssuredUtils.post();
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @Then("store the {string} from response")
    public void store_the_from_response(String key) {
        String value = RestAssuredUtils.getResponseFieldValue(key);
        ConfigReader.setProperty(key, value);
    }


    @When("user performs get request")
    public void userPerformsGetRequest() {
        RestAssuredUtils.get();
    }

    @And("verify response has field {string} same as {string}")
    public void verifyResponseHasFieldSameAs(String fieldName, String fieldValue) {
        String value = RestAssuredUtils.getResponseFieldValue(fieldName);
        Assert.assertEquals(fieldValue, value);
    }

    @And("set request body from file {string}")
    public void setRequestBodyFromFile(String fileName) {
        try {
            RestAssuredUtils.setBody(fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("user performs put request")
    public void userPerformsPutRequest() {
        RestAssuredUtils.put();
    }

    @When("user performs delete request")
    public void userPerformsDeleteRequest() {
        RestAssuredUtils.delete();
    }
}
