package api.test;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTest {
	
	Faker faker;
	Store storePayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUP() {
		
		faker=new Faker();
		storePayload=new Store();
		
		//generate faker data for store
		
		storePayload.setId(faker.idNumber().hashCode());
		storePayload.setPetId(faker.idNumber().hashCode());
		storePayload.setQuantity(faker.number().numberBetween(1, 25));
		storePayload.setShipDate("2023-04-20T06:31:27.731Z");
		storePayload.setStatus("placed");
		storePayload.setComplete("true");
		
		//logs
		logger =LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testCreateOrder() {
		
		logger.info("Creating new order");
		Response response=StoreEndPoints.createOrder(storePayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("New order has been created");
	}

	@Test(priority = 2)
	public void testGetOrderByOrderId() {
		
		logger.info("Reading order details by order id");
		Response response=StoreEndPoints.getOrder(this.storePayload.getId());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(this.storePayload.getId() + "order details has been read");
		
	}
	
	@Test(priority = 3)
	public void testDeleteOrderByOrderId() {
		
		logger.info("Deleting order details by order id");
		Response response=StoreEndPoints.deleteOrder(this.storePayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Deleting order details has been successfull");
	}
}
