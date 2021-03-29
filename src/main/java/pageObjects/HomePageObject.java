package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageFactory.PageFactoryManager;
import pageUIs.AbstractPageUI;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {

	WebDriver driver;

	public HomePageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public boolean isHomePageDisplayed(String dynamicHeader) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_HEADER, dynamicHeader);
		return getCurrentUrl(driver).contains("home");
	}
	
	public AbstractPage clickToDynamicStudentMangementPage(String dynamicTab) {
		waitToElementVisible(driver, HomePageUI.DYNAMIC_STUDENTMANAGEMENT_TAB, dynamicTab);
		clickToElement(driver, HomePageUI.DYNAMIC_STUDENTMANAGEMENT_TAB, dynamicTab);
		
		switch(dynamicTab) {
		case "Student":
			return PageFactoryManager.getStudentPage(driver);
		default:
			return PageFactoryManager.getHomePage(driver);
		}
	}
}
