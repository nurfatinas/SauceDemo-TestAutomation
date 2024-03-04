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

	@FindBy(css = ".cart_item_label")
	List<WebElement> productsName;

	By BY_cartItem = By.cssSelector(".cart_item");
	By BY_removeFromCart = By.cssSelector(".item_pricebar button[class*='cart_button']");

	public String yourCartPageHeader() {
		waitForWebElementToAppear(yourCartHeader);
		return yourCartHeader.getText();
	}

	// Method to return to Products Page by clicking on Continue Shopping button
	public ProductsPage continueShopping() {
		continueShopBtn.click();
		ProductsPage productsPage = new ProductsPage(driver);

		return productsPage;
	}

	public List<WebElement> getProductsList() {
		waitForElementToAppear(BY_cartItem);
		return productsName;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("a")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	// Method to remove product(s) from Cart by clicking on Remove button
	public void removeFromCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(BY_removeFromCart).click();
		Thread.sleep(1000);
	}

}
