package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestComponents.Retry;

public class YourCart extends BaseTest {
	
	// TC_CART_001: [P] User able to add product(s) to cart and product(s) are displayed correctly in Your Cart Page
	@Test(dataProvider = "getValidCred", groups = {"LoginSucess"}, retryAnalyzer = Retry.class)
	public void addProductsToCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertFalse(loginPage.errorPromptExists(), "Login error prompt visible!");
		Assert.assertTrue(loginPage.homePageHeaderExists(), "User is not redirected to homepage");
		
		
		
	}

	
	
	
	@DataProvider
	public Object[][] getValidCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginValidCred.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}, {data.get(4)}};	
	}
}
