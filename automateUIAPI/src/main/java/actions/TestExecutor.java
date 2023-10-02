package actions;

import org.testng.Assert;

import base.BaseClass;

public class TestExecutor{

	private static TestExecutor testExecutor=null;
	
	private TestExecutor() {}
	
	public static TestExecutor getInstance() {
		if(testExecutor==null) {
			testExecutor=new TestExecutor();
		}
		return testExecutor;
	}

	public void executeAction(TestSteps steps) {
		switch (steps.getAction()) {
		case "Click":
			BaseClass.click(steps.getLocatorType(), steps.getLocator());
		case "EnterValue":
			BaseClass.type(steps.getLocatorType(), steps.getLocator(),steps.getValue());
			break;
		case "WaitForPageToBeLoad":
			BaseClass.waitForPageToBeLoad(Integer.parseInt(steps.getValue()));
			break;
		default:
			Assert.assertTrue(false, steps.getAction() + "is not defined.Please define your action in the TestExecutor Class.");
		}
	}
	
	public String getLocator(String locatorIdentifier, String parameters){
		return BaseClass.replacePath(locatorIdentifier,parameters);
	}

}
