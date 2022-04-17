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

    Date date;

    @Test (description = "Create new pet and verify the creation")
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

        /*** Line 24 and 28 is for ^Data preparation**/

        System.out.println(requestBody.toString());

        Response response = Pets.createNewPet(requestBody.toString(), 200);
        /***^Api call**/

        String actualName = response.jsonPath().get("name").toString();
        String idOfPet = response.jsonPath().get("id").toString();

        Assert.assertEquals(name,actualName );
        Assert.assertFalse(idOfPet.isEmpty());
        /***^Assertions**/


    }

    @Test
    public void verifyPetStatus(){
        Response response =  Pets.getPetStatus(200,"available");
        Assert.assertEquals(response.jsonPath().get("status[0]"), "available");

    }

    @Test (description = "Verify pet by id")
    public void verifyPetByID(){
        Date d = new Date();
        String name = "Kitto"+d.getTime();
        JSONObject requestBody=  JsonUtilities.getJsonFileObj("src/main/resources/NewPet.json");
        requestBody.put("name", name);


        System.out.println(requestBody.toString());

        Response response = Pets.createNewPet(requestBody.toString(), 200);
        String idOfPet = response.jsonPath().get("id").toString();

        Response resp = Pets.getPetByID(idOfPet, 200);
        Assert.assertEquals(name, resp.jsonPath().get("name"));
    }

    @Test (description = "verify updating of a pet name, family and tag name")
    public void verifyUpdatePet(){
        date = new Date();
        String name = "Kitto"+date.getTime();
        String expectedName = "Petone";
        String expectedtagName = "updated_pet";
        String expectedFamily = "Big cats";

        JSONObject requestBody=  JsonUtilities.getJsonFileObj("src/main/resources/NewPet.json");
        requestBody.put("name",name);

        /*** ^^^Data preparation***/

        Response response = Pets.createNewPet(requestBody.toString(),200);

        /*** ^^Create Pet for updating***/

        JSONObject requestBodyUpdate = JsonUtilities.getJsonFileObj("src/main/resources/Updatepet.json");
        requestBodyUpdate.put("id", response.jsonPath().get("id").toString());

        /*** ^^data preparation for updating***/
        Response updateResponse=Pets.updatePet(200,requestBodyUpdate.toString());
        /*** update api call***/

        System.out.println(updateResponse.body().asString());
        Assert.assertEquals(expectedName,updateResponse.jsonPath().get("name").toString());
        Assert.assertEquals(expectedtagName, updateResponse.jsonPath().get("tags[0].name").toString());
        Assert.assertEquals(expectedFamily, updateResponse.jsonPath().get("category.name").toString());

        /*** Update api test assertions***/

    }
}




