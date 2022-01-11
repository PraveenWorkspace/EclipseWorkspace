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
		cm.getFilePath("src/TestData/DataDetails.xlsx");
		cm.getReportName("VerifyPositionColorTextOfElementsInMainPage", "VerifyPositionColorTextOfElementsInMainPage");
		String username = cm.getCellData("Sheet1", "UserName", 1);
		String password = cm.getCellData("Sheet1", "Password", 1);
		packtPage.loginToApplication(username, password);
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
		String text = cm.getCellData("Sheet1", "Text", 1);
		result = packtPage.checkTheTextOfReadNow(text);
		cm.reportingForTests(result, "verify the Text of Read now button", "Text of Read now button is checked",
				"Text of Read now button is checked successfully", "Fail to check Text of Read now button");
		String authorname = cm.getCellData("Sheet1", "Author name", 1);
		result = packtPage.checkTheTextOfAuthorName(authorname);
		cm.reportingForTests(result, "verify the Text of Author Name", "Text of Author Name is checked",
				"Text of Author Name is checked successfully", "Fail to check Text of Author Name");
		String strtext = cm.getCellData("Sheet1", "Title", 1);
		result = packtPage.checkTheTextOfTitle(strtext);
		cm.reportingForTests(result, "To Check the Title Text", "Title Text is checked",
				"Title Text is checked successfully", "Fail to check the Title Text");
	}
}