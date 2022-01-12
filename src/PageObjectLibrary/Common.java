package PageObjectLibrary;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	List<String> strProductValues = new ArrayList<String>();
	List<String> strTitleValues = new ArrayList<String>();
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter reporter;
	ExtentTest createtests;
	FileInputStream fis = null;
	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;

	public Common(WebDriver driver) {
		// Initialize the driver
		this.driver = driver;
	}

	// Method to click on element
	public boolean clickOnElement(String locator) {
		// set the result to false
		boolean result = false;
		try {
			// used to find the element
			WebElement webElement = driver.findElement(By.xpath(locator));
			// click on the Web Element
			webElement.click();
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	// Method to set text
	public boolean setTextByXpath(String locator, String value) {
		// set the result to false
		boolean result = false;
		try {
			// used to find the element
			WebElement webElement = driver.findElement(By.xpath(locator));
			// send the value to Web Element
			webElement.sendKeys(value);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	// Method to select Dropdown values
	public boolean selectDropDown(String locator, String value) {
		// set the result to false
		boolean result = false;
		try {
			// used to select dropdown
			Select dropDown = new Select(driver.findElement(By.xpath(locator)));
			// select the dropdown option by value
			dropDown.selectByValue(value);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	// Method to check for the element
	public boolean isElementPresent(String locator) {
		// set the result to false
		boolean result = false;
		try {
			// used to find the web element
			WebElement webElement = driver.findElement(By.xpath(locator));
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	// Method for wait
	public void implictWait(int i) {
		// wait for the Element
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);
	}

	// Method for wait
	public void waitUntilElementClickable(String locator, int i) {
		// Initiating the webdriver wait
		WebDriverWait wait = new WebDriverWait(driver, i);
		// wait for the element for particular time
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	// Method to launch URL
	public WebDriver launchURL() throws Exception {
		// get the path of excel
		getFilePath("src/TestData/DataDetails.xlsx");
		// get the chrome path from excel
		String strChromePath = getCellData("sheet1", "Chrome File", 1);
		// get URL from excel
		String strURL = getCellData("sheet1", "URL", 1);
		// Launch Chrome browser
		System.setProperty("webdriver.chrome.driver", strChromePath);
		// set the chrome driver
		driver = new ChromeDriver();
		// launch the url
		driver.get(strURL);
		// maximize the window
		driver.manage().window().maximize();
		// return the driver
		return driver;
	}

	// Method to set text
	public boolean setTextByID(String locator, String value) {
		// set the result to false
		boolean result = false;
		try {
			// find the web element
			WebElement webElement = driver.findElement(By.id(locator));
			// set the value of the Web Element
			webElement.sendKeys(value);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	// switch to the child window
	public boolean switchToChildWindow() {
		// get the parent window iD
		parentWindow = driver.getWindowHandle();
		// get all window Ids
		Set<String> allWindows = driver.getWindowHandles();
		for (String childwindow : allWindows) {
			// check parent window and child window ID
			if (!parentWindow.equalsIgnoreCase(childwindow)) {
				// switch to child window
				driver.switchTo().window(childwindow);
			}
		}
		return false;
	}

	// switch to the parent window
	public void switchToParentWindow() {
		// close the current tab
		driver.close();
		// switch to parent window
		driver.switchTo().window(parentWindow);
	}

	// Method to click on element
	public boolean clickOnElementById(String locator) {
		// set the result to false
		boolean result = false;
		try {
			// To find the web element
			WebElement webElement = driver.findElement(By.id(locator));
			// click on web element
			webElement.click();
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	// Method to get the List of Elements
	public List<String> getListOfElementsByClass(String locator) {
		// wait for the element
		waitUntilElementClickable(locator, 25);
		// get the elements and assign to List
		List<WebElement> strvalues = driver.findElements(By.xpath(locator));
		for (WebElement elements : strvalues) {
			// add values to the list
			strProductValues.add(elements.getText());
		}
		return strProductValues;
	}

	// Method to get the List of Elements
	public List<String> getListOfElementsByXpathWithoutWait(String locator) {
		// get the elements and assign to List
		List<WebElement> strTitles = driver.findElements(By.xpath(locator));
		for (WebElement elements : strTitles) {
			// add values to the list
			strTitleValues.add(elements.getText());
		}
		return strTitleValues;
	}

	// Method to get text
	public String getTextByXpath(String locator) {
		try {
			// To find the web element
			WebElement webElement = driver.findElement(By.xpath(locator));
			// to get Text from web element
			return webElement.getText();
		} catch (Exception e) {
		}
		return childWindow;
	}

	// check the element is in the correct Position
	public Boolean checkTheElementPosition(String locator, String strValueX, String strValueY) {
		try {
			actions = new Actions(driver);
			// To find the web element
			WebElement webElement = driver.findElement(By.xpath(locator));
			// Get the Location of the web element
			Point point = webElement.getLocation();
			/// get X cord
			int xcord = point.getX();
			/// get Y cord
			int ycord = point.getY();
			// check the values with actual values
			return ((String.valueOf(xcord)).equals(strValueX) && (String.valueOf(ycord)).equals(strValueY));
		} catch (Exception e) {
		}
		return true;
	}

	// check the colour of the elements
	public boolean checkTheColourOfTheElement(String locator, String expectedColor) {
		try {
			// To find the web element
			WebElement webElement = driver.findElement(By.xpath(locator));
			// get the color of the web element
			String actualclr = webElement.getCssValue("color");
			// change the value to hexa decimal
			String exptclr = Color.fromString(actualclr).asHex();
			// check the value with actual value
			return expectedColor.equals(exptclr);
		} catch (Exception e) {
		}
		return false;
	}

	// create the Reports for the test cases
	public void reportingForTests(boolean result, String checkdeatils, String passedInfo, String passedResult,
			String failedResult) {
		// check for the result value
		if (result) {
			// attach the report
			extent.attachReporter(reporter);
			// set Status log
			createtests.log(Status.PASS, checkdeatils);
			// get the pass info
			createtests.pass(passedInfo);
			// set pass log
			createtests.log(Status.PASS, passedResult);
			extent.flush();
		} else if (!result) {
			// attach the report
			extent.attachReporter(reporter);
			// set Status log
			createtests.log(Status.FAIL, checkdeatils);
			// get the fail info
			createtests.fail(failedResult);
			// set fail log
			createtests.log(Status.FAIL, failedResult);
			// set the screenshot for fail
			createtests.fail(MediaEntityBuilder.createScreenCaptureFromPath("./Results/FailScreenshot.png").build());
			extent.flush();
		}
	}

	// create the reports for each test case
	public void getReportName(String createTest, String reportName) {
		// set the Create test log
		createtests = extent.createTest(createTest);
		// set the reporter name
		reporter = new ExtentSparkReporter("./Results/" + reportName + ".html");
		// attach the report
		extent.attachReporter(reporter);
		extent.flush();
	}

	public void getFilePath(String filePath) throws Exception {
		// set the file path
		fis = new FileInputStream(filePath);
		// get the workbook data
		workbook = new XSSFWorkbook(fis);
		// close the file
		fis.close();
	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			// set the column number
			int col_Num = -1;
			// get the sheet name
			sheet = workbook.getSheet(sheetName);
			// get the row number
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// check the value in column header
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					// set the column number
					col_Num = i;
			}
			// get Row number
			row = sheet.getRow(rowNum);
			// get column number
			cell = row.getCell(col_Num);
			// get the cell value
			return cell.getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist  in Excel";
		}
	}

	// Method for wait
	public void waitUntilElementVisible(String locator, int i) {
		// set the webdriver wait
		WebDriverWait wait = new WebDriverWait(driver, i);
		// wait element for expected condition
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

}
