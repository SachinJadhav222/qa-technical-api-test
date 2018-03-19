package com.patest.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import java.util.Map;

public class Steps_Definitions {
    private Steps_Implementation implementation;
    public Steps_Definitions(Steps_Implementation implementation) {
        this.implementation = implementation;
    }

    @Given("^The baseURL \"([^\"]*)\" and port \"([^\"]*)\" up and running$")
    public void theBaseURLAndPortUpAndRunning(String baseURL, String portNumber)  {
        implementation.setup_base(baseURL, portNumber);
    }

    @And("^Set Request and Response Header content type to \"([^\"]*)\"$")
    public void setRequestAndResponseHeaderContentTypeTo(String content_type) {
        implementation.request_content_type_setup(content_type);
    }

    @Then("^I should see response status code \"([^\"]*)\"$")
    public void iShouldSeeResponseStatusCode(String response_code)  {
        Assert.assertEquals(implementation.getResponseCode(), Integer.parseInt(response_code));
    }

    @And("^The response body should contain following details about the API$")
    public void theResponseBodyShouldContainFollowingDetailsAboutTheAPI(Map<String, String> responseFields) {
        implementation.check_response_body_contents(responseFields);
    }

    @When("^I specify the input data to be added to the API$")
    public void iSpecifyTheInputDataToBeAddedToTheAPI(DataTable table) {
        implementation.add_data_to_the_api(table);
    }


    @Then("^I execute \"([^\"]*)\" request to the end point \"([^\"]*)\"$")
    public void iExecuteRequestToTheEndPoint(String request_type, String END_POINT) {
        implementation.request_execution_to_the_endpoint(request_type, END_POINT);
    }


}
