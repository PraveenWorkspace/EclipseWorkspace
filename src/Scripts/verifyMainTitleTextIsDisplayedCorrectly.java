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
		packtPage.loginToApplication("15pa5a0221@vishnu.edu.in", "J@n@2022");
		boolean result = packtPage.checkPacktPagLoaded();
		cm.reportingForTests(result, "To Check the Login to Appllication", "Login Page is checked",
				"Login To Application successfully", "Fail to login to the application");
		cm.implictWait(5);
		result = packtPage.checkMainTitleTextDisplayed();
		cm.reportingForTests(result, "To Check the main Text is displayed", "main Text is displayed",
				"main Text is displayed successfully", "Main Text is not displayed");
	}
}
