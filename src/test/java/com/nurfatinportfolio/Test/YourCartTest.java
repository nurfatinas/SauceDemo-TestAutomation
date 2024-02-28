package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nurfatinportfolio.Pages.CartPage;
import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestData.ExtDataProvider;
import com.nurfatinportfolio.Utilities.ExcelReader;

public class YourCartTest extends BaseTest {
	
	
    /**
     * TC_CART_001: [P] User able to add product(s) to cart and product(s) are displayed correctly in Your Cart Page
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
			productsPage.removeProductFromCart(product);
			System.out.println("Product: [" +product + "] is added to Cart");
		    num_items_in_cart++;
		}
		System.out.println("Total ["+ num_items_in_cart + "] product(s) added to Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), num_items_in_cart, "Total number of items in Cart is incorrect.");
		
		CartPage cartPage = productsPage.goToCart();
		Assert.assertEquals(cartPage.yourCartPageHeader(), "Your Cart", "User is not redirected to Your Cart Page.");

		List<String> productsInCart = cartPage.getProductsInCart();
		Assert.assertTrue(productsInCart.containsAll(productsData), "Product(s) in Cart do not match product(s) added to Cart.");

		System.out.println("Products In Cart\t\tProducts From Data");
		for (int i = 0; i < Math.max(productsInCart.size(), productsData.size()); i++) {
		    String productInCart = (i < productsInCart.size()) ? productsInCart.get(i) : "-";
		    String productData = (i < productsData.size()) ? productsData.get(i) : "-";

		    System.out.println(productInCart + "\t\t" + productData);
		}
		
}		
}
	
