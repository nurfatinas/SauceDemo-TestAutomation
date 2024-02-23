package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestComponents.Retry;

public class Login extends BaseTest {
	
	
	// TC_LOGIN_001: [P] User login using valid username and password        
	@Test(dataProvider = "getValidCred", groups = {"LoginSucess"}, retryAnalyzer = Retry.class)
	public void testLoginWithValidCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
	}
	
	// TC_LOGIN_002: [N] User login using invalid username or password
	@Test(dataProvider = "getIncorrectCred", groups = {"LoginIncorrect"}, retryAnalyzer = Retry.class)
	public void testLoginWithInvalidCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
	}
	
	// TC_LOGIN_003: [N] User login using blank username or password *blank username
	@Test(dataProvider = "getBlankCred", groups = {"LoginIncorrect"}, retryAnalyzer = Retry.class)
	public void testLoginWithBlankUsername(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin("", input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
	}
	
	// TC_LOGIN_003: [N] User login using blank username or password *blank password
	@Test(dataProvider = "getBlankCred", groups = {"LoginIncorrect"}, retryAnalyzer = Retry.class)
	public void testLoginWithBlankPassword(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), "");
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
	}
	
	// TC_LOGIN_004: [N] User login using locked credential's account
	@Test(dataProvider = "getLockedCred", groups = {"LoginLocked"}, retryAnalyzer = Retry.class)
	public void testLoginWithLockedCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.");
	}
	
	
	
	// Test data for valid username and password.
	@DataProvider
	public Object[][] getValidCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginValidCred.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}, {data.get(4)}};	
	}
	
	// Test data for invalid username or password.
	@DataProvider
	public Object[][] getIncorrectCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginInvalidCred.json");
		return new Object [][] {{data.get(1)},{data.get(2)}};	
	}
	
	// Test data for locked user's credential.
	@DataProvider
	public Object[][] getLockedCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginInvalidCred.json");
		return new Object [][] {{data.get(0)}};	
	}
	
	// Test data for valid username or password.
	@DataProvider
	public Object[][] getBlankCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginValidCred.json");
		return new Object [][] {{data.get(0)}};	
	}
		
}
