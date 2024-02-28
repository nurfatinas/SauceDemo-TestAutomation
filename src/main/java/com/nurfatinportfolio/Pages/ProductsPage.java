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
	
	@FindBy(css=".btn_inventory")
	WebElement addToCart_single;
	
	@FindBy(css=".inventory_item_description")
	List<WebElement> productsName;
	
	@FindBy(css=".inventory_details_back_button")
	WebElement backToProduct;

	By BY_cartIconCount = By.cssSelector("[class='shopping_cart_badge']");
	By BY_productsName = By.cssSelector(".inventory_item");
	By BY_addToCart = By.cssSelector(".pricebar button[class*='btn_inventory']");
	By BY_selectProduct = By.cssSelector(".inventory_item_name");
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(BY_productsName);
		return productsName;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod =	getProductsList().stream().filter(product->
		product.findElement(By.cssSelector("a")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(BY_addToCart).click();
		Thread.sleep(1000);
	}
	
	public void addToCartFromPDP(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(BY_selectProduct).click();
		addToCart_single.click();
		Thread.sleep(1000);
	}
	
	public int hasItemInCart() throws InterruptedException
	{	
	    boolean hasItemsInCart = driver.findElements(BY_cartIconCount).size() > 0;
	    
	    if (hasItemsInCart) {
	    	String numItemsInCart = driver.findElement(BY_cartIconCount).getText();
	        return Integer.parseInt(numItemsInCart);
	        
	    } 
	    else {
	        return 0;
	    }
	}

	public void removeProductFromCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(BY_addToCart).click();
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
	
	public void backToProducts() {
		backToProduct.click();
	}


}
	

	