package com.patest.steps;

import com.google.gson.JsonObject;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Steps_Implementation {
    Logger logger = Logger.getLogger("Steps_Implementation");
    Response response;
    ValidatableResponse validResponse;
    RequestSpecification request;
    JsonObject jsonObject;

    public void setup_base(String baseURL, String port_code) {
        baseURI = baseURL;
        port = Integer.parseInt(port_code);
        jsonObject = new JsonObject();
        logger.info("Base URL set- " + baseURL);
        logger.info("Port Set- " + port_code);
    }

    public void request_content_type_setup(String content_type) {
        request = given().accept(content_type);
        logger.info("Request Type set to- " + content_type);
    }

    public int getResponseCode() {
        System.out.println("\nResponse Status Code =" + response.getStatusCode());
        System.out.println("\nResponse Body:" + response.prettyPrint());
        logger.info("Response Coded returned");
        return response.getStatusCode();
    }

    public void request_execution_to_the_endpoint(String request_type, String END_POINT) {
        switch (request_type) {
            case "GET": {
                response = request.get(END_POINT);
                break;
            }
            case "POST": {
                response = request.post(END_POINT);
                break;
            }
            case "PUT": {
                response = request.put(END_POINT);
                break;
            }
            case "DELETE": {
                response = request.delete(END_POINT);
                break;
            }
        }
        logger.info(" Request Executed- " + request_type);
        logger.info(" END POINT- " + END_POINT);
    }

    public void add_data_to_the_api(DataTable table) {
        List<List<String>> data = table.raw();
        for (int i = 1; i < data.size(); i++) {

            jsonObject.addProperty(data.get(i).get(0), data.get(i).get(1));
        }
        request.body(jsonObject.toString());
    }

    public void check_response_body_contents(Map<String, String> responseFields) {
        System.out.println(responseFields);
        validResponse = response.then();
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                validResponse.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            } else {
                validResponse.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }
}
