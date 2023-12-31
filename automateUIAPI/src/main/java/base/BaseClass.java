package base;

import base.actionInterface.IActionUI;
import base.driverFactory.DriverFactory;
import utililties.PropertyManager;

public class BaseClass {
	
	public static IActionUI globalUIDriver=null;
	
	public static void appStart() {
		globalUIDriver=DriverFactory.uiDriverInstance(PropertyManager.getAnyProperty("config","UIDRIVERNAME"));
		globalUIDriver.initialize(PropertyManager.getAnyProperty("config","BROWSERNAME"),false);
		globalUIDriver.openURL(PropertyManager.getAnyProperty("config","APPURL"));
	}
	
	public static void closeBrowser() {
		globalUIDriver.closeBrowser();
	}
	
	public static void click(String locatorValue) {
		globalUIDriver.clickElement(locatorValue);
	}
	
	public static void type(String locatorValue,String textToEnter) {
		globalUIDriver.enterTextOnElement(locatorValue,textToEnter);
	}
	
	public static byte[] snapAsBytes() {
		return globalUIDriver.takeScreenshotAsBytes();
	}
	
	public static void waitForPageToBeLoad(int timeInSeconds) {
		globalUIDriver.waitForPageLoad(timeInSeconds);
	}
	
	public static boolean isElementDisplayedOrEnabledOrSelected(String locatorValue,String state) {
		return globalUIDriver.isElementDisplayedOrEnabledOrSelected(locatorValue,state);
	}
	public static void scrollToElement(String locatorValue) {
		globalUIDriver.scrollToElement(locatorValue);
	}
	
	public static String replacePath(String locatorIdentifier, String values) {
		String xpath = PropertyManager.getAnyProperty("locators", locatorIdentifier);
		String newPath=xpath;
		if (values.contains("~")) {
			String[] parameters = values.split("~");
			int j = parameters.length;
			// logger_method().info(j);
			for (int i = 0; i < j; i++) {
				String replacement = parameters[i];
				newPath = xpath.replace("$" + i + "$", replacement);
				xpath = newPath;
			}
		}
		else {
			newPath = xpath.replace("$0$", values);
			xpath = newPath;
		}
		return newPath;
	}
	
	public static void waitUntill(String locatorValue,String conditionName) {
		globalUIDriver.waitUntill(locatorValue,conditionName);
	}
	
	public static String getElementAttribute(String locatorValue,String attributeName) {
		return globalUIDriver.getAttributeValue(locatorValue,attributeName);
	}
	
	public static void waitForElement(int timeInSeconds) {
		globalUIDriver.waitForElement(timeInSeconds);
	}
	
	public static void jsClick(String locatorValue) {
		globalUIDriver.jsClick(locatorValue);
	}
	
	public static String getElementText(String locatorValue) {
		return globalUIDriver.getText(locatorValue);
	}
	
}
