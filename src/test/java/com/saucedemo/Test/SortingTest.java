package com.saucedemo.Test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.TestComponents.BaseTest;
import com.saucedemo.TestData.TestDataProvider;

public class SortingTest extends BaseTest {
	
    /** 
     * TC_SORT_001: [P] Sorting products by name in alphabetical ascending order
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void sortProductNameAscending(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
	    // User login and navigate to Products Page
		ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
		
	    // Select "Name (A to Z)" from the sorting drop-down options
		productsPage.selectSortDropdown("Name (A to Z)");
		
	    // Check products are sorted in ascending order
		boolean result  = productsPage.areProductsSortedAscending();
        Assert.assertTrue(result, "Products are not sorted in ascending order");
	}
	
    /** 
     * TC_SORT_002: [P] Sorting products by name in alphabetical descending order
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void sortProductNameDescending(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
	    // User login and navigate to Products Page
		ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
		
	    // Select "Name (Z to A)" from the sorting drop-down options
		productsPage.selectSortDropdown("Name (Z to A)");	
		
	    // Check products are sorted in descending order
		boolean result  = productsPage.areProductsSortedDescending();
        Assert.assertTrue(result, "Products are not sorted in descending order");
	}
	
    /** 
     * TC_SORT_003: [P] Sorting products by price in ascending order
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void sortProductPriceAscending(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
	    // User login and navigate to Products Page
		ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
		
	    // Select "Price (low to high)" from the sorting drop-down options
		productsPage.selectSortDropdown("Price (low to high)");	
		
	    // Check products are sorted in descending order
		boolean result  = productsPage.arePricesSortedAscending();
		Assert.assertTrue(result, "Products are not sorted in ascending order");
	}
	
    /** 
     * TC_SORT_004: [P] Sorting products by price in descending order
     */
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void sortProductPriceDescending(String username, String password, String firstname, String lastname, String postal, String addProductList, String removeProductList) throws IOException, InterruptedException
	{
	    // User login and navigate to Products Page
		ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
		
	    // Select "Price (high to low)" from the sorting drop-down options
		productsPage.selectSortDropdown("Price (high to low)");	
		
	    // Check products are sorted in descending order
		boolean result  = productsPage.arePricesSortedDescending();
		Assert.assertTrue(result, "Products are not sorted in descending order");
	}

}
	



