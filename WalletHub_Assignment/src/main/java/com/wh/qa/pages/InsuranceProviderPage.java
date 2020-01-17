package com.wh.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.wh.qa.base.TestBase;

public class InsuranceProviderPage extends TestBase {
	private static final String Finally = null;
	public WebDriverWait wait = new WebDriverWait(driver, 50);
	public JavascriptExecutor js = (JavascriptExecutor) driver;
	public Actions action = new Actions(driver);

	// WebElement Companies on menu
	@FindBy(xpath = "//nav[@class='right-menu loggedIn']/div/a[6]")
	WebElement menuCompanies;

	// Insurance Providers Submenu
	@FindBy(xpath = "//nav[@id='burger-companies']//a[contains(text(),'Insurance Providers')]")
	WebElement menuInsProv;

	// Insurance Provider
	@FindBy(xpath = "//h1[contains(text(),'Insurance Providers')]")
	WebElement InsProvPage;

	// Search Insurance Provider
	@FindBy(xpath = "//input[@placeholder=\"Firm or Individual's Name\"]")
	WebElement searchInsProv;

	// Write a Review
	@FindBy(xpath = "//button[@class='btn blue-brds']")
	WebElement writeAReview;

	// Five stars
	@FindBy(xpath = "//review-star[@class='rvs-svg']//div[@class='rating-box-wrapper']")
	WebElement fiveStars;

	// Fourth star
	// @FindBy(xpath = "//div[starts-with[@class,'review-']//*[4]")
	@FindBy(xpath = "//review-star[@class='rvs-svg']//*[4]")
	WebElement fourthStar;

	// Policy Drop Down
	@FindBy(xpath = "//span[contains(text(),'Select...')]")
	WebElement policyDropDown;

	// Write your Review
	@FindBy(xpath = "//textarea[@placeholder='Write your review...']")
	WebElement writeYourReview;

	// Submit Button
	@FindBy(xpath = "//div[@class='sbn-action semi-bold-font btn fixed-w-c tall']")
	WebElement submitButton;

	// Review posted Confirmation
	@FindBy(xpath = "//h4[contains(text(),'Your review has been posted.')]")
	WebElement reviewpostedConformation;

	// ==============================================================
	// Initializing the InsuranceProviderPage Objects
	// ==============================================================
	public InsuranceProviderPage() {
		PageFactory.initElements(driver, this);
	}

	// ==============================================================
	// Actions or Methods //
	// ==============================================================

	public void clicksubmenuitemfromMenu() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(menuCompanies));
		action.moveToElement(menuCompanies).perform();
		Thread.sleep(1000);
		action.moveToElement(menuInsProv).click().build().perform();
		Reporter.log("Clicking on Companies and then the Insurance Providers submenu", true);
	}

	public void ValidateInsuranceProviderTitle() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		String insProvTxt = InsProvPage.getText();
		Reporter.log("The title of the page is displayed as  =  " + insProvTxt);
		softAssert.assertEquals(insProvTxt, "Insurance Providers");
		softAssert.assertAll();

	}

	public void searchInsuranceProvider(String compName) throws InterruptedException {
		action.moveToElement(searchInsProv).perform();
		searchInsProv.sendKeys(compName, Keys.ENTER);
		Thread.sleep(3000);
	}

	public void clickOntheInsuranceCompany(String insCompany) {
		WebElement insComp = driver.findElement(By.xpath("//a[contains(text(),'" + insCompany + "')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", insComp);
		action.moveToElement(insComp).click().perform();
		Reporter.log("Clicking on the Insurance Provider Company", true);
	}

	public void hoverOnfiveStars() throws InterruptedException {

		try {
			action.moveToElement(fiveStars).build().perform();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Reporter.log("The user can provide his review only once");
		}
	}

	public void clickOnfourthStar() throws InterruptedException {
		action.moveToElement(fourthStar).build().perform();
		Thread.sleep(2000);
		fourthStar.click();
		Reporter.log("Clicking on the fourth Star out of five stars to provide my review", true);
	}

	public void selectInsTypeFromPolicyDropDown(String insType, String reviewText) throws InterruptedException {
		Thread.sleep(6000);
		try {
			wait.until(ExpectedConditions.visibilityOf(policyDropDown));
			policyDropDown.click();
			WebElement insTyp = driver.findElement(
					By.xpath("//ul[@class='dropdown-list ng-enter-element']/li[contains(text(),'" + insType + "')]"));
			action.moveToElement(insTyp).click().perform();
			Reporter.log("selecting Health Insurance from the Policies dropdown list", true);
			writeYourReview.sendKeys(reviewText);
			Thread.sleep(5000);
			Reporter.log("Entering my review comment for the company", true);
			submitButton.click();
			Thread.sleep(4000);
			Reporter.log("Clicking on the Submit button", true);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} finally {
			if (writeYourReview.isDisplayed() && writeYourReview.isEnabled()) {
				writeYourReview.sendKeys(reviewText);
				Thread.sleep(5000);
				Reporter.log(
						"Anyway since policy dropdown to enter Health Insurance is not available, entering my review comment for the company, if needed",
						true);
				submitButton.click();
				Reporter.log("Clicking on the Submit button", true);
			}
		}
	}

	/*The below function is commented because user reviewed company is being verified in the User Profile page.
	 * 
	 * public void VerifyreviewpostedConfirmation() {
		SoftAssert softAssert = new SoftAssert();
		try {
			wait.until(ExpectedConditions.visibilityOf(reviewpostedConformation));
			String reviewtxt = reviewpostedConformation.getText();
			softAssert.assertEquals(reviewtxt, "Your review has been posted.");
			Reporter.log("Validating the review posted by user, Confirmation message", true);
			softAssert.assertAll();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}*/
}
