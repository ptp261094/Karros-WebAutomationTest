package pageFactory;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.StudentPageObject;


public class PageFactoryManager {
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static StudentPageObject getStudentPage(WebDriver driver) {
		return new StudentPageObject(driver);
	}

}

