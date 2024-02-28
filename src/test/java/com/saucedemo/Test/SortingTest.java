package com.saucedemo.Test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.TestComponents.BaseTest;
import com.saucedemo.TestData.TestDataProvider;

public class SortingTest extends BaseTest {
	

	
	@Test (dataProvider = "fromExcel", dataProviderClass = TestDataProvider.class)
	public void ExcelReadingData(String username, String password, String firstname, String lastname, String postal) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(username, password);
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		
	    System.out.println("First Name: " + firstname);
	    System.out.println("Last Name: " + lastname);
	    System.out.println("Postal: " + postal);
	}
	
	
	

}
