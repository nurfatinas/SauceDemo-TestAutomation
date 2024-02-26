package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestData.ExtDataProvider;


public class Products extends BaseTest{

    /**
     * TC_PROD_005: [P] User able to add product(s) to Cart
     */
	@Test (dataProvider = "producData", dataProviderClass = ExtDataProvider.class)
	public void addProductsToCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
		productsPage.addProductToCart(input.get("product"));
		Assert.assertEquals(productsPage.cartIconCountExist(), "1", "Product count is incorrect!");
	}
	
    /**
     * TC_PROD_006: [P] User able to remove item(s) added to Cart from Products Page
     */
	@Test (dataProvider = "producData", dataProviderClass = ExtDataProvider.class)
	public void removeProductsFromCart(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
		productsPage.addProductToCart(input.get("product"));
		Assert.assertEquals(productsPage.cartIconCountExist(), "1", "Product count is incorrect!");
		productsPage.removeProductFromCart(input.get("product"));
        Assert.assertTrue(productsPage.cartIconCountNotExist(), "Cart icon is unexpectedly present");
	}
	

}
