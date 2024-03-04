package com.saucedemo.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.AbstractComponents.AbstractComponents;

public class OverviewPage extends AbstractComponents {

	WebDriver driver;
	
	public OverviewPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[id='cancel']")
	WebElement cancelBtn;
	
	@FindBy(css = "[id='finish']")
	WebElement finishBtn;
	
	// Return to products page by clicking on the Cancel
	// button.
	public ProductsPage cancel() {
		cancelBtn.click();
		ProductsPage productsPage = new ProductsPage(driver);

		return productsPage;
	}

	// Method to click on Finish and navigate to the Checkout: Complete! Page
	public CompletePage finish() {
		finishBtn.click();
		CompletePage completePage = new CompletePage(driver);

		return completePage;
	}
}
