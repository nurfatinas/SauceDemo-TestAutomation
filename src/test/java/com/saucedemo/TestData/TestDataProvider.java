package com.saucedemo.TestData;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.saucedemo.TestComponents.BaseTest;

public class TestDataProvider extends BaseTest{
	
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
     * Test data from Excel.
     */ 
	@DataProvider
	public Object[][] fromExcel() throws IOException
	{
	    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\saucedemo\\TestData\\testData.xlsx");
	    XSSFWorkbook wb = new XSSFWorkbook(fis);
	    XSSFSheet sheet = wb.getSheetAt(0);

	    int rowCount = sheet.getPhysicalNumberOfRows();
	    int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	    Object data[][] = new Object[rowCount - 1][colCount];

	    for (int i = 0; i < rowCount - 1; i++) {
	        XSSFRow row = sheet.getRow(i + 1);
	        for (int j = 0; j < colCount; j++) {
	            XSSFCell cell = row.getCell(j);
	            data[i][j] = formatter.formatCellValue(cell);
	        }
	    }
	    wb.close();
	    fis.close();
	    return data;	
	}
	
}