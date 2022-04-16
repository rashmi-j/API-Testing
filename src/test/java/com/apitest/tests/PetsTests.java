package com.apitest.tests;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import com.apitest.endPoints.Pets;
import com.apitest.Utilities.JsonUtilities;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class PetsTests {

    @Test
    /*public void verifyGetPetByID(){
        Response response = Pets.getPetByID("9223372000001114338", 200);
        Assert.assertEquals(response.jsonPath().get("name"), "petname123");

    }*/

    public void createNewPetAndVerify(){
        Date d = new Date();
        String name = "Kitto"+d.getTime();
        JsonUtilities JsonUtils = null;
        JSONObject requestBody=  JsonUtilities.getJsonFileObj("src/main/resources/NewPet.json");
        requestBody.put("name", name);
        System.out.println(requestBody.toString());
        Response response = Pets.createNewPet(requestBody.toString(), 200);
        String actualName = response.jsonPath().get("name").toString();
        String idOfPet = response.jsonPath().get("id").toString();


        Assert.assertEquals(name,actualName );
        Assert.assertFalse(idOfPet.isEmpty());


    }

    @Test
    public void verifyPetStatus(){
        Response response =  Pets.getPetStatus(200,"available");
        Assert.assertEquals(response.jsonPath().get("status[0]"), "available");

    }

    @Test
    public void verifyPetByID(){
        Date d = new Date();
        String name = "Kitto"+d.getTime();

        JSONObject requestBody=  JsonUtilities.getJsonFileObj("src/main/resources/NewPet.json");
        requestBody.put("name", name);
        System.out.println(requestBody.toString());
        Response response = Pets.createNewPet(requestBody.toString(), 200);
        String idOfPet = response.jsonPath().get("id").toString();

        Response resp = Pets.getPetByID(idOfPet, 200);
    }
}
