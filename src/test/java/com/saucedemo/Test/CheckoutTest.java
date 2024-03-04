package com.saucedemo.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.Pages.CartPage;
import com.saucedemo.Pages.InfoPage;
import com.saucedemo.Pages.OverviewPage;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.TestComponents.BaseTest;
import com.saucedemo.TestData.TestDataProvider;

public class CheckoutTest extends BaseTest {

	/**
	 * TC_CHECKOUT_001: [P] User able to cancel on Checkout: Your Information Page
	 */
	@Test(dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = { "ProductPage" })
	public void cancelOnInfoPage(String username, String password, String firstname, String lastname, String postal,
			String addProductList, String removeProductList) throws IOException, InterruptedException {
		
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

		// Navigate to the Your Cart Page
		CartPage cartPage = productsPage.goToCart();
		Assert.assertEquals(cartPage.pageTitle(), "Your Cart", "User is not redirected to Your Cart Page.");

		// Navigate to the Checkout: Your Information Page
		InfoPage infoPage = cartPage.checkout();
		Assert.assertEquals(infoPage.pageTitle(), "Checkout: Your Information", "User is not redirected to Your Cart Page.");
		
		//Cancel and return to Your Cart Page
		CartPage returnToCartPage = infoPage.cancel();
		Assert.assertEquals(returnToCartPage.pageTitle(), "Your Cart", "User is not redirected to Your Cart Page.");
	}

	/**
	 * TC_CHECKOUT_002: [P] User able to cancel on Checkout: Overview Page
	 */
	@Test(dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class, groups = { "ProductPage" })
	public void cancelOnOverviewPage(String username, String password, String firstname, String lastname, String postal,
			String addProductList, String removeProductList) throws IOException, InterruptedException {
		
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

		// Navigate to the Your Cart Page
		CartPage cartPage = productsPage.goToCart();
		Assert.assertEquals(cartPage.pageTitle(), "Your Cart", "User is not redirected to Your Cart Page.");

		// Navigate to the Checkout: Your Information Page
		InfoPage infoPage = cartPage.checkout();
		Assert.assertEquals(infoPage.pageTitle(), "Checkout: Your Information", "User is not redirected to Your Cart Page.");
		
		// Fill up the form and navigate to the Checkout: Overview Page
		OverviewPage overviewPage = infoPage.fillUpForm(firstname, lastname, postal);
		Assert.assertEquals(overviewPage.pageTitle(), "Checkout: Overview", "User is not redirected to Your Cart Page.");
		
		//Cancel and return to Products Page
		ProductsPage returnToProductsPage = overviewPage.cancel();
		Assert.assertEquals(returnToProductsPage.pageTitle(), "Checkout: Overview", "User is not redirected to Your Cart Page.");
	}

	/**
	 * TC_CHECKOUT_003: [N] User not able to proceed to Checkout: Overview Page when
	 * Your Information form is not filled up completely
	 */

	/**
	 * TC_CHECKOUT_004: [N] User not able to checkout when no product(s) is added to
	 * Cart
	 */

}
