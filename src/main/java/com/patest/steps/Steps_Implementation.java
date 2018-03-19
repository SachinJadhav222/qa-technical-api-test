package com.patest.steps;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;


public class Steps_Implementation {
    Response response;
    ValidatableResponse validResponse;
    RequestSpecification request;
    JsonObject jsonObject;

    public void setup_base(String baseURL,String port_code){
        baseURI=baseURL;
        port=Integer.parseInt(port_code);
        jsonObject=new JsonObject();
    }
    public void request_content_type_setup(String content_type){
        request=given().accept(content_type);
    }
    public int getResponseCode(){
        return response.getStatusCode();
    }

    public void request_execution_to_the_endpoint(String request_type,String END_POINT){
        switch (request_type){
            case "GET" :{
                response=request.get(END_POINT);
                System.out.println("GET Request Executed");
                break;}
            case "POST":{
                response=request.post(END_POINT);
                System.out.println("POST Request Executed");
                break;
            }
            case "PUT":{
                response=request.put(END_POINT);
                System.out.println("PUT Request Executed");
                break;
            }
            case "DELETE":{
                response=request.delete(END_POINT);
                System.out.println("DELETE Request Executed");
                break;
            }
    }}
}
