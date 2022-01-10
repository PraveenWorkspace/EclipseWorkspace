package PageObjectLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Common {

	WebDriver driver;
	Actions actions;
	String parentWindow, childWindow;
	static String actualReportName;
	List<String> strProductValues = new ArrayList<String>();
	List<String> strTitleValues = new ArrayList<String>();
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter reporter = new ExtentSparkReporter("./Results/status.html");

	public Common(WebDriver driver) {
		this.driver = driver;
	}

	// Method to click on element
	public boolean clickOnElement(String locator) {

		boolean result = false;
		try {

			WebElement webElement = driver.findElement(By.xpath(locator));
			webElement.click();
			result = true;

		} catch (Exception e) {

		}
		return result;
	}

	// Method to set text
	public boolean setTextByXpath(String locator, String value) {

		boolean result = false;
		try {

			WebElement webElement = driver.findElement(By.xpath(locator));
			webElement.sendKeys(value);
			result = true;

		} catch (Exception e) {

		}
		return result;
	}

	// Method to select Dropdown values
	public boolean selectDropDown(String locator, String value) {

		boolean result = false;
		try {

			Select dropDown = new Select(driver.findElement(By.xpath(locator)));
			dropDown.selectByValue(value);
			result = true;

		} catch (Exception e) {

		}
		return result;
	}

	// Method to check for the element
	public boolean isElementPresent(String locator) {

		boolean result = false;
		try {

			WebElement webElement = driver.findElement(By.xpath(locator));

			result = true;

		} catch (Exception e) {

		}
		return result;
	}

	// Method for wait
	public void implictWait(int i) {
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);
	}

	// Method for wait
	public void waitUntilElementClickable(String locator, int i) {
		WebDriverWait wait = new WebDriverWait(driver, i);

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	// Method to launch URL
	public WebDriver launchURL() throws Exception {

		// Launch Chrome browser
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://subscription.packtpub.com/");
		driver.manage().window().maximize();
		return driver;
	}

	// Method to set text
	public boolean setTextByID(String locator, String value) {

		boolean result = false;
		try {

			WebElement webElement = driver.findElement(By.id(locator));
			webElement.sendKeys(value);
			result = true;

		} catch (Exception e) {

		}
		return result;
	}

	// switch to the child window
	public boolean switchToChildWindow() {
		parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String childwindow : allWindows) {

			if (!parentWindow.equalsIgnoreCase(childwindow)) {
				driver.switchTo().window(childwindow);
			}
		}
		return false;
	}

	// switch to the parent window
	public void switchToParentWindow() {
		driver.close();
		driver.switchTo().window(parentWindow);
	}

	// Method to click on element
	public boolean clickOnElementById(String locator) {

		boolean result = false;
		try {

			WebElement webElement = driver.findElement(By.id(locator));
			webElement.click();
			result = true;

		} catch (Exception e) {

		}
		return result;
	}

	// Method to get the List of Elements
	public List<String> getListOfElementsByClass(String locator) {
		waitUntilElementClickable(locator, 25);
		List<WebElement> strvalues = driver.findElements(By.xpath(locator));
		for (WebElement elements : strvalues) {
			strProductValues.add(elements.getText());
		}
		return strProductValues;
	}

	// Method to get the List of Elements
	public List<String> getListOfElementsByXpathWithoutWait(String locator) {
		List<WebElement> strTitles = driver.findElements(By.xpath(locator));
		for (WebElement elements : strTitles) {
			strTitleValues.add(elements.getText());
		}
		return strTitleValues;
	}

	// Method to get text
	public String getTextByXpath(String locator) {

		try {

			WebElement webElement = driver.findElement(By.xpath(locator));
			return webElement.getText();

		} catch (Exception e) {

		}
		return childWindow;
	}

	// check the element is in the correct Position
	public Boolean checkTheElementPosition(String locator, String strValueX, String strValueY) {
		try {
			actions = new Actions(driver);
			WebElement webElement = driver.findElement(By.xpath(locator));
			Point point = webElement.getLocation();
			int xcord = point.getX();
			int ycord = point.getY();
			return ((String.valueOf(xcord)).equals(strValueX) && (String.valueOf(ycord)).equals(strValueY));
		} catch (Exception e) {

		}
		return true;
	}
	
	//check the colour of the elements 
	public boolean checkTheColourOfTheElement(String locator, String expectedColor) {
		try {
			WebElement webElement = driver.findElement(By.xpath(locator));
			String actualclr = webElement.getCssValue("color");
			String exptclr = Color.fromString(actualclr).asHex();
			return expectedColor.equals(exptclr);
		} catch (Exception e) {

		}
		return false;
	}
	
	//create the Reports for the test cases
	public void reportingForTests(boolean result, String createTest, String passedInfo, String passedResult,String failedResult) {
		ExtentTest createtests = extent.createTest(createTest);
		if(result) {
		extent.attachReporter(reporter);	
		createtests.pass(passedInfo);
	    createtests.log(Status.PASS, passedResult);
	    extent.flush();
		}
		else if(!result) {
			extent.attachReporter(reporter);
			createtests.fail(failedResult);
		    createtests.log(Status.FAIL, failedResult);
		    createtests.fail(MediaEntityBuilder.createScreenCaptureFromPath("./Results/FailScreenshot.png").build());
		    extent.flush();
		}
	}
}
