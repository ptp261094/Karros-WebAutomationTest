package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.StudentPageUI;

public class StudentPageObject extends AbstractPage {

	WebDriver driver;

	public StudentPageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public boolean isTheSchoolListFiltered(String value) {
		List<WebElement> list = finds(driver, StudentPageUI.SCHOOL_LIST);
		Boolean status = true;
		for(WebElement e:list) {
			if(!e.getText().toLowerCase().contains(value)) {
				status = false;
				break;
			}
		}
		return status;
	}
	
	public void clickOnRandomSchool(String value) {
		List<WebElement> list = finds(driver, StudentPageUI.SCHOOL_LIST);
		Random random = new Random();
		list.get(random.nextInt(list.size())).click();
		
	}
	
	public void clickOnCalendarButton() {
		waitToElementVisible(driver, StudentPageUI.CALENDAR_BUTTON);
		clickToElement(driver, StudentPageUI.CALENDAR_BUTTON);
	}
	

	public String getMonthCode(String monthNumber) {
		String monthCode = null;
		switch (monthNumber) {
		case "1":
			monthCode = "JAN";
			break;
		case "2":
			monthCode = "FEB";
			break;
		case "3":
			monthCode = "MAR";
			break;
		case "4":
			monthCode = "APR";
			break;
		case "5":
			monthCode = "MAY";
			break;
		case "6":
			monthCode = "JUN";
			break;
		case "7":
			monthCode = "JUL";
			break;
		case "8":
			monthCode = "AUG";
			break;
		case "9":
			monthCode = "SEP";
			break;
		case "10":
			monthCode = "OCT";
			break;
		case "11":
			monthCode = "NOV";
			break;
		case "12":
			monthCode = "DEC";
			break;
		}
		return monthCode;
	}
	
	public void clickOnDynamicButtonOnCalendar(String dynamicType) {
		waitToElementVisible(driver, StudentPageUI.DYNAMIC_BUTTON_ON_CALENDAR, dynamicType);
		clickToElement(driver, StudentPageUI.DYNAMIC_BUTTON_ON_CALENDAR, dynamicType);
	}
	
	public void selectDynamicDateOnCalendar(String selectDate) {
		waitToElementVisible(driver, StudentPageUI.CALENDAR_POPUP);

		String[] dates = selectDate.split("/");
		String selectDay = dates[1];
		String selectMonth = getMonthCode(dates[0]);
		String selectYear = dates[2];

		String selectMonthYear = selectMonth+ " " + selectYear;
		String currentMonthYear = find(driver, StudentPageUI.CURRENT_MONTHYEAR).getText();
		
		List<WebElement> calendarList = finds(driver, StudentPageUI.CALENDAR_LIST);
	    if(selectMonthYear.equalsIgnoreCase(currentMonthYear)) {
	    	for(WebElement d:calendarList) {
	    		if(d.getText().equals(selectDay)) {
	    			d.click();
	    			break;
	    		}
	    	}
	    } else {
	    	clickOnDynamicButtonOnCalendar("Choose month and year");
	    	Boolean status = true;
	    	while(status) {
		    	calendarList = finds(driver, StudentPageUI.CALENDAR_LIST);

		    	for(WebElement y:calendarList) {
		    		if(y.getText().equals(selectYear)) {
		    			y.click();
		    			status = false;
		    			break;
		    		} 
		    	}
		    	if(status == false) {
		    		break;
		    	} else {
			    	clickOnDynamicButtonOnCalendar("Previous 20 years");
		    	}
	    	}

	    	calendarList = finds(driver, StudentPageUI.CALENDAR_LIST);
	    	for(WebElement m:calendarList) {
	    		if(m.getText().equals(selectMonth)) {
	    			m.click();
	    			break;
	    		} 
	    	}
	    	
	    	calendarList = finds(driver, StudentPageUI.CALENDAR_LIST);
	    	for(WebElement d:calendarList) {
	    		if(d.getText().equals(selectDay)) {
	    			d.click();
	    			break;
	    		} 
	    	}
	    }	
	}
	
	public String getTextOfDynamicFirstCell(String dynamicValue) {
		waitToElementVisible(driver, StudentPageUI.DYNAMIC_FIRST_CELL, dynamicValue);
		return getTextElement(driver, StudentPageUI.DYNAMIC_FIRST_CELL, dynamicValue);
	}
	
	public String getTextNotification() {
		waitToElementVisible(driver, StudentPageUI.ADDED_MESSAGE);
		return getTextElement(driver, StudentPageUI.ADDED_MESSAGE);
	}
	
}
