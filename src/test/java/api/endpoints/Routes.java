package api.endpoints;

/* Swagger URI : https://petstore.swagger.io
 * 
 * Create User(post) https://petstore.swagger.io/v2/user
 * Get User(get) https://petstore.swagger.io/v2/user/{username}
 * Update User(put) https://petstore.swagger.io/v2/user/{username}
 * Delete User(delete) https://petstore.swagger.io/v2/user/{username}
 */
public class Routes {
	
	//routes class will contains only URLs
	
	public static String baseURL ="https://petstore.swagger.io/v2";
	
	//user module
	
	public static String postURL = baseURL + "/user";
	public static String getURL =baseURL + "/user/{username}";
	public static String updateURL =baseURL + "/user/{username}";
	public static String deleteURL =baseURL + "/user/{username}";
	
	//Store module
	
	public static String storePostURL=baseURL +"/store/order";
	public static String storeGetURL=baseURL +"/store/order/{orderId}";
	public static String storeDeleteURL=baseURL +"/store/order/{orderId}";
	
	
	//Pet module
	
	public static String petPostURL=baseURL + "/pet";

}
