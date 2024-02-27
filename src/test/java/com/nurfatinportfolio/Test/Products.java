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


public class Products extends BaseTest{

    /**
     * TC_PROD_005: [P] User able to add product(s) to Cart
     */
	@Test (dataProvider = "getLogin", dataProviderClass = ExtDataProvider.class)
	public void addProductsToCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		
		ExcelReader excelReader = new ExcelReader();
		ArrayList<String> productsData = excelReader.getExcelData("Product2");
		
		int num_items_in_cart = 0;
		for (String product : productsData) {
			productsPage.addProductToCart(product);
			System.out.println("Product: [" +product + "] is added to Cart");
		    num_items_in_cart++;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
	}
	
    /**
     * TC_PROD_006: [P] User able to remove item(s) added to Cart from Products Page
     */
	@Test (dataProvider = "getLogin", dataProviderClass = ExtDataProvider.class)
	public void removeProductsFromCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		
		ExcelReader excelReader = new ExcelReader();
		ArrayList<String> productsData = excelReader.getExcelData("Product2");
		ArrayList<String> removeProductsData = excelReader.getExcelData("RemoveProduct1");
		
		int num_items_in_cart = 0;
		for (String product : productsData) {
			productsPage.addProductToCart(product);
			System.out.println("Product: [" +product + "] is added to Cart");
		    num_items_in_cart++;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
		
		for (String product : removeProductsData) {
			productsPage.addProductToCart(product);
			System.out.println("Product: [" +product + "] is removed from Cart");
		    num_items_in_cart--;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) remaining in Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
	}
	

}
