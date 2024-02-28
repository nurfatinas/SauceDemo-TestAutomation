package com.nurfatinportfolio.Pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurfatinportfolio.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='title']")
	WebElement yourCartHeader;
	
	@FindBy(css=".inventory_item_name")
	List<WebElement> productsInCart;
	
	@FindBy(css=".cart_quantity")
	List<WebElement> productsQty;

	public List<String> getProductsInCart()
	{
	    List<String> productNames = productsInCart.stream()
	            .map(product -> {
	                String productName = product.getText();
	                return productName;
	            })
	            .collect(Collectors.toList());

	    return productNames;
	}
	
	public String yourCartPageHeader() {
		waitForWebElementToAppear(yourCartHeader);
		return yourCartHeader.getText();
	}

}
