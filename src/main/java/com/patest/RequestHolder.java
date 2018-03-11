package com.patest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

/**
 * This class Handles Request
 */
public class RequestHolder {
    Logger logger=Logger.getLogger("RequestHolder");
    Utility utility;
    public RequestHolder(Utility utility){
        this.utility=utility;

    }
    public void specify_base_URL(String baseURL){
        RestAssured.baseURI=baseURL;
        logger.info("Base URL  is specified to the Restful Services");
    }
    public RequestSpecification getHttpRequest(){
        return RestAssured.given();
    }

}
