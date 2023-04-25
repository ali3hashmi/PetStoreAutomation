package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTestUser {

	Faker faker;
	User userPayload;
	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID,String userName,String fName,String lName,String userEmail,String pwd,String phone) {
		
		//Faker faker=new Faker();
		userPayload =new User();
		
		//userPayload.setId(faker.idNumber().hashCode());
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fName);
		userPayload.setLastname(lName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response=UserEndPoints.createUser(userPayload);
		 response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 2,dataProvider = "userNames",dataProviderClass = DataProviders.class)
	public void testGetUserByName(String userName) {
		
		Response response=UserEndPoints.readUser(userName);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
/*	@Test(priority = 3,dataProvider = "userNames",dataProviderClass = DataProviders.class)
	public void testUpdateUserByName(String userName) {
		
		faker=new Faker();
		userPayload =new User();
		
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(userName, userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate=UserEndPoints.readUser(userName);
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	*/
	
	
	@Test(priority = 4,dataProvider = "userNames",dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userName) {
		
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
