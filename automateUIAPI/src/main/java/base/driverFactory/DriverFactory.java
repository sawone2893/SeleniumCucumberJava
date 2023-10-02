package base.driverFactory;

import base.actionInterface.IAction;
import base.modal.SeleniumActions;

public class DriverFactory {
	
	static IAction action=null;
	
	public static IAction driverInstance(String driverName) {
		switch (driverName.toLowerCase()) {
		case "selenium":{
			System.out.println("Create Instance for "+driverName);
			action = new SeleniumActions();
			break;
		}	
		default:{
			System.out.println("Unsupported Driver Name: " + driverName);
		}	
		}
		return action;
	}

}
