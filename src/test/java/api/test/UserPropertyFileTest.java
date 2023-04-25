package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsUsingPropertyFile;
import api.payload.User;
import io.restassured.response.Response;

public class UserPropertyFileTest {

	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUP() {
		
		faker=new Faker();
		userPayload=new User();
		
		//generate faker data
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("------------Creating user----------");
		Response response=UserEndPointsUsingPropertyFile.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("--------user is created--------------");
		
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("------------Reading user info----------");
		Response response=UserEndPointsUsingPropertyFile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("------------User info is displayed----------");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		//update data using payload
		
				
		//generate faker data
		//userPayload.setFirstname(faker.name().firstName());
	//	userPayload.setLastname(faker.name().lastName());
		logger.info("------------Updating user----------");
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response=UserEndPointsUsingPropertyFile.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("------------User is updated----------");
		//checking the data after updation
		Response responseAfterUpdate=UserEndPointsUsingPropertyFile.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("------------Deleting user----------");
		Response response=UserEndPointsUsingPropertyFile.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("------------User deleted----------");
	}
}
