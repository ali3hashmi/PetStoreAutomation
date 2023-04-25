package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Pet;
import io.restassured.response.Response;

public class PetEndPoints {


	public static Response addNewPet(Pet payload) {

		Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)

				.when()
				.post(Routes.petPostURL);

		return response;

	}
}
