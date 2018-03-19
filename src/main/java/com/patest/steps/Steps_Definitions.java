package com.patest.steps;

import com.google.gson.JsonObject;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.gl.E;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Steps_Definitions {
    Response response;
    ValidatableResponse validResponse;
    RequestSpecification request;
    JsonObject jsonObject;
    Steps_Implementation implementation;
   public Steps_Definitions(Steps_Implementation implementation){
       this.implementation=implementation;
   }

    @Given("^The baseURL \"([^\"]*)\" and port \"([^\"]*)\" up and running$")
    public void theBaseURLAndPortUpAndRunning(String baseURL, String portNumber) throws Throwable {
       implementation.setup_base(baseURL,portNumber);
    }
    @And("^Set Request and Response Header content type to \"([^\"]*)\"$")
    public void setRequestAndResponseHeaderContentTypeTo(String content_type)  {
       implementation.request_content_type_setup(content_type);
    }

    @Then("^I should see response status code \"([^\"]*)\"$")
    public void iShouldSeeResponseStatusCode(String response_code) throws Throwable {
      //  validResponse = response.then().statusCode(Integer.parseInt(response_code));
      //  System.out.println(response.getStatusCode());
      //  System.out.println(response.asString());
        Assert.assertEquals(implementation.getResponseCode(),Integer.parseInt(response_code));


    }

    @And("^The response body should contain following details about the API$")
    public void theResponseBodyShouldContainFollowingDetailsAboutTheAPI(Map<String,String> responseFields)  {
        System.out.println(responseFields);
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                validResponse.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                validResponse.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

    @When("^I specify the input data to be added to the API$")
    public void iSpecifyTheInputDataToBeAddedToTheAPI(DataTable table) {
        List<List<String>> data = table.raw();
        for(int i = 1; i < data.size(); i++){

            jsonObject.addProperty(data.get(i).get(0),data.get(i).get(1));
        }
        request.body(jsonObject.toString());

    }


    @Then("^I execute \"([^\"]*)\" request to the end point \"([^\"]*)\"$")
    public void iExecuteRequestToTheEndPoint(String request_type, String END_POINT) throws Throwable {
     implementation.request_execution_to_the_endpoint(request_type,END_POINT);
      }




}
