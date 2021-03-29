package com.karros.studentmanagement;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import commons.AbstractTest;
import commons.Constant;
import pageFactory.PageFactoryManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.StudentPageObject;

public class StudentManagement_01_AddNewStudent extends AbstractTest {
	
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private StudentPageObject studentPage;
	
	Faker usFaker = new Faker(new Locale("en-US"));
	String firstName = usFaker.address().firstName();
	String lastName = usFaker.address().lastName();
	String grade = Integer.toString(AbstractTest.randomNumber(1,12));
	String studentId = Integer.toString(AbstractTest.randomNumber(10000,90000));
	String legacyStudentId = Integer.toString(AbstractTest.randomNumber(10000,90000));
	String school = "test school 1";
	String dob = "5/21/1999";
	
	
	@BeforeClass
	@Parameters("browser")

	public void beforeClass(String browserName) throws Exception {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManager.getLoginPage(driver);
		loginPage.inputToDynamicTextbox(driver, "username", Constant.USERNAME);
		loginPage.inputToDynamicTextbox(driver, "password", Constant.PASSWORD);
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed("System Management"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	@Test
	public void TC_01_VerifyUserFilterSchool() {
		
		studentPage = (StudentPageObject) homePage.clickToDynamicStudentMangementPage("Student");
		Assert.assertTrue(studentPage.isDynamicMangementPageDisplayed(driver, "Student"));
		studentPage.clickOnDynamicButtonByName(driver, "Add Student");
		Assert.assertTrue(studentPage.isDynamicMangementPageDisplayed(driver, "Add Student"));
		studentPage.waitForProcessBarDisappears(driver);

		studentPage.inputToDynamicTextbox(driver, "school", school);
		Assert.assertTrue(studentPage.isTheSchoolListFiltered(school));
		
	}
	
	@Test
	public void TC_02_VerifyUserCanAddStudent() throws Exception{

		studentPage.clickOnRandomSchool(school);
		studentPage.inputToDynamicTextbox(driver, "givenName", firstName);
		studentPage.inputToDynamicTextbox(driver, "familyName", lastName);
		studentPage.inputToDynamicTextbox(driver, "grade", grade);
		studentPage.inputToDynamicTextbox(driver, "districtId", studentId);
		studentPage.inputToDynamicTextbox(driver, "legacyStudentId", legacyStudentId);
		studentPage.clickOnCalendarButton();
		studentPage.selectDynamicDateOnCalendar(dob);
		studentPage.clickOnDynamicButtonByName(driver, "Add");

		studentPage.waitForProcessBarDisappears(driver);
		Assert.assertEquals(studentPage.getTextNotification(), "Add new Student successfully.");
		
		
		Assert.assertEquals(studentPage.getTextOfDynamicFirstCell("firstName"), firstName);
		Assert.assertEquals(studentPage.getTextOfDynamicFirstCell("lastName"), lastName);
		Assert.assertEquals(studentPage.getTextOfDynamicFirstCell("grade"), grade);
		Assert.assertEquals(studentPage.getTextOfDynamicFirstCell("districtId"), studentId);
		Assert.assertEquals(studentPage.getTextOfDynamicFirstCell("legacyStudentId"), legacyStudentId);
	}


}
