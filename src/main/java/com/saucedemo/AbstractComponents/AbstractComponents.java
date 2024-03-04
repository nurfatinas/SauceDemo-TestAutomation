package com.saucedemo.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.Pages.CartPage;
import com.saucedemo.Pages.LoginPage;

/**
 * AbstractComponent class serves as a base class for page objects in this
 * framework It contains common methods and elements shared across multiple Page
 * Objects classes Child classes, which are the Page Object classes, must extend
 * this class This ensures a consistent structure for page objects and promotes
 * code re-usability
 */

public class AbstractComponents {

	WebDriver driver;
	String cartCount;

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class='shopping_cart_link']")
	WebElement cartButton;

	@FindBy(css = "[class='shopping_cart_badge']")
	WebElement cartNumber;

	@FindBy(css = "[id='react-burger-menu-btn']")
	WebElement hamburgerIcon;

	@FindBy(css = "[id='logout_sidebar_link']")
	WebElement logout;

	@FindBy(css = "[class='bm-menu']")
	WebElement hamburgerMenu;

	@FindBy(css = "[class='title']")
	WebElement pageTitle;

	@FindBy(css = ".inventory_item_description")
	List<WebElement> productsName;

	@FindBy(css = ".inventory_item_name")
	List<WebElement> product_inventory;

	@FindBy(css = ".inventory_item_price")
	List<WebElement> price_inventory;

	By BY_inventoryItem = By.cssSelector(".inventory_item");
	By BY_inventoryPrice = By.cssSelector(".inventory_item_price");

	// Waits for a WebElement to become visible on the page.
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Waits for an by Element identified to become visible on the page.
	public void waitForElementToAppear(By element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	// Checks if an by Element is present on the page within a specified timeout.
	public boolean isElementPresent(By element) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			return true; // Element is not present
		} catch (TimeoutException e) {
			// Element is still present or not found within the timeout
			return false;
		}
	}

	// Retrieves and returns the text of the page title.
	public String pageTitle() {
		waitForWebElementToAppear(pageTitle);
		return pageTitle.getText();
	}

	// Clicks on the cart button and navigates to the cart page.
	public CartPage goToCart() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver);

		return cartPage;
	}

	// Logs out from the application and returns a new login page instance.
	public LoginPage logout() {
		hamburgerIcon.click();
		waitForWebElementToAppear(hamburgerMenu);
		logout.click();
		LoginPage loginPage = new LoginPage(driver);

		return loginPage;
	}

	// Retrieves the list of products as WebElements from products list page (PLP).
	public List<WebElement> getProductsListFromPLP() {
		waitForElementToAppear(BY_inventoryItem);
		return productsName;
	}
	
	// Retrieves the list of prices as WebElements from products list page (PLP).
	public List<WebElement> getPricesListFromPLP() {
		waitForElementToAppear(BY_inventoryPrice);
		return productsName;
	}

	// Gets a specific WebElement of product by its name from the products list page (PLP).
	public WebElement getProductWEInPLP(String productName) {
		WebElement prod = getProductsListFromPLP().stream()
				.filter(product -> product.findElement(By.cssSelector("a")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	// Retrieves the price of a product by its name from the products list page (PLP).
	public double getPriceFromPLP(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		String priceTag = prod.findElement(BY_inventoryPrice).getText();

		// Remove $ sign from the priceTag
		priceTag = priceTag.replaceAll("\\$", "");
		double priceValue = Double.parseDouble(priceTag);

		return priceValue;
	}

	// Retrieves the price of a product by its name from the cart or checkout (overview) page.
	public Double getPriceFromPage(String productName) {
		for (int i = 0; i < Math.min(product_inventory.size(), price_inventory.size()); i++) {
			WebElement productElement = product_inventory.get(i);
			WebElement priceElement = price_inventory.get(i);

			String productText = productElement.getText();
			String priceTag = priceElement.getText();

			priceTag = priceTag.replaceAll("\\$", "");

			double priceValue = Double.parseDouble(priceTag);

			if (productText.equals(productName)) {
				return priceValue;
			}
		}
		return null;
	}
	
 

}
