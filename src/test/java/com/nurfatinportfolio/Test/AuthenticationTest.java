package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nurfatinportfolio.Pages.LoginPage;
import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestComponents.Retry;
import com.nurfatinportfolio.TestData.ExtDataProvider;

public class AuthenticationTest extends BaseTest {
	
    /**
     * TC_LOGIN_001: [P] User login using valid userName and password 
     */      
	@Test(dataProvider = "getValidCred", dataProviderClass = ExtDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInSuccessful_validCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
	}

    /**
     * TC_LOGIN_002: [N] User login using invalid userName or password
     */
	@Test(dataProvider = "getInvalidCred", dataProviderClass = ExtDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_invalidCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
	}
	
    /**
     * TC_LOGIN_003: [N] User login using blank userName or password *blank userName
     */
	@Test(dataProvider = "getBlankCred", dataProviderClass = ExtDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_blankUsername(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin("", input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
	}
	
    /**
     * TC_LOGIN_003: [N] User login using blank userName or password *blank password
     */
	@Test(dataProvider = "getBlankCred", dataProviderClass = ExtDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_blankPassword(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), "");
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
	}
	
    /**
     * TC_LOGIN_004: [N] User login using locked credential's account
     */
	@Test(dataProvider = "getLockedCred", dataProviderClass = ExtDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_locked(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.");
	}
	
    /**
     * TC_LOGOUT_001: [N] User logout from Sauce Demo website
     */
	@Test (dataProvider = "getLogin", dataProviderClass = ExtDataProvider.class, groups = {"Logout"}, retryAnalyzer = Retry.class)
	public void logout(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page.");
		LoginPage loginPage = productsPage.logout();
	    Assert.assertEquals("https://www.saucedemo.com/", loginPage.getUrl(), "Logout Not Successful");
	}
	
}


