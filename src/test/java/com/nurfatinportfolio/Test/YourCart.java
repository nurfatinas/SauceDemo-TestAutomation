package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;

public class YourCart extends BaseTest {
	
	// TC_CART_001: [P] User able to add product(s) to cart and product(s) are displayed correctly in Your Cart Page
	@Test (dataProvider = "getData")
	public void addProductsToCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertFalse(loginPage.errorPromptExists(), "Login error prompt visible!");
		
		productsPage.addProductToCart(input.get("product"));
	}

	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\getData.json");
		return new Object [][] {{data.get(0)}};	
	}
	
}
