package com.patest.stepDefinitions;

import com.patest.RequestHolder;
import com.patest.ResponseHolder;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;


public class apiSteps {
    Logger logger=Logger.getLogger("apiSteps");
    RequestHolder requestHolder;
    ResponseHolder responseHolder;
    public apiSteps(RequestHolder requestHolder,ResponseHolder responseHolder){
        this.requestHolder=requestHolder;
        this.responseHolder=responseHolder;
    }
    @Given("^I specify the Base URL \"([^\"]*)\"$")
    public void iSpecifyTheBaseURL(String baseURL)  {
    requestHolder.specify_base_URL(baseURL);
    }

    @When("^I specify request type  \"([^\"]*)\" and set the parameter \"([^\"]*)\"$")
    public void iSpecifyRequestTypeAndSetTheParameter(String request_type, String dataPassed){
     responseHolder.select_the_request_type_and_pass_data(request_type,dataPassed);
    }

    @Then("^I should see response code \"([^\"]*)\"$")
    public void iShouldSeeResponseCode(String responseCode){
        Assert.assertEquals(Integer.parseInt(responseCode),responseHolder.getResponseCode());
    }
}
