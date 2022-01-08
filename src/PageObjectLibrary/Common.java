package PageObjectLibrary;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {

	WebDriver driver;
	String parentWindow, childWindow;
	List<String> strProductValues = new ArrayList<String>();
	List<String> strTitleValues = new ArrayList<String>();

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

}
