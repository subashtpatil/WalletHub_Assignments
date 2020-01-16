package com.fb.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fb.qa.base.TestBase;

public class LoginPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 50);

	// ****************************************************
	// PageFactory - Object Repository
	// ****************************************************

	// Email input box
	@FindBy(id = "email")
	static WebElement email;

	@FindBy(id = "pass")
	static WebElement password;

	// ****************************************************
	// Initializing the LoginPage Objects
	// ****************************************************
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// ****************************************************
	// Actions or Methods
	// ****************************************************
	public String validate_fbLoginPageTitle() {
		return driver.getTitle();
	}

	public void enterEmail(String eml) throws InterruptedException {
		email.clear();
		Thread.sleep(1000);
		email.sendKeys(eml);
	}

	public void enterPassword(String pwd) throws InterruptedException {
		password.clear();
		Thread.sleep(1000);
		password.sendKeys(pwd);
		Thread.sleep(1000);
	}

	public void LoginBtnClick() {
		driver.findElement(By.xpath("//input[@value=\"Log In\"]")).click();
	}

	public String validate_fbHomePageTitle() {
		return driver.getTitle();
	}

}
