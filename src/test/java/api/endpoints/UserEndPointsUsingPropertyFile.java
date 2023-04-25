package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
//created this class to perform create,read,update and delete requests to the user API

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsUsingPropertyFile {


	//method created for getting url's from property file
	public static ResourceBundle getURL(){
		
		ResourceBundle routes=ResourceBundle.getBundle("routes");//load property file
		return routes;
	}
	public static Response createUser(User payload){

		String postURL= getURL().getString("postURL");
		Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)

				.when()
				.post(postURL);

		return response;

	}

	public static Response readUser(String username) {

		String getURL= getURL().getString("getURL");
		
		Response response=given()
				.pathParam("username", username)

				.when()
				.get(getURL);

		return response;
	}

	public static Response updateUser(String username,User payload){

		String updateURL= getURL().getString("updateURL");
		
		Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.pathParam("username", username)
				.body(payload)

				.when()
				.put(updateURL);

		return response;

	}
	
	public static Response deleteUser(String username) {

		String deleteURL= getURL().getString("deleteURL");
		
		Response response=given()
				.pathParam("username", username)

				.when()
				.delete(deleteURL);

		return response;
	}

}
