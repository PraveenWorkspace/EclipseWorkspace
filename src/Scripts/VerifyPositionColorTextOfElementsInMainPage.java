package Scripts;

import org.openqa.selenium.WebDriver;

import PageObjectLibrary.Common;
import PageObjectLibrary.PacktPage;

public class VerifyPositionColorTextOfElementsInMainPage {

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
		packtPage.checkPositionAndMoveToElement();
		packtPage.checkPositionAndMoveToElement();
		packtPage.checkTheTextOfMyLibrary("My Library");
		packtPage.checkTheTextOfMainTitle("Hello, C#! Welcome, .NET!");
		packtPage.checkTheTextOfReadNow("Read now");
		packtPage.checkTheTextOfAuthorName("Mark J. Price");
		boolean result = packtPage.checkTheTextOfTitle("Incident response with Threat Intelligence");
		 if(result) {
	        	System.out.println("Script Pass");
	        }
	        else{
	        	System.out.println("Script Fail");
	        }
	}
}
