package PageObjectLibrary;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;

public class PacktPage extends Common {

	Common common;
	String[] strProductNames = { "Python", "Paint", "Secure", "Tableau" };

	public PacktPage(WebDriver driver) {
		super(driver);
		common = new Common(driver);
	}
    
	//We elements locators
	String strLogo = "//img[@class='logo']";
	String strSignInButton = "//a[@class='nav-link-subscribe btn-primary']";
	String strEmailID = "login-input-email";
	String strPasswordID = "login-input-password";
	String strSignInSubmitt = "//*[text()='Sign In']/../../button[@class='btn btn-lg btn-primary btn-block']";
	String strCommonNav = "//span[contains(text(),'%s')]";
	String strViewAllBooks = "//div[@class='row p-3 keep-me-active']//li";
	String strCommonText = "//*[text()='%s']";
	String strPublicationCheckboxID = "2021";
	String strSerachTextbox = "//*[@id='search-input']";
	String strSearchButton = "//*[@class='btn btn-primary ais-search-box--submit']";
	String strSearchCoseIcon = "//*[@class='ais-search-box--reset']";
	String strProductTitles = "//*[@class='search-hit__title']//em";
	String strMainCategory = "//*[contains(@class,'row categories-list')]//span[text()='%s']";
	String strCommonSubOptions = "//*[contains(@class,'category-header-items')]";
	String strSubOptions = "//*[contains(@class,'category-header-items')][text()='%s']";
	String strMainTitles = "//*[@class='d-flex justify-content-start']";
	String strSubMainTitles = "//*[@class='collapse show']//a";
	String strSubTitle = "//*[@class='collapse show']//a[text()='%s']";
	String strCheckMainTitles = "//div[@class='epub-source']//h1";
	String strStartFreeTrail = "//*[@class='button'][text()='Start FREE trial']";
	String strReadNow = "//button[text()='Read now']";
	String strAuthorName = "//*[@class='author-name']";
	String strMediaTitle = "//*[@class='media-title']";
	String strTitles = "//*[@class='title']";

	// Login to the application
	public WebDriver loginToApplication(String userName, String password) throws Exception {
        //check the logo is present
		common.isElementPresent(strLogo);
		//click on sign in button
		common.clickOnElement(strSignInButton);
		// switch to child window
		common.switchToChildWindow();
		//set the Email Id
		common.setTextByID(strEmailID, userName);
		//set the Password
		common.setTextByID(strPasswordID, password);
		//click on Log in button
		common.clickOnElement(strSignInSubmitt);
		//return the driver
		return driver;
	}

	// check the page is loaded
	public boolean checkPacktPagLoaded() {
		//wait and click on My Library
		common.waitUntilElementClickable(String.format(strCommonNav, "My Library"), 15);
		return true;
	}

	// click on the Browse Nav
	public boolean clickOnBrowseNav() {
		return common.clickOnElement(String.format(strCommonNav, "Browse"));
	}

	// click on the View All Books link
	public boolean clickOnViewAllBooks() {
		return common.clickOnElement(strViewAllBooks);
	}

	// verify Text is Present
	public boolean verifyCommonTextIsPresent(String textname) {
		return common.isElementPresent(String.format(strCommonText, textname));
	}

	// click on the text
	public boolean clickOnCommonText(String textname) {
		return common.clickOnElement(String.format(strCommonText, textname));
	}

	// click on the 2021 Publication year checkbox
	public boolean clickOn2021PublicationYearCheckbox() {
		return common.clickOnElementById(strPublicationCheckboxID);
	}

	// check the Product title for all
	public boolean checkTheProductTitleForAll() {
		boolean blnResult = false;
		for (int j = 0; j < strProductNames.length;) {
			//set the product name
			common.setTextByXpath(strSerachTextbox, strProductNames[j]);
			//click on Search buton
			common.clickOnElement(strSearchButton);
			//wait for web element
			common.implictWait(10);
			//Get the Product titles
			List<String> strValues = common.getListOfElementsByClass(strProductTitles);
			for (int i = 0; i < strValues.size(); i++) {
				//check the values is present in the list
				blnResult = strValues.get(i).contains(strProductNames[j]);
			}
			//click on the close icon
			common.clickOnElement(strSearchCoseIcon);
			j++;
		}
		return blnResult;
	}

