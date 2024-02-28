package com.saucedemo.TestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.DataProvider;
import com.saucedemo.TestComponents.BaseTest;

public class LoginDataProvider extends BaseTest{
	
	DataFormatter formatter = new DataFormatter();
	
    /**
     * Test data for valid userName and password.
     */
	@DataProvider
	public Object[][] getLogin() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\saucedemo\\TestData\\mainLogin.json");
		return new Object [][] {{data.get(0)}};	
	}
	
    /**
     * Test data for valid userName and password.
     */
	@DataProvider
	public Object[][] getValidCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\saucedemo\\TestData\\loginValidCred.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}, {data.get(4)}};	
	}
	
	 /**
     * Test data for invalid userName or password.
     */
	@DataProvider
	public Object[][] getInvalidCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\saucedemo\\TestData\\loginInvalidCred.json");
		return new Object [][] {{data.get(1)},{data.get(2)}};	
	}
	
	 /**
     * Test data for locked user's credential.
     */ 
	@DataProvider
	public Object[][] getLockedCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\saucedemo\\TestData\\loginInvalidCred.json");
		return new Object [][] {{data.get(0)}};	
	}
	
	 /**
     * Test data for valid userName or password.
     */ 
	@DataProvider
	public Object[][] getBlankCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\saucedemo\\TestData\\loginValidCred.json");
		return new Object [][] {{data.get(0)}};	
	}

}