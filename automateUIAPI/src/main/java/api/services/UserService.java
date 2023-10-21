package api.services;

import org.json.JSONArray;
import org.testng.Assert;

import api.endpoint.Routes;
import base.actionInterface.IActionAPI;
import base.driverFactory.DriverFactory;
import utililties.PropertyManager;

public class UserService {
	
	public static IActionAPI globalAPIDriver=DriverFactory.apiDriverInstance(PropertyManager.getAnyProperty("config","APIDRIVERNAME"));
	public static JSONArray userResponse=null;
	
	public static void getUsers() {
		userResponse= globalAPIDriver.getRequest(Routes.GET_USER);
	}
	
	public static void validateUserStatusCode(int expectedStatusCode) {
		Assert.assertEquals(globalAPIDriver.getStatusCode(), expectedStatusCode,"Status code is not matched");
	}
	
	public static void validateUserErrorMessage(String expectedMsg) {
		Assert.assertEquals(globalAPIDriver.getErrorMessage(), expectedMsg,"Error Message is not matched");
	}
	
	public static void printResponse() {
		System.out.println("User response: "+userResponse);
	}
	
	public static void validateGetUserResponse() {
		userResponse.get(0);
	}
	
}
