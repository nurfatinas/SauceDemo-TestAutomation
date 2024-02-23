package com.nurfatinportfolio.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nurfatinportfolio.Pages.CartPage;

public class AbstractComponents {
	
	WebDriver driver;
	String cartCount;

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='shopping_cart_link']")
	WebElement cartButton;
	
	@FindBy(css="[class='shopping_cart_badge']")
	WebElement cartNumber;
	
	public void waitForWebElementToAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToAppear(By element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public boolean isElementPresent(By element) throws InterruptedException
	{
	    try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	        return true;  // Element is not present
	    } catch (TimeoutException e) {
	        // Element is still present or not found within the timeout
	        return false;
	    }
	}
	
	public CartPage goToCart() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		
		return cartPage;
	}
	
	public String cartItemCount() {
		cartCount = cartNumber.getText();
		
		return cartCount;
	}


}
