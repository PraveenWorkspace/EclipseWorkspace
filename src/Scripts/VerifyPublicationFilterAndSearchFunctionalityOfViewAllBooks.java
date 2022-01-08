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
		packtPage.checkPacktPagLoaded();
		packtPage.clickOnBrowseNav();
		cm.implictWait(5);
		packtPage.clickOnViewAllBooks();
		cm.switchToChildWindow();
		packtPage.verifyCommonTextIsPresent("Product Type");
		packtPage.clickOnCommonText("Clear all ");
		packtPage.clickOn2021PublicationYearCheckbox();
		packtPage.checkThePublicationYearForAllProducts("2021");
		boolean result =  packtPage.checkTheProductTitleForAll();
		 if(result) {
	        	System.out.println("Script Pass");
	        }
	        else{
	        	System.out.println("Script Fail");
	        }
	}
}
