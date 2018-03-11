package com.patest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

/**
 * This class handles all the response and convert to usable formas like sting , integer etc
 */
public class ResponseHolder {
    Logger logger=Logger.getLogger("ResponseHolder");
    RequestHolder requestHolder;
    Utility utility;
    Response response;
    public ResponseHolder(Utility utility,RequestHolder requestHolder){
        this.utility=utility;
        this.requestHolder=requestHolder;
    }

    public void select_the_request_type_and_pass_data(String request_type,String dataPass){
        String requestMethod="Method."+request_type.toUpperCase();
        response=requestHolder.getHttpRequest().request(requestMethod,dataPass);
    }

    /**
     * The response code returned in Integer format
     * @return
     */
    public int getResponseCode(){
        int actualResponseCode=response.getStatusCode();
        logger.info("Actual Response Code:"+actualResponseCode);
        return actualResponseCode;
    }

    /**
     * Response body in String format
     * @return
     */
    public String getResponseBody(){
        String actualResponseBody=response.getBody().toString();
        logger.info("Actual Response Body: "+actualResponseBody);
        return actualResponseBody;
    }

    /**
     * This will return the vlue of the key  parameter passed
     * @param key
     * @return
     */
    public String json_path_key_value(String key){

        return  response.jsonPath().get(key);
    }
}
