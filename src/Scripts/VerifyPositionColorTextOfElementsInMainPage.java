package Scripts;

import org.openqa.selenium.WebDriver;

import PageObjectLibrary.Common;
import PageObjectLibrary.PacktPage;

public class VerifyPositionColorTextOfElementsInMainPage {

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
		result = packtPage.checkPositionAndMoveToElements();
		cm.reportingForTests(result, "verify the Position of the Elements", "Position of the Elements is checked",
				"Position of the Elements is checked successfully", "Fail to check Position of the Elements");
		result = packtPage.checkColorOfTheElementsInPage();
		cm.reportingForTests(result, "verify the Color of the elements", "Color of the elements is checked",
				"Color of the elements is checked successfully", "Fail to check Color of the elementse");
		result = packtPage.checkTheTextOfReadNow("Read now");
		cm.reportingForTests(result, "verify the Text of Read now button", "Text of Read now button is checked",
				"Text of Read now button is checked successfully", "Fail to check Text of Read now button");
		result = packtPage.checkTheTextOfAuthorName("Mark J. Price");
		cm.reportingForTests(result, "verify the Text of Author Name", "Text of Author Name is checked",
				"Text of Author Name is checked successfully", "Fail to check Text of Author Name");
		result = packtPage.checkTheTextOfTitle("Incident response with Threat Intelligence");
		cm.reportingForTests(result, "To Check the Title Text", "Title Text is checked",
				"Title Text is checked successfully", "Fail to check the Title Text");
	}
}