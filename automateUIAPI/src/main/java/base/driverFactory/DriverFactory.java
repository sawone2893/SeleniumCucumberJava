package base.driverFactory;

import base.actionInterface.IActionAPI;
import base.actionInterface.IActionUI;
import base.modal.RestAssuredActions;
import base.modal.SeleniumActions;

public class DriverFactory {
	
	static IActionUI actionUI=null;
	static IActionAPI actionAPI=null;
	
	public static IActionUI uiDriverInstance(String uiDriverName) {
		switch (uiDriverName.toLowerCase()) {
		case "selenium":{
			System.out.println("Create Instance for "+uiDriverName);
			actionUI = new SeleniumActions();
			break;
		}
		default:{
			System.out.println("Unsupported UI Driver Name: " + uiDriverName);
		}	
		}
		return actionUI;
	}	
	
	public static IActionAPI apiDriverInstance(String apiDriverName) {
		switch (apiDriverName.toLowerCase()) {
		case "restassured":{
			System.out.println("Create Instance for "+apiDriverName);
			actionAPI = new RestAssuredActions();
			break;
		}
		default:{
			System.out.println("Unsupported API Driver Name: " + apiDriverName);
		}	
		}
		return actionAPI;
	}	

}
