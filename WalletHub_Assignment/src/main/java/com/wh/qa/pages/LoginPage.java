package com.wh.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wh.qa.base.TestBase;

public class LoginPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 50);
	public JavascriptExecutor js = (JavascriptExecutor) driver;
	public Actions action = new Actions(driver);

	//************ PageFactory - Object Repository****************//

	// Login Menu
	@FindBy(xpath = "//a[@class='login']")
	WebElement LoginMenu;

	// Email input box
	@FindBy(xpath = "//input[@placeholder='Email Address']")
	WebElement email;

	// password input box
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;

	// Login Button
	@FindBy(xpath = "//button[@class='btn blue center reg-tabs-bt touch-element-cl']")
	WebElement loginBtn;
	
	
	// ==============================================================
	// Initializing the LoginPage Objects
	// ==============================================================
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// ==============================================================
	// Actions or Methods
	//
	// ==============================================================
	// Verify the PreLogin title
	public String validate_LoginPageTitle() {
		return driver.getTitle();
	}

	public void clickOnLoginMenu() {
		LoginMenu.click();
	}

	// This method is to set Email in the email text box
	public void setEmail(String strEmail) {
		email.sendKeys(strEmail);
	}

	// This method is to set Password in the password text box
	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}

	// This method is to click on Next Button
	public void clickOnloginBtn() {
		loginBtn.click();
	}
	
	public String validate_HomePageTitle() {
		return driver.getTitle();
	}

}
