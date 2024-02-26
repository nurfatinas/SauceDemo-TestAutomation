package com.nurfatinportfolio.TestData;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;
import com.nurfatinportfolio.TestComponents.BaseTest;

public class ExtDataProvider extends BaseTest{
	
	
    /**
     * Test data for valid userName, password and product.
     */
	@DataProvider
	public Object[][] producData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\productData.json");
		return new Object [][] {{data.get(0)}};	
	}
	
    /**
     * Test data for valid userName, password and multiple products.
     */
	@DataProvider
	public Object[][] multipleProductsData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\productData.json");
		return new Object [][] {{data.get(1)}};	
	}
	
    /**
     * Test data for valid userName and password.
     */
	@DataProvider
	public Object[][] getValidCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginValidCred.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}, {data.get(4)}};	
	}
	
	 /**
     * Test data for invalid userName or password.
     */
	@DataProvider
	public Object[][] getIncorrectCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginInvalidCred.json");
		return new Object [][] {{data.get(1)},{data.get(2)}};	
	}
	
	 /**
     * Test data for locked user's credential.
     */ 
	@DataProvider
	public Object[][] getLockedCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginInvalidCred.json");
		return new Object [][] {{data.get(0)}};	
	}
	
	 /**
     * Test data for valid userName or password.
     */ 
	@DataProvider
	public Object[][] getBlankCred() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\loginValidCred.json");
		return new Object [][] {{data.get(0)}};	
	}
	
}