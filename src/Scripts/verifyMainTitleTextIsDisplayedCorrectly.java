package Scripts;

import org.openqa.selenium.WebDriver;

import PageObjectLibrary.Common;
import PageObjectLibrary.PacktPage;

public class verifyMainTitleTextIsDisplayedCorrectly {

	static WebDriver driver;
    static Common cm = new Common(driver);
	static PacktPage packtPage;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		driver = cm.launchURL();
		packtPage = new PacktPage(driver);
		packtPage.loginToApplication("15pa5a0221@vishnu.edu.in", "J@n@2022");
		packtPage.checkPacktPagLoaded();
		cm.implictWait(5);
        boolean result = packtPage.checkMainTitleTextDisplayed();
        if(result) {
        	System.out.println("Script Pass");
        }
        else{
        	System.out.println("Script Fail");
        }
	}

}
