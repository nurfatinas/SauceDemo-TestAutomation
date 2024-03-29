package com.saucedemo.Test;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.Pages.LoginPage;
import com.saucedemo.Pages.ProductsPage;
import com.saucedemo.TestComponents.BaseTest;
import com.saucedemo.TestComponents.Retry;
import com.saucedemo.TestData.LoginDataProvider;

public class AuthenticationTest extends BaseTest {
	
    /**
     * TC_LOGIN_001: [P] User login using valid userName and password 
     */      
	@Test(dataProvider = "getValidCred", dataProviderClass = LoginDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInSuccessful_validCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page!");
	}

    /**
     * TC_LOGIN_002: [N] User login using invalid userName or password
     */
	@Test(dataProvider = "getInvalidCred", dataProviderClass = LoginDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_invalidCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
	}
	
    /**
     * TC_LOGIN_003: [N] User login using blank userName or password *blank userName
     */
	@Test(dataProvider = "getBlankCred", dataProviderClass = LoginDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_blankUsername(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin("", input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
	}
	
    /**
     * TC_LOGIN_003: [N] User login using blank userName or password *blank password
     */
	@Test(dataProvider = "getBlankCred", dataProviderClass = LoginDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_blankPassword(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), "");
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
	}
	
    /**
     * TC_LOGIN_004: [N] User login using locked credential's account
     */
	@Test(dataProvider = "getLockedCred", dataProviderClass = LoginDataProvider.class, groups = {"Login"}, retryAnalyzer = Retry.class)
	public void logInUnsuccessful_locked(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.");
	}
	
    /**
     * TC_LOGOUT_001: [N] User logout from Sauce Demo website
     */
	@Test (dataProvider = "getLogin", dataProviderClass = LoginDataProvider.class, groups = {"Logout"}, retryAnalyzer = Retry.class)
	public void logout(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.pageTitle(), "Products", "User is not redirected to Products Page.");
		LoginPage loginPage = productsPage.logout();
	    Assert.assertEquals("https://www.saucedemo.com/", loginPage.getUrl(), "Logout Not Successful");
	}
	
}


