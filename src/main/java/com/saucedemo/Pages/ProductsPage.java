package com.saucedemo.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.AbstractComponents.AbstractComponents;

public class ProductsPage extends AbstractComponents {

	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class='shopping_cart_link']")
	WebElement cartIcon;

	@FindBy(css = "[class='shopping_cart_badge']")
	WebElement cartIconCount;

	@FindBy(css = ".btn_inventory")
	WebElement addRemoveProduct;

	@FindBy(css = ".inventory_item_description")
	List<WebElement> productsName;

	@FindBy(css = ".inventory_details_back_button")
	WebElement backToProduct;

	@FindBy(css = ".btn_inventory")
	WebElement removeProduct;

	By BY_cartCount = By.cssSelector("[class='shopping_cart_badge']");
	By BY_addToCart = By.cssSelector(".pricebar button[class*='btn_inventory']");
	By BY_selectProduct = By.cssSelector(".inventory_item_name");

	// Add product to cart by clicking on the 'Add to Cart' button.
	public void addToCart(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_addToCart).click();
		Thread.sleep(1000);
	}

	// Add product to cart from the product detail page (PDP).
	public void addToCartFromPDP(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_selectProduct).click();
		addRemoveProduct.click();
		Thread.sleep(1000);
	}

	// Check if items are present in cart and return the count.
	public int hasItemInCart() throws InterruptedException {
		boolean hasItemsInCart = driver.findElements(BY_cartCount).size() > 0;

		if (hasItemsInCart) {
			String numItemsInCart = driver.findElement(BY_cartCount).getText();
			return Integer.parseInt(numItemsInCart);

		} else {
			return 0;
		}
	}

	// Remove product from the product detail page (PDP).
	public void removeProductFromPDP(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_selectProduct).click();
		addRemoveProduct.click();
		Thread.sleep(1000);
	}

	// Remove product from product list page (PLP)
	public void removeProduct(String productName) throws InterruptedException {
		WebElement prod = getProductWEInPLP(productName);
		prod.findElement(BY_addToCart).click();
		Thread.sleep(1000);
	}

	public void backToProducts() {
		backToProduct.click();
	}

}
