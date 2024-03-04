package com.saucedemo.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.TestComponents.BaseTest;
import com.saucedemo.TestData.TestDataProvider;
import com.saucedemo.Pages.CartPage;

public class CartTest extends BaseTest {

	/**
	 * TC_CART_001: [P] User able to add product(s) to cart and product(s) are
	 * displayed correctly in Your Cart Page
	 */
	@Test(dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void addProductToCart(String username, String password, String firstname, String lastname, String postal,
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

		// Initialize a map to collect product prices displayed on Products Page
		Map<String, Double> priceInProducts = new HashMap<>();

		// Add items to the cart
		int cart_count = 0;
		for (String product : prod_addToCart) {
			productsPage.addToCart(product);
			System.out.println("Product: [" + product + "] is added to Cart");

			double price = productsPage.getPriceOfProducts(product);
			priceInProducts.put(product, price);

			cart_count++;
		}
		System.out.println("Total [" + cart_count + "] product(s) in Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");

		// Navigate to the CartPage
		CartPage cartPage = productsPage.goToCart();
		Assert.assertEquals(cartPage.pageTitle(), "Your Cart", "User is not redirected to Your Cart Page.");

		// Verify that prices in the Your Cart Page match prices displayed on the
		// Products Page
		for (String product : prod_addToCart) {
			Double priceInCartPage = cartPage.getPriceInPage(product);
			Double priceOfProducts = priceInProducts.get(product);

			Assert.assertEquals(priceInCartPage, priceOfProducts,
					"Price in Your Cart Page do not match Price displayed on Products Page.");
		}

	}

	/**
	 * TC_CART_002: [P] User able to continue shopping without checkout after added
	 * product(s) to cart from Your Cart Page
	 */
	@Test(dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void continueShopping(String username, String password, String firstname, String lastname, String postal,
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

		// Navigate to the CartPage
		CartPage cartPage = productsPage.goToCart();
		Assert.assertEquals(cartPage.pageTitle(), "Your Cart", "User is not redirected to Your Cart Page.");

		// Navigate to the Products Page
		cartPage.continueShopping();
	}

	/**
	 * TC_CART_003: [P] User able to remove item(s) in Cart from Your Cart Page
	 */
	@Test(dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void removeProductFromCart(String username, String password, String firstname, String lastname,
			String postal, String addProductList, String removeProductList) throws IOException, InterruptedException {
		// Login and navigate to Products Page
		ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");

		// Extract products names obtained from Excel data file
		String[] prod_addToCart = addProductList.split(",");
		String[] prod_removeFromCart = removeProductList.split(",");

		// Trim whitespace from add products name
		for (int i = 0; i < prod_addToCart.length; i++) {
			prod_addToCart[i] = prod_addToCart[i].trim();
		}

		// Trim whitespace from remove products name
		for (int i = 0; i < prod_removeFromCart.length; i++) {
			prod_removeFromCart[i] = prod_removeFromCart[i].trim();
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

		// Navigate to the CartPage
		CartPage cartPage = productsPage.goToCart();
		Assert.assertEquals(cartPage.pageTitle(), "Your Cart", "User is not redirected to Your Cart Page.");

		// Remove items from the cart
		for (String product : prod_removeFromCart) {
			cartPage.removeFromCart(product);
			System.out.println("Product: [" + product + "] is removed from Cart");
			cart_count--;
		}
		System.out.println("Total [" + cart_count + "] product(s) in Cart");
		Assert.assertEquals(productsPage.hasItemInCart(), cart_count, "Total number of items in Cart is incorrect.");

	}
}
