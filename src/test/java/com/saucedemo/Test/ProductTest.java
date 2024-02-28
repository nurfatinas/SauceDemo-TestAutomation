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
	    ProductsPage productsPage = loginPage.userLogin(username, password);
	    Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");

	    String[] addProducts = addProductList.split(",");
	    
	    // Add items to the cart
	    int numItemsInCart = 0;
	    for (String product : addProducts) {
	        productsPage.addToCart(product.trim());
	        System.out.println("Product: [" + product + "] is added to Cart");
	        numItemsInCart++;
	    }
	    System.out.println("Total [" + numItemsInCart + "] product(s) added to Cart");
	    Assert.assertEquals(productsPage.hasItemInCart(), numItemsInCart, "Total number of items in Cart is incorrect.");
	}
	
	 /**
     * TC_PROD_002: [P] User able to remove item(s) added to Cart from Products Page
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = {"ProductPage"})
	public void removeFromProductsPage(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
	    ProductsPage productsPage = loginPage.userLogin(username, password);
	    Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");

	    String[] addProducts = addProductList.split(",");
	    String[] removeProducts = removeProductList.split(",");
		
	    // Add items to the cart
		int num_items_in_cart = 0;
		for (String product : addProducts) {
			productsPage.addToCart(product.trim());
			System.out.println("Product: [" +product + "] is added to Cart");
		    num_items_in_cart++;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
		
	    // Remove items from the cart
		for (String product : removeProducts) {
			productsPage.addToCart(product.trim());
			System.out.println("Product: [" +product + "] is removed from Cart");
		    num_items_in_cart--;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) remaining in Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
	}
	
	 /**
     * TC_PROD_003: [P] User able to add product(s) to Cart from Product Detail Page (PDP)
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = {"ProductDetailPage"})
	public void addFromProductDetailPage(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
	    ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		
	    String[] addProducts = addProductList.split(",");
		
	    // Add items to the cart
	    int num_items_in_cart = 0;
	    for (int i = 0; i < addProducts.length; i++) {
	        String product = addProducts[i].trim();
	        productsPage.addToCartFromPDP(product);
	        System.out.println("Product: [" + product + "] is added to Cart");
	        num_items_in_cart++;

	        if (i < addProducts.length - 1) {
	            productsPage.backToProducts();
	            Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
	        }
	    }
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
	}
	
    /**
     * TC_PROD_004: [P] User able to remove item(s) added to Cart from Product Detail Page
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = {"ProductDetailPage1"})
	public void removeFromProductDetailPage(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(username, password);
	    Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");

	    String[] addProducts = addProductList.split(",");
	    String[] removeProducts = removeProductList.split(",");

	    int numItemsInCart = 0;

	    // Add items to the cart
	    for (int i = 0; i < addProducts.length; i++) {
	        String product = addProducts[i].trim();
	        productsPage.addToCartFromPDP(product);
	        System.out.println("Product: [" + product + "] is added to Cart");
	        numItemsInCart++;

	        if (i < addProducts.length - 1) {
	            productsPage.backToProducts();
	            Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
	        }
	    }

	    System.out.println("Total [" + numItemsInCart + "] product(s) added to Cart");
	    Assert.assertEquals(productsPage.hasItemInCart(), numItemsInCart, "Total number of items in Cart is incorrect.");
	    
        productsPage.backToProducts();

	    // Remove items from the cart
	    for (int i = 0; i < removeProducts.length; i++) {
	        String product = removeProducts[i].trim();
	        productsPage.removeProductFromPDP(product);
	        System.out.println("Product: [" + product + "] is removed from Cart");
	        numItemsInCart--;

	        if (i < removeProducts.length - 1) {
	            productsPage.backToProducts();
	            Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
	        }
	    }

	    Assert.assertEquals(productsPage.hasItemInCart(), numItemsInCart, "Total number of items in Cart is incorrect.");
	}
}
    