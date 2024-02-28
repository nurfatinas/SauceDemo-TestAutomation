package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestData.ExtDataProvider;
import com.nurfatinportfolio.Utilities.ExcelReader;


public class ProductsTest extends BaseTest{
	
    /**
     * TC_PROD_001: [P] User able to add product(s) to Cart
     */
	@Test (dataProvider = "getLogin", dataProviderClass = ExtDataProvider.class)
	public void addFromProductsPage(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		
		ExcelReader excelReader = new ExcelReader();
		ArrayList<String> productsData = excelReader.getExcelData("Product2");
		
		int num_items_in_cart = 0;
		for (String product : productsData) {
			productsPage.addToCart(product);
			System.out.println("Product: [" +product + "] is added to Cart");
		    num_items_in_cart++;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
	}
	
    /**
     * TC_PROD_002: [P] User able to remove item(s) added to Cart from Products Page
     */
	@Test (dataProvider = "getLogin", dataProviderClass = ExtDataProvider.class)
	public void removeFromProductsPage(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		
		ExcelReader excelReader = new ExcelReader();
		ArrayList<String> productsData = excelReader.getExcelData("Product2");
		ArrayList<String> removeProductsData = excelReader.getExcelData("RemoveProduct1");
		
		int num_items_in_cart = 0;
		for (String product : productsData) {
			productsPage.addToCart(product);
			System.out.println("Product: [" +product + "] is added to Cart");
		    num_items_in_cart++;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
		
		for (String product : removeProductsData) {
			productsPage.addToCart(product);
			System.out.println("Product: [" +product + "] is removed from Cart");
		    num_items_in_cart--;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) remaining in Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
	}

    /**
     * TC_PROD_003: [P] User able to add product(s) to Cart from Product Detail Page (PDP)
     */
	@Test (dataProvider = "getLogin", dataProviderClass = ExtDataProvider.class)
	public void addFromProductDetailPage(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		
		ExcelReader excelReader = new ExcelReader();
		ArrayList<String> productsData = excelReader.getExcelData("Product3");		
		
	    int num_items_in_cart = 0;
	    for (int i = 0; i < productsData.size(); i++) {
	    	
	        String product = productsData.get(i);
	        productsPage.addToCartFromPDP(product);
	        System.out.println("Product: [" + product + "] is added to Cart");
	        num_items_in_cart++;

	        if (i < productsData.size() - 1) {
	            productsPage.backToProducts();
	            Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
	        }
	    }
	    
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
	}
}
