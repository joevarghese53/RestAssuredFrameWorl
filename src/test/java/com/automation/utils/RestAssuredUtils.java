package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {
    static RequestSpecification requestSpecification = RestAssured.given();
    static String endPoint;
    static Response response;

    public static void setEndPoint(String endPoint){
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value){
        requestSpecification.header(key, value);
    }

    public static void setBody(String fileName) throws Exception {
        String content = RestAssuredUtils.getJsonDataFromFile(fileName);
        requestSpecification.body(content);
    }

    public static String getJsonDataFromFile(String fileName) throws FileNotFoundException {
        String folderPath = "src/test/resources/data/";
        Scanner sc = new Scanner(new FileInputStream(folderPath + fileName));
        return sc.useDelimiter("\\Z").next();
    }

    public static void setBody(Object pojo) {
        requestSpecification.body(pojo);
    }

    public static void get(){
        requestSpecification.log().all();
        response = requestSpecification.get(endPoint);
        response.then().log().all();
    }

    public static void post(){
        requestSpecification.log().all();
        response = requestSpecification.post(endPoint);
        response.then().log().all();
    }

    public static void put(){
        requestSpecification.log().all();
        response = requestSpecification.put(endPoint);
        response.then().log().all();
    }

    public static void delete(){
        requestSpecification.log().all();
        response = requestSpecification.delete(endPoint);
        response.then().log().all();
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static Response getResponse(String endPoint){
        return response;
    }

    public static String getResponseFieldValue(String jsonPath){
        return response.jsonPath().getString(jsonPath);
    }

    public static void clear(){
        requestSpecification = RestAssured.given();
    }

}
