package Scripts;

import org.openqa.selenium.WebDriver;

import PageObjectLibrary.Common;
import PageObjectLibrary.PacktPage;

public class verifyMainTitleTextIsDisplayedCorrectly {

	static WebDriver driver;
	static Common cm = new Common(driver);
	static PacktPage packtPage;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		driver = cm.launchURL();
		packtPage = new PacktPage(driver);
		cm.getFilePath("src/TestData/DataDetails.xlsx");
		cm.getReportName("verifyMainTitleTextIsDisplayedCorrectly", "verifyMainTitleTextIsDisplayedCorrectly");
		String username = cm.getCellData("Sheet1", "UserName", 1);
		String password = cm.getCellData("Sheet1", "Password", 1);
		packtPage.loginToApplication(username, password);
		boolean result = packtPage.checkPacktPagLoaded();
		cm.reportingForTests(result, "To Check the Login to Appllication", "Login Page is checked",
				"Login To Application successfully", "Fail to login to the application");
		cm.implictWait(5);
		result = packtPage.checkMainTitleTextDisplayed();
		cm.reportingForTests(result, "To Check the main Text is displayed", "main Text is displayed",
				"main Text is displayed successfully", "Main Text is not displayed");
	}
}
