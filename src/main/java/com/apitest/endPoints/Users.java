package com.apitest.endPoints;

import com.apitest.Utilities.Loadconfig;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.requestSpecification;

public class Users {

    static Loadconfig lf = new Loadconfig();

    public static Response createUserWithArray(int statusCode,String reqBody) {

        String endPoint = lf.HOST+"/user/createWithArray";
            Response response =given().log().ifValidationFails()
                    .header("Content-Type","application/json")
                    .accept("application/json").body(reqBody)
                    .when().post(endPoint)
                    .then().
                    assertThat()
                    .statusCode(statusCode).extract().response();
            return response;

        }

    public static Response createUserWithList(int statusCode, String reqBody){

        String endPoint = lf.HOST+"/user/createWithList";

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .body(reqBody)
                .when().post(endPoint)
                .then().assertThat()
                .statusCode(statusCode).extract().response();
        return response;
    }

    public static Response getUser(String userName, int statusCode){

        String endPoint = lf.HOST+"/user/"+userName;

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .when().get(endPoint)
                .then().assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public static Response updateUserDetails(int statusCode,String reqBody,String userName){

        String endPoint = lf.HOST+"/user/"+userName;

        Response response = given().
                log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json").body(reqBody)
                .when().put(endPoint)
                .then().assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public static Response deleteUser(int statusCode,String userName){

        String endPoint = lf.HOST+"/user/"+userName;
        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .when().delete(endPoint)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public static Response userlogin(int statusCode,String userName, String password){

        String endPoint = lf.HOST+"/user/login";

        // DOUBT - String endPoint = lf.HOST+"/user/login?username="+userName+"&password="+password;

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode)
                .extract().response();
        return response;
    }
}
