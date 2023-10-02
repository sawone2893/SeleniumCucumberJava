package base;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import base.actionInterface.IAction;
import base.driverFactory.DriverFactory;

public class BaseClass {
	
	public static IAction globalDriver=null;

	public static void appStart() {
		globalDriver=DriverFactory.driverInstance(BaseClass.getAnyProperty("config","DRIVERNAME"));
		globalDriver.initialize(BaseClass.getAnyProperty("config","BROWSERNAME"),false);
		globalDriver.openURL(BaseClass.getAnyProperty("config","APPURL"));
	}
	
	public static void closeBrowser() {
		globalDriver.closeBrowser();
	}
	
	public static void click(String locatorValue) {
		globalDriver.clickElement(locatorValue);
	}
	
	public static void type(String locatorValue,String textToEnter) {
		globalDriver.enterTextOnElement(locatorValue,textToEnter);
	}
	
	public static byte[] snapAsBytes() {
		return globalDriver.takeScreenshotAsBytes();
	}
	
	public static void waitForPageToBeLoad(int timeInSeconds) {
		globalDriver.waitForPageLoad(timeInSeconds);
	}
	
	public static boolean isElementDisplayedOrEnabledOrSelected(String locatorValue,String state) {
		return globalDriver.isElementDisplayedOrEnabledOrSelected(locatorValue,state);
	}
	public static void scrollToElement(String locatorValue) {
		globalDriver.scrollToElement(locatorValue);
	}
	
	public static String getAnyProperty(String propFileName,String configPropName) {
		String value = null;
		try {
			Properties property = new Properties();
			String filepath=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\"+propFileName+".properties";
			FileInputStream fis = new FileInputStream(new File(filepath));
			property.load(fis);
			value = property.getProperty(configPropName);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String replacePath(String locatorIdentifier, String values) {
		String xpath = BaseClass.getAnyProperty("locators", locatorIdentifier);
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
		globalDriver.waitUntill(locatorValue,conditionName);
	}
	
	public static String getElementAttribute(String locatorValue,String attributeName) {
		return globalDriver.getAttributeValue(locatorValue,attributeName);
	}
	
	public static void waitForElement(int timeInSeconds) {
		globalDriver.waitForElement(timeInSeconds);
	}
	
}
