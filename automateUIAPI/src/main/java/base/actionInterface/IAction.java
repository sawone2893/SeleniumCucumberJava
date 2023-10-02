package base.actionInterface;

import org.openqa.selenium.WebElement;

public interface IAction {
	
	public void initialize(String browserChannelName, boolean isHeadless);
	public void closeBrowser();
	public void openURL(String url);
	public void refresh();
	public void clickElement(String locatorType,String locatorValue);
	public void enterTextOnElement(String locatorType,String locatorValue,String textToEnter);
	public void waitForElement(int seconds);
	public boolean isElementDisplayed(WebElement element);
	public boolean isElementEnabled(WebElement element);
	public boolean isElementSelected(WebElement element);
	public String getAttributeValue(WebElement element,String attributeName);
	public String getURL();
	public void takeScreenshot(String screenshotName);
	public void jsClick(WebElement element);
	public WebElement findElement(String locatorType,String locatorValue);
	public void waitUntill(final WebElement element,final String conditionName);
	public byte [] takeScreenshotAsBytes();
	public void waitForPageLoad(int timeInSeconds);
}
