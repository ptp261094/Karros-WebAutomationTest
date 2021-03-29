package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageFactory.PageFactoryManager;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {

	WebDriver driver;

	public LoginPageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public HomePageObject clickToLoginButton() throws Exception {
		Thread.sleep(1000);
		waitToElementVisible(driver, LoginPageUI.SUBMIT_BUTTON);
		clickToElement(driver, LoginPageUI.SUBMIT_BUTTON);
		return PageFactoryManager.getHomePage(driver);
	}
	
}
