package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import base.actionInterface.IAction;
import base.driverFactory.DriverFactory;

public class BaseTestNG{

	@BeforeSuite
	public void beforeSuite() {
		BaseClass.appStart();
	}

	@AfterSuite
	public void afterSuite() {
		BaseClass.closeBrowser();
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {

	}

}
