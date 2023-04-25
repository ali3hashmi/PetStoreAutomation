package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
//created this class to perform create,read,update and delete requests to the user API

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {


	public static Response createUser(User payload){

		Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)

				.when()
				.post(Routes.postURL);

		return response;

	}

	public static Response readUser(String username) {

		Response response=given()
				.pathParam("username", username)

				.when()
				.get(Routes.getURL);

		return response;
	}

	public static Response updateUser(String username,User payload){

		Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.pathParam("username", username)
				.body(payload)

				.when()
				.put(Routes.updateURL);

		return response;

	}
	
	public static Response deleteUser(String username) {

		Response response=given()
				.pathParam("username", username)

				.when()
				.delete(Routes.deleteURL);

		return response;
	}

}
