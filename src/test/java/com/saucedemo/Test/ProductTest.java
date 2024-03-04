package com.saucedemo.Test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.TestComponents.BaseTest;
import com.saucedemo.TestData.TestDataProvider;

public class ProductTest extends BaseTest{

    /**
     * TC_PROD_001: [P] User able to add product(s) to Cart
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = {"ProductPage"})
	public void addFromProductsPage(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
		// Login and navigate to Products Page
	    ProductsPage productsPage = loginPage.userLogin(username, password);
	    Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");

		// Extract products names obtained from Excel data file
		String[] prod_addToCart = addProductList.split(",");
		
		// Trim whitespace from add products name
		for (int i = 0; i < prod_addToCart.length; i++) {
			prod_addToCart[i] = prod_addToCart[i].trim();
		}
	    
	    // Add items to the cart
	    int cart_count = 0;
	    for (String product : prod_addToCart) {
	        productsPage.addToCart(product);
	        System.out.println("Product: [" + product + "] is added to Cart");
	        cart_count++;
	    }
	    System.out.println("Total [" + cart_count + "] product(s) in Cart");
	    Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");
	}
	
	 /**
     * TC_PROD_002: [P] User able to remove item(s) added to Cart from Products Page
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = {"ProductPage"})
	public void removeFromProductsPage(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
		// Login and navigate to Products Page
	    ProductsPage productsPage = loginPage.userLogin(username, password);
	    Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");

		// Extract products names obtained from Excel data file
		String[] prod_addToCart = addProductList.split(",");
	    String[] prod_removeFromCart = removeProductList.split(",");

		// Trim whitespace from add products name
		for (int i = 0; i < prod_addToCart.length; i++) {
			prod_addToCart[i] = prod_addToCart[i].trim();
		}
		
		// Trim whitespace from remove products name
		for (int i = 0; i < prod_removeFromCart.length; i++) {
			prod_removeFromCart[i] = prod_removeFromCart[i].trim();
		}
		
	    // Add items to the cart
		int cart_count = 0;
		for (String product : prod_addToCart) {
			productsPage.addToCart(product);
	        System.out.println("Product: [" + product + "] is added to Cart");
			cart_count++;
		}
		System.out.println("["+ cart_count + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");
		
	    // Remove items from the cart
		for (String product : prod_removeFromCart) {
			productsPage.addToCart(product);
	        System.out.println("Product: [" + product + "] is removed from Cart");
			cart_count--;
		}
		System.out.println("Total ["+ cart_count + "] product(s) in Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");
	}
	
	 /**
     * TC_PROD_003: [P] User able to add product(s) to Cart from Product Detail Page (PDP)
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = {"ProductDetailPage"})
	public void addFromProductDetailPage(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
		// Login and navigate to Products Page
	    ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
		
		// Extract products names obtained from Excel data file
		String[] prod_addToCart = addProductList.split(",");
		
		// Trim whitespace from add products name
		for (int i = 0; i < prod_addToCart.length; i++) {
			prod_addToCart[i] = prod_addToCart[i].trim();
		}
		
		int cart_count = 0;
		int lastIndex = prod_addToCart.length - 1;
		
	    // Add items to the cart
	    for (int i = 0; i < prod_addToCart.length; i++) {
	        String product = prod_addToCart[i];
	        productsPage.addToCartFromPDP(product);
	        System.out.println("Product: [" + product + "] is added to Cart");
	        cart_count++;

	        if (i < lastIndex) {
	            productsPage.backToProducts();
	            Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
	        }
	    }
	    
		System.out.println("Total ["+ cart_count + "] product(s) in Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");
	}
	
    /**
     * TC_PROD_004: [P] User able to remove item(s) added to Cart from Product Detail Page (PDP)
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = {"ProductDetailPage1"})
	public void removeFromProductDetailPage(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
		// Login and navigate to Products Page
		ProductsPage productsPage = loginPage.userLogin(username, password);
	    Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");

		// Extract products names obtained from Excel data file
		String[] prod_addToCart = addProductList.split(",");
	    String[] prod_removeFromCart = removeProductList.split(",");

		// Trim whitespace from add products name
		for (int i = 0; i < prod_addToCart.length; i++) {
			prod_addToCart[i] = prod_addToCart[i].trim();
		}
		
		// Trim whitespace from remove products name
		for (int i = 0; i < prod_removeFromCart.length; i++) {
			prod_removeFromCart[i] = prod_removeFromCart[i].trim();
		}

		int cart_count = 0;
		int lastIndex_add = prod_addToCart.length - 1;
		
	    // Add items to the cart
	    for (int i = 0; i < prod_addToCart.length; i++) {
	        String product = prod_addToCart[i];
	        productsPage.addToCartFromPDP(product);
	        System.out.println("Product: [" + product + "] is added to Cart");
	        cart_count++;

	        if (i < lastIndex_add) {
	            productsPage.backToProducts();
	            Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
	        }
	    }

	    System.out.println("[" + cart_count + "] product(s) added to Cart");
	    Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");
	    
        productsPage.backToProducts();

		int lastIndex_remove= prod_removeFromCart.length - 1;

	    // Remove items from the cart
	    for (int i = 0; i < prod_removeFromCart.length; i++) {
	        String product = prod_removeFromCart[i];
	        productsPage.removeProductFromPDP(product);
	        System.out.println("Product: [" + product + "] is removed from Cart");
	        cart_count--;

	        if (i < lastIndex_remove) {
	            productsPage.backToProducts();
	            Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
	        }
	    }
	    System.out.println("Total [" + cart_count + "] product(s) in Cart");
	    Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");
	}
}
    