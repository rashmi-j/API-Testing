package com.apitest.endPoints;

import com.apitest.Utilities.Loadconfig;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class Pets {

    static Loadconfig lf;

    public static Response getPetByID(String petID, int statusCode){
        lf = new Loadconfig();

        String endPoint = lf.HOST+"/pet/"+petID;

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }


    public static Response getPetStatus(int statusCode, String status) {
        lf = new Loadconfig();

        String endPoint = lf.HOST + "/pet/findByStatus?status=available";

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }

    public static Response createNewPet(String body,int statusCode){

        lf = new Loadconfig();

        String endPoint = lf.HOST + "/pet";

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json").body(body)
                .when().post(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;

    }
}

