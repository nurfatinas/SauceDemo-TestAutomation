package com.saucedemo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.AbstractComponents.AbstractComponents;

public class InfoPage extends AbstractComponents {

	WebDriver driver;

	public InfoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[id='cancel']")
	WebElement cancelBtn;

	@FindBy(css = "[id='first-name']")
	WebElement firstNameInput;

	@FindBy(css = "[id='last-name']")
	WebElement lastNameInput;

	@FindBy(css = "[id='postal-code']")
	WebElement postalInput;

	@FindBy(css = "[id='continue']")
	WebElement continueBtn;

	// Method to fill up a form with the provided first name, last name, and postal
	// and navigate to Checkout: Overview Page
	public OverviewPage fillUpForm(String firstName, String lastName, String postal) {
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		postalInput.sendKeys(postal);

		// Click on the continue button after filling up the form
		continueBtn.click();

		// Create an instance of the OverviewPage class and return it
		OverviewPage overviewPage = new OverviewPage(driver);

		return overviewPage;
	}

	// Return to checkout: your information page by clicking on the Cancel
	// button.
	public CartPage cancel() {
		cancelBtn.click();
		CartPage cartPage = new CartPage(driver);

		return cartPage;
	}

}
