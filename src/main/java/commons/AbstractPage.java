package commons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.AbstractPageUI;

public class AbstractPage {
	
	public WebElement find(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public List<WebElement> finds(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
		;
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
		;
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public void overrideGlobalTimeout(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, Constant.longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitToElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, Constant.longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, Constant.shortTimeout);
		overrideGlobalTimeout(driver, Constant.shortTimeout);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		overrideGlobalTimeout(driver, Constant.longTimeout);
	}

	public void waitToElementInvisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, Constant.shortTimeout);
		overrideGlobalTimeout(driver, Constant.shortTimeout);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		overrideGlobalTimeout(driver, Constant.longTimeout);
	}
	
	public boolean isDynamicMangementPageDisplayed(WebDriver driver, String dynamicHeader) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_HEADER, dynamicHeader);
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_HEADER, dynamicHeader);
	}
	
	public void inputToDynamicTextbox(WebDriver driver, String dynamicFormName, String value) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX, dynamicFormName);
		sendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX, value, dynamicFormName);
	}

	public void clickOnDynamicButtonByName(WebDriver driver, String dynamicName) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_BUTTON_BYNAME, dynamicName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON_BYNAME, dynamicName);
	}
	
	public void waitForProcessBarDisappears(WebDriver driver) {
		waitToElementInvisible(driver, AbstractPageUI.PROCESS_BAR);
	}
	
	
}
