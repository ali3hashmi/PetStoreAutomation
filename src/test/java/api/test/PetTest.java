package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import io.restassured.response.Response;

public class PetTest {

	Faker faker;
	Pet petPayload;

	public Logger logger;

	@BeforeClass
	public void setUP() {

		faker=new Faker();
		petPayload =new Pet();

		//generate faker data for pet
		petPayload.setPetID(faker.idNumber().hashCode());
		//petPayload.setCatID(faker.number().numberBetween(1, 100));
		petPayload.setCatName("Animal");
		//petPayload.setCategory(null);
	//	String[] categoryArr = {"0","string"};
	//	petPayload.setCategory(categoryArr);
		
		petPayload.setPetName(faker.animal().name());
		String[] photourl= {"string"};
		petPayload.setPhotoURLS(photourl);
		
		String[] tagsArr={"0","string"};
		petPayload.setTags(tagsArr);
		//petPayload.settID(faker.idNumber().hashCode());
		//petPayload.settName(faker.animal().name());
		petPayload.setStatus("available");
		
		
	}
	
	@Test(priority = 1)
	public void testAddNewPet() {
		
		logger.info("Adding new pet");
		Response response=PetEndPoints.addNewPet(petPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Added new pet details");
	}

}
