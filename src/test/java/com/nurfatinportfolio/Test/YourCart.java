package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestData.ExtDataProvider;

public class YourCart extends BaseTest {
	
	
    /**
     * TC_CART_001: [P] User able to add product(s) to cart and product(s) are displayed correctly in Your Cart Page
     */
	@Test (dataProvider = "multipleProductsData", dataProviderClass = ExtDataProvider.class)
	public void addProductsToCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
		
	    for (String product : input.get("product").split(",")) {
	        productsPage.addProductToCart(product.trim());
	        System.out.println("Added product to cart: " + product.trim());
	    }
	}

	
}
