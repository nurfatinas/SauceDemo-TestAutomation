package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;

public class Products extends BaseTest{

	// TC_PROD_005: [P] User able to add product(s) to Cart
	@Test (dataProvider = "getData")
	public void addProductsToCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
		productsPage.addProductToCart(input.get("product"));
		Assert.assertEquals(productsPage.cartIconExist(), "1", "Product count is incorrect!");
	}
	
	// TC_PROD_006: [P] User able to remove item(s) added to Cart from Products Page
	@Test (dataProvider = "getData")
	public void removeProductsFromCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
		productsPage.addProductToCart(input.get("product"));
		Assert.assertEquals(productsPage.cartIconExist(), "1", "Product count is incorrect!");
		productsPage.removeProductFromCart(input.get("product"));
        Assert.assertTrue(productsPage.cartIconNotExist(), "Cart icon is unexpectedly present");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\getData.json");
		return new Object [][] {{data.get(0)}};	
	}
	
}
