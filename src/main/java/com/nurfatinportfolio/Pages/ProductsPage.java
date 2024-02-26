package com.nurfatinportfolio.Pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;


import com.nurfatinportfolio.AbstractComponents.AbstractComponents;

public class ProductsPage extends AbstractComponents {

	WebDriver driver;
	
	public ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='shopping_cart_link']")
	WebElement cartIcon;
	
	@FindBy(css="[class='shopping_cart_badge']")
	WebElement cartIconCount;
	
	@FindBy(css="[class='title']")
	WebElement productsHeader;
	
	@FindBy(css=".inventory_item_description")
	List<WebElement> productsName;

	
	By cartIconCountBy = By.cssSelector("[class='shopping_cart_badge']");
	By productsNameBy = By.cssSelector(".inventory_item");
	By addToCartButtonBy = By.cssSelector(".pricebar button[class*='btn_inventory']");
	By removeButtonBy = By.cssSelector(".pricebar button[class*='btn_inventory']");
	

	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsNameBy);
		return productsName;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod =	getProductsList().stream().filter(product->
		product.findElement(By.cssSelector("a")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartButtonBy).click();
		Thread.sleep(1000);
	}
	
	public String cartIconCountExist() throws InterruptedException
	{
		waitForWebElementToAppear(cartIconCount);
		return cartIconCount.getText();
	}
	
	public boolean cartIconCountNotExist() throws InterruptedException
	{
		return isElementPresent(cartIconCountBy);
	}
	
	public void removeProductFromCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartButtonBy).click();
		Thread.sleep(1000);
	}
	
	public void printAllProductNames() {
	    List<WebElement> productsList = getProductsList();

	    System.out.println("List of Product Names:");
	    for (WebElement product : productsList) {
	        System.out.println(product.getText());
	    }
	}
	
	public String productsPageHeader() {
		waitForWebElementToAppear(productsHeader);
		return productsHeader.getText();
	}


}
	

	