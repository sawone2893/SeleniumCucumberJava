package base.modal;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import base.actionInterface.IAction;

public class SeleniumActions implements IAction {
	
	private WebDriver driver=null;
	private static WebElement element=null;
	private int maxWaitTime=120;
	

	@Override
	public void initialize(String browserName, boolean isHeadless) {
		launchBrowser(browserName);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(maxWaitTime, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}
	
	public void launchBrowser(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome": {
			System.out.println("Initializing "+browserName+" browser...");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriverV117.0.5938.92.exe");
			driver = new ChromeDriver();
			break;
		}
		case "firefox": {
			System.out.println("Initializing "+browserName+" browser...");
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		case "edge": {
			System.out.println("Initializing "+browserName+" browser...");
			System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver");
			driver = new EdgeDriver();
			break;
		}
		default:
			Assert.assertTrue(false, "Unsupported Browse Name: " + browserName);
		}
	}

	@Override
	public void closeBrowser() {
		System.out.println("Closing broswer...");
		driver.close();	
		driver.quit();	
	}

	@Override
	public void openURL(String url) {
		System.out.println("Opening url:"+url);
		driver.get(url);
	}

	@Override
	public void refresh() {
		driver.navigate().refresh();	
	}

	@Override
	public void clickElement(String locatorType,String locatorValue) {
		try {
			element=findElement(locatorType,locatorValue);
			waitUntill(element,"clickable");
			element.click();
		}catch(Exception e) {
			Assert.assertTrue(false, "WebElement["+locatorValue+"] is not clickable.");
		}
		
		
	}

	@Override
	public void enterTextOnElement(String locatorType,String locatorValue,String textToEnter) {
		element=findElement(locatorType,locatorValue);
		if(isElementEnabled(element)) {
			element.sendKeys(textToEnter);
		}else {
			Assert.assertTrue(false, "WebElement["+locatorValue+"] is not enabled.");
		}
		
	}

	@Override
	public void waitUntill(final WebElement element,final String conditionName) {
		try {
			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
					switch(conditionName.toLowerCase()) {
					case "clickable":{
						wait.until(ExpectedConditions.elementToBeClickable(element));
						break;
					}
					case "Invisible":{
						wait.until(ExpectedConditions.invisibilityOf(element));
						break;
					}	
					case "visible":{
						wait.until(ExpectedConditions.visibilityOf(element));
						break;
					}	
					case "selected":{
						wait.until(ExpectedConditions.elementToBeSelected(element));
						break;
					}
					default:
						Assert.assertTrue(false, "Unsupported wait untill action: " + conditionName);
					}
					return true;
				}
			};
			setFluentWait(function);
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}
		
	}
	
	public void setFluentWait(Function<WebDriver,Boolean> function) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.pollingEvery(500, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(maxWaitTime,TimeUnit.SECONDS);
		fluentWait.ignoring(NoSuchElementException.class);
		fluentWait.until(function);	
	}
	
	public void setWebDriverWait(Function<WebDriver,Boolean> function,int time) {
		Wait<WebDriver> wait = new WebDriverWait(driver, maxWaitTime);
		wait.until(function);
		
	}
	
	public void waitForPageLoad(int time) {

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.
				out.println("Current Window State  : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		};

		setWebDriverWait(function,time);
	}
	
	@Override
	public void waitForElement(int seconds) {
		int time = seconds * 1000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	@Override
	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
		
	}

	@Override
	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
		
	}

	@Override
	public String getAttributeValue(WebElement element,String attributeName) {
		return element.getAttribute(attributeName);
	}

	@Override
	public String getURL() {
		return driver.getCurrentUrl();
		
	}

	@Override
	public void takeScreenshot(String screenshotName) {
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File(System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] takeScreenshotAsBytes() {
		byte[] screenshotAsBytes = null;
		try {
		screenshotAsBytes=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return screenshotAsBytes;
	}

	@Override
	public void jsClick(WebElement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WebElement findElement(String locatorType, String locatorValue) {
		WebElement element = null;
		try {
			switch (locatorType.toUpperCase()) {
			case ("ID"):
				element = driver.findElement(By.id(locatorValue));
			case ("LINK"):
				element = driver.findElement(By.linkText(locatorValue));
			case ("XPATH"):
				element = driver.findElement(By.xpath(locatorValue));
			case ("NAME"):
				element = driver.findElement(By.name(locatorValue));
			case ("CLASS"):
				element = driver.findElement(By.className(locatorValue));
			default:
				Assert.assertTrue(false, "Unsupported Locator Type: " + locatorType);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.assertTrue(false,"NoSuchElementException");
			
		} catch (WebDriverException e) {
			e.printStackTrace();
			Assert.assertTrue(false,"WebDriverException");
		}
		return element;

	}

}
