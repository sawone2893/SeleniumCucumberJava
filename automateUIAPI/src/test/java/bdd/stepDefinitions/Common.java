package bdd.stepDefinitions;

import actions.TestExecutor;
import actions.TestSteps;
import factory.FactoryRegistry;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Common {
	
	TestExecutor testExecutor=FactoryRegistry.getTestExecutor();
	TestSteps steps=FactoryRegistry.getTestSteps();
	
	/*Example:
	 * Then I "Click" on "XPATH" "Button" with values "Login"
	   Then I "WaitUntillElementDisappear" on "XPATH" "Button" with values "Login"
	   Then I "WaitUntillElementAppear" on "XPATH" "Button" with values "Login"
	 */
	@Then("^I \"([^\"]*)\" on \"([^\"]*)\" \"([^\"]*)\" with values \"([^\"]*)\"$")
	public void iOnWithValues(String action,String locatorType,String locatorIdentifier,String param){
		steps.setAction(action);
		steps.setLocatorType(locatorType);
		steps.setLocator(testExecutor.getLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	}
	
	/*Example:
	 * Then I "WaitForPageLoadState" "load"
	 */
	@Given("^I \"([^\"]*)\" \"([^\"]*)\"$")
	public void i(String action, String value){
		steps.setAction(action);
		steps.setValue(value);
		testExecutor.executeAction(steps);
	}
	
	/*Example:
	 * When I "EnterValue" "Shabbir" for "XPATH" "TextField" with values "Username"
	 */
	@When("^I \"([^\"]*)\" \"([^\"]*)\" for \"([^\"]*)\" \"([^\"]*)\" with values \"([^\"]*)\"$")
	public void iForWithValues(String action,String locatorType,String value,String locatorIdentifier,String param){
		steps.setAction(action);
		steps.setValue(value);
		steps.setLocatorType(locatorType);
		steps.setLocator(testExecutor.getLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	    
	}

	/*Example:
	 * Then I "VerifyVisibility" is "true" for "XPATH" "TagWithText" with values "span~<OrderConfirmationText>"
	 */
	@Then("^I \"([^\"]*)\" is \"([^\"]*)\" for \"([^\"]*)\" with values \"([^\"]*)\"$")
	public void iIsForWithValues(String action,String value,String locatorType,String locatorIdentifier,String param){
		steps.setAction(action);
		steps.setValue(value);
		steps.setLocatorType(locatorType);
		steps.setLocator(testExecutor.getLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	}
	
	/*Example:
	* Then I "WaitForElement" "5" seconds
	*/
	@Then("^I \"([^\"]*)\" \"([^\"]*)\" seconds$")
	public void iSeconds(String action, String value){
		steps.setAction(action);
		steps.setValue(value);
		testExecutor.executeAction(steps);
	    
	}

}