	// check the Sub Options Page displayed correctly
	public boolean checkSubOptionsPageProperlyDisplayed(String CategoryName) {
		boolean blnResult = false;
		for (int j = 0; j < 4;) {
			//click on Browse
			clickOnBrowseNav();
			//click on the main category
			common.clickOnElement(String.format(strMainCategory, CategoryName));
			//wait for element
			common.implictWait(10);
			//get the Sub options
			List<String> strValues = common.getListOfElementsByClass(strCommonSubOptions);
			//click on sub option
			common.clickOnElement(String.format(strSubOptions, strValues.get(j)));
			// switch to child window
			common.switchToChildWindow();
			//get the product titkes
			List<String> strTitles = common.getListOfElementsByXpathWithoutWait(strProductTitles);
			for (int i = 0; i < 5; i++) {
				//check the  value in the product titles
				if (strTitles.get(i).contains(strValues.get(j))
						|| strTitles.get(i).contains(getShortcutNameForModule(strValues.get(j)))) {
					blnResult = true;
				}
			}
			//switch to Parent window
			common.switchToParentWindow();
			j++;
		}
		return blnResult;
	}

	// get Shortcut name for the Module
	public String getShortcutNameForModule(String fullName) {
		switch (fullName) {
		case "Java Script":
			return "js";
		case "Angular":
		case "React":
		case "Django":
			return fullName;
		case "Node.js":
			return "Node";
		}
		return fullName;
	}

	// check the Main Title Text displayed correctly
	public boolean checkMainTitleTextDisplayed() {
		boolean blnResult = false;
		//wait for the element
		common.waitUntilElementVisible(strMainTitles,15);
		// click on main title
		common.clickOnElement(strMainTitles);
		// get the sub titles
		List<String> strValues = common.getListOfElementsByClass(strSubMainTitles);
		for (int i = 0; i < strValues.size(); i++) {
			// click on sub title
			common.clickOnElement(String.format(strSubTitle, (strValues).get(i)));
			//wait for the element
			common.implictWait(15);
			//get the main title text
			String strActual = common.getTextByXpath(strCheckMainTitles);
			//check the value with actual value
			blnResult = strActual.equals(strValues.get(i));
			//click on logo
			common.clickOnElement(strLogo);
			//wait for the main title
			common.waitUntilElementVisible(strMainTitles,20);
			//click on main title
			common.clickOnElement(strMainTitles);
		}
		return blnResult;
	}

	// check the Position element
	public boolean checkPositionAndMoveToElements() {
		//check the position of web elements in Main Page
		common.checkTheElementPosition(strLogo,"134","15");
		common.checkTheElementPosition(String.format(strCommonNav, "My Library"), "1198","26");
		common.checkTheElementPosition(String.format(strCommonNav, "Browse"),"649","26");
		common.checkTheElementPosition(strMainTitles,"1016","604");
		common.checkTheElementPosition(strStartFreeTrail,"679","312");
		common.checkTheElementPosition(strReadNow,"736","597");
		common.checkTheElementPosition(strAuthorName,"75","1297");
	    common.checkTheElementPosition(strMediaTitle,"75","1727");
		return common.checkTheElementPosition(strTitles,"111","2130");
	}

	// check the color of the web elements in main Page
	public boolean checkColorOfTheElementsInPage() {
		//check the color of the web elements in main Page
		common.checkTheColourOfTheElement(strLogo, "#4ab9d5");
		common.checkTheColourOfTheElement(String.format(strCommonNav, "My Library"), "#fff");
		common.checkTheColourOfTheElement(String.format(strCommonNav, "Browse"), "#fff");
		common.checkTheColourOfTheElement(strMainTitles, "#fff");
		common.checkTheColourOfTheElement(strStartFreeTrail, "#fff");
		common.checkTheColourOfTheElement(strReadNow, "#fff");
		common.checkTheColourOfTheElement(strAuthorName, "#4ac5e3");
		common.checkTheColourOfTheElement(strMediaTitle, "#454a55");
		return common.checkTheColourOfTheElement(strTitles, "#4ac5e3");
	}

	// check the Text of Read now
	public boolean checkTheTextOfReadNow(String value) {
		String strValue = common.getTextByXpath(strReadNow);
		return value.equals(strValue);
	}

	// check the Text of Author Name
	public boolean checkTheTextOfAuthorName(String value) {
		String strValue = common.getTextByXpath(strAuthorName);
		return value.equals(strValue);
	}

	// check the Text of Title
	public boolean checkTheTextOfTitle(String value) {
		String strValue = common.getTextByXpath(strTitles);
		return value.equals(strValue);
	}
}
