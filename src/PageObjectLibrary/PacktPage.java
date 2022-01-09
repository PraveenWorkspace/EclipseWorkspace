package PageObjectLibrary;

import java.util.List;

import org.openqa.selenium.WebDriver;

public class PacktPage extends Common {

	Common common;
	String[] strProductNames = { "Python", "Paint", "Secure", "Tableau" };

	public PacktPage(WebDriver driver) {
		super(driver);
		common = new Common(driver);
	}

	String strLogo = "//img[@class='logo']";
	String strSignInButton = "//a[@class='nav-link-subscribe btn-primary']";
	String strEmailID = "login-input-email";
	String strPasswordID = "login-input-password";
	String strSignInSubmitt = "//*[text()='Sign In']/../../button[@class='btn btn-lg btn-primary btn-block']";
	String strCommonNav = "//span[contains(text(),'%s')]";
	String strViewAllBooks = "//div[@class='row p-3 keep-me-active']//li";
	String strCommonText = "//*[text()='%s']";
	String strPublicationCheckboxID = "2021";
	String strProductsYearText = "//span[contains(@class,'search-hit__published-on')]";
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

		common.isElementPresent(strLogo);
		common.clickOnElement(strSignInButton);
		common.switchToChildWindow();
		common.setTextByID(strEmailID, userName);
		common.setTextByID(strPasswordID, password);
		common.clickOnElement(strSignInSubmitt);
		return driver;
	}

	// check the page is loaded
	public boolean checkPacktPagLoaded() {
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

	// check the Publication Year for all
	public boolean checkThePublicationYearForAllProducts(String strValueText) {
		boolean blnResult = false;
		List<String> strValues = common.getListOfElementsByClass(strProductsYearText);
		for (int i = 0; i < strValues.size(); i++) {
			blnResult = strValues.get(i).contains(strValueText);
		}
		return blnResult;
	}

	// check the Product title for all
	public boolean checkTheProductTitleForAll() {
		boolean blnResult = false;
		for (int j = 0; j < strProductNames.length;) {
			common.setTextByXpath(strSerachTextbox, strProductNames[j]);
			common.clickOnElement(strSearchButton);
			common.implictWait(10);
			List<String> strValues = common.getListOfElementsByClass(strProductTitles);
			for (int i = 0; i < strValues.size(); i++) {
				blnResult = strValues.get(i).contains(strProductNames[j]);
			}
			common.clickOnElement(strSearchCoseIcon);
			j++;
		}
		return blnResult;
	}

	// check the Sub Options Page displayed correctly
	public boolean checkSubOptionsPageProperlyDisplayed(String CategoryName) {
		boolean blnResult = false;
		for (int j = 0; j < 4;) {
			clickOnBrowseNav();
			common.clickOnElement(String.format(strMainCategory, CategoryName));
			common.implictWait(10);
			List<String> strValues = common.getListOfElementsByClass(strCommonSubOptions);
			common.clickOnElement(String.format(strSubOptions, strValues.get(j)));
			common.switchToChildWindow();
			List<String> strTitles = common.getListOfElementsByXpathWithoutWait(strProductTitles);
			for (int i = 0; i < 5; i++) {
				if (strTitles.get(i).contains(strValues.get(j))
						|| strTitles.get(i).contains(getShortcutNameForModule(strValues.get(j)))) {
					blnResult = true;
				}
			}
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
		common.clickOnElement(strMainTitles);
		List<String> strValues = common.getListOfElementsByClass(strSubMainTitles);
		for (int i = 0; i < strValues.size(); i++) {
			common.clickOnElement(String.format(strSubTitle, (strValues).get(i)));
			common.implictWait(15);
			String strActual = common.getTextByXpath(strCheckMainTitles);
			blnResult = strActual.equals(strValues.get(i));
			common.clickOnElement(strLogo);
			common.clickOnElement(strMainTitles);
		}
		return blnResult;
	}

	// check the Position and move to element
	public boolean checkPositionAndMoveToElements() {
		common.checkTheElementPosition(strLogo);
		common.checkTheElementPosition(String.format(strCommonNav, "My Library"));
		common.checkTheElementPosition(String.format(strCommonNav, "Browse"));
		common.checkTheElementPosition(strMainTitles);
		common.checkTheElementPosition(strStartFreeTrail);
		common.checkTheElementPosition(strReadNow);
		common.checkTheElementPosition(strAuthorName);
		common.checkTheElementPosition(strMediaTitle);
		return common.checkTheElementPosition(strTitles);
	}

	// check the Position and move to element
	public boolean checkPositionAndMoveToElement() {
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
