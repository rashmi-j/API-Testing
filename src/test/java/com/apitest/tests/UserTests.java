package com.apitest.tests;

import com.apitest.Utilities.JsonUtilities;
import com.apitest.endPoints.Users;
import com.jayway.restassured.response.Response;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class UserTests {

     Date date;

     @Test(description = "Verify create api")
    public void createUser(){
         date = new Date();
         JSONObject requestBody = JsonUtilities.getJsonFileObj("src/main/resources/CreateSingleUser.json");
          String userName = "User"+date.getTime();
          String firstName = userName;
          String lastName = "boogie";
          String phoneNumber = "1"+date.getTime();
          String email = userName+"@gmail.com";
          String password = userName;
          requestBody.put("username",userName);
          requestBody.put("firstName",firstName);
          requestBody.put("lastName",lastName);
          requestBody.put("email",email);
          requestBody.put("password",password);
          requestBody.put("phone",phoneNumber);

          System.out.println(requestBody.toString());

          /***^Data preparation**/

          Response createUserResponse=Users.createUser(requestBody.toString(), 200);
          System.out.println(createUserResponse.body().asString());
          /***^Api call**/

          Assert.assertFalse(createUserResponse.jsonPath().get("message").toString().isEmpty());
          /***^Assertions**/
     }

     @Test(description = "get user by user name")
     public void getUserByUserName(){

          date = new Date();
          JSONObject requestBody = JsonUtilities.getJsonFileObj("src/main/resources/CreateSingleUser.json");
          String userName = "UserName"+date.getTime();
          String firstName = userName;
          String lastName = "boogie";
          String phoneNumber = "1"+date.getTime();
          String email = userName+"@gmail.com";
          String password = userName;
          requestBody.put("username",userName);
          requestBody.put("firstName",firstName);
          requestBody.put("lastName",lastName);
          requestBody.put("email",email);
          requestBody.put("password",password);
          requestBody.put("phone",phoneNumber);

          Response createUserResponse = Users.createUser(requestBody.toString(),200);
          /***^Data preparation**/

          Response getResponse = Users.getUser("userName",200);

          System.out.println(getResponse.body().asString());
          /***^API call**/

          String actualUserName = getResponse.jsonPath().get("userName").toString();
          String id = getResponse.jsonPath().get("id").toString();
          String actualEmail = getResponse.jsonPath().get("email").toString();
          /***^Assertions**/

          Assert.assertEquals(userName,actualUserName);
          Assert.assertEquals(email,actualEmail);
          Assert.assertFalse(id.isEmpty());
     }
}
