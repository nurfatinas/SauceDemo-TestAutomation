package com.nurfatinportfolio.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.nurfatinportfolio.TestComponents.BaseTest;


public class ExcelReader extends BaseTest{
	
	@Test
	public ArrayList<String> getExcelData(String ProductList) throws IOException {

	    ArrayList<String> a = new ArrayList<String>();
	    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\products.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);

	    try {
	        int sheets = workbook.getNumberOfSheets();
	        for (int i = 0; i < sheets; i++) {
	            if (workbook.getSheetName(i).equalsIgnoreCase("Data")) {
	                XSSFSheet sheet = workbook.getSheetAt(i);

	                Iterator<Row> rows = sheet.iterator();
	                Row firstrow = rows.next();

	                int column = -1; // Initialize column to -1, indicating it hasn't been found yet
	                Iterator<Cell> ce = firstrow.cellIterator();

	                int k = 0;
	                while (ce.hasNext()) {
	                    Cell value = ce.next();
	                    if (value.getStringCellValue().equalsIgnoreCase(ProductList)) {
	                        column = k;
	                    }
	                    k++;
	                }

	                if (column == -1) {
	                    throw new RuntimeException("Column with header " + ProductList + " not found in the sheet.");
	                }

	                while (rows.hasNext()) {
	                    Row r = rows.next();
	                    if (r.getCell(column) != null) {
	                        Cell c = r.getCell(column);
	                        if (c.getCellType() == CellType.STRING) {
	                            a.add(c.getStringCellValue());
	                        } else {
	                            a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
	                        }
	                    }
	                }
	            }
	        }

	    } finally {
	        // Close the workbook and file stream in a finally block to ensure they are closed even if an exception occurs.
	        if (workbook != null) {
	            workbook.close();
	        }
	        if (fis != null) {
	            fis.close();
	        }
	    }

	    return a;
	}


}
	


	

