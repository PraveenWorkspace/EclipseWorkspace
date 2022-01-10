package Scripts;

import org.openqa.selenium.WebDriver;

import PageObjectLibrary.Common;
import PageObjectLibrary.PacktPage;

public class VerifyPublicationFilterAndSearchFunctionalityOfViewAllBooks {

	static WebDriver driver;
    static Common cm = new Common(driver);
	static PacktPage packtPage;

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		driver = cm.launchURL();
		packtPage = new PacktPage(driver);
		packtPage.loginToApplication("15pa5a0221@vishnu.edu.in", "J@n@2022");
		boolean result = packtPage.checkPacktPagLoaded();
		cm.reportingForTests(result, "To Check the Login to Appllication", "Login Page is checked",
				"Login To Application successfully", "Fail to login to the application");
		result = packtPage.clickOnBrowseNav();
		cm.reportingForTests(result, "To click on the Browse", "Browse option is clicked",
				"Browse Option clicked successfully", "Fail to click on the Browse");
		cm.implictWait(5);
		result = packtPage.clickOnViewAllBooks();
		cm.reportingForTests(result, "To click on the View All link", "View All link option is clicked",
				"View All link Option clicked successfully", "Fail to click on the View All link");
		cm.switchToChildWindow();
		result = packtPage.verifyCommonTextIsPresent("Product Type");
		cm.reportingForTests(result, "Verify Product Type is Present", "Product Type is Present",
				"Product Type is displayed successfully", "Product Type is not displayed");
		result = packtPage.clickOnCommonText("Clear all ");
		cm.reportingForTests(result, "To click on the Clear all", "Clear all filter is clicked",
				"Clear all filter clicked successfully", "Fail to click on the Clear all filter");
		result = packtPage.clickOn2021PublicationYearCheckbox();
		cm.reportingForTests(result, "To click on the Publication checkbox", "Publication checkbox is clicked",
				"Publication checkbox clicked successfully", "Fail to click on the Publication checkbox");
		result =  packtPage.checkTheProductTitleForAll();
		cm.reportingForTests(result, "To check on the Product Title", "Product Title is displayed correctly",
				"Product Title are correctly displayed", "Fail to check the Product Title");
	}
}
