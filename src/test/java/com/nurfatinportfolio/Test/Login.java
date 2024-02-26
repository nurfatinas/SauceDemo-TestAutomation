package com.nurfatinportfolio.Test;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nurfatinportfolio.Pages.ProductsPage;
import com.nurfatinportfolio.TestComponents.BaseTest;
import com.nurfatinportfolio.TestComponents.Retry;
import com.nurfatinportfolio.TestData.ExtDataProvider;

public class Login extends BaseTest {
	
	
    /**
     * TC_LOGIN_001: [P] User login using valid userName and password 
     */      
	@Test(dataProvider = "getValidCred", dataProviderClass = ExtDataProvider.class, groups = {"LoginSucess"}, retryAnalyzer = Retry.class)
	public void testLoginWithValidCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		ProductsPage productsPage = loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertEquals(productsPage.productsPageHeader(), "Products", "User is not redirected to Products Page!");
	}

    /**
     * TC_LOGIN_002: [N] User login using invalid userName or password
     */
	@Test(dataProvider = "getIncorrectCred", dataProviderClass = ExtDataProvider.class, groups = {"LoginIncorrect"}, retryAnalyzer = Retry.class)
	public void testLoginWithInvalidCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
	}
	
    /**
     * TC_LOGIN_003: [N] User login using blank userName or password *blank userName
     */
	@Test(dataProvider = "getBlankCred", dataProviderClass = ExtDataProvider.class, groups = {"LoginIncorrect"}, retryAnalyzer = Retry.class)
	public void testLoginWithBlankUsername(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin("", input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
	}
	
    /**
     * TC_LOGIN_003: [N] User login using blank userName or password *blank password
     */
	@Test(dataProvider = "getBlankCred", dataProviderClass = ExtDataProvider.class, groups = {"LoginIncorrect"}, retryAnalyzer = Retry.class)
	public void testLoginWithBlankPassword(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), "");
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
	}
	
    /**
     * TC_LOGIN_004: [N] User login using locked credential's account
     */
	@Test(dataProvider = "getLockedCred", dataProviderClass = ExtDataProvider.class, groups = {"LoginLocked"}, retryAnalyzer = Retry.class)
	public void testLoginWithLockedCredential(HashMap<String, String> input) throws IOException, InterruptedException
	{
		loginPage.userLogin(input.get("username"), input.get("password"));
		Assert.assertTrue(loginPage.errorPromptExists(), "Login error prompt not visible!");
		Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.");
	}
	

}
