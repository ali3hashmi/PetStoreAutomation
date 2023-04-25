package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Store;
import io.restassured.response.Response;

public class StoreEndPoints {


	public static Response createOrder(Store payload) {

		Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)

				.when()
				.post(Routes.storePostURL);

		return response;

	}

	public static Response getOrder(int orderId) {

		Response response=given()
				.pathParam("orderId", orderId)

				.when()
				.get(Routes.storeGetURL);

		return response;

	}
	public static Response deleteOrder(int orderId) {

		Response response=given()
				.pathParam("orderId", orderId)

				.when()
				.delete(Routes.storeGetURL);

		return response;

	}

}
