package com.saucedemo.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class='title']")
	WebElement yourCartHeader;

	@FindBy(css = ".inventory_item_name")
	List<WebElement> productsInCart;

	@FindBy(css = ".cart_quantity")
	List<WebElement> productsQty;

	@FindBy(css = "[id='continue-shopping']")
	WebElement continueShopBtn;
	
	@FindBy(css = "[id='checkout']")
	WebElement checkoutBtn;

	@FindBy(css = ".cart_item_label")
	List<WebElement> productsName;

	By BY_cartItem = By.cssSelector(".cart_item");
	By BY_removeFromCart = By.cssSelector(".item_pricebar button[class*='cart_button']");

	// Return to the product list page (PLP) by clicking on the Continue Shopping
	// button.
	public ProductsPage continueShopping() {
		continueShopBtn.click();
		ProductsPage productsPage = new ProductsPage(driver);

		return productsPage;
	}
	
	// Navigate to checkout: your information page by clicking on the Checkout button.
	public InfoPage checkout() {
		checkoutBtn.click();
		InfoPage infoPage = new InfoPage(driver);

		return infoPage;
	}

	// Retrieves the list of products as WebElements from cart page.
	public List<WebElement> getProductListFromCart() {
		waitForElementToAppear(BY_cartItem);
		return productsName;
	}

	// Gets a specific WebElement of product by its name from the cart page.
	public WebElement getProductWEInCart(String productName) {
		WebElement prod = getProductListFromCart().stream()
				.filter(product -> product.findElement(By.cssSelector("a")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	// Remove product from cart by clicking on the Remove button.
	public void removeFromCart(String productName) throws InterruptedException {
		WebElement prod = getProductWEInCart(productName);
		prod.findElement(BY_removeFromCart).click();
		Thread.sleep(1000);
	}

}
