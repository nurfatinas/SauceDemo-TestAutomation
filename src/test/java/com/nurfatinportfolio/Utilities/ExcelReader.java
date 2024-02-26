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
	public ArrayList<String> getExcelData(String dataName) throws IOException {
	
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\nurfatinportfolio\\TestData\\productData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    
		try {
	    
		int sheets = workbook.getNumberOfSheets();
		for (int i=0; i<sheets; i++)
		{
			if (workbook.getSheetName(i).equalsIgnoreCase("Data")) 
			{
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next();
				
				Iterator<Cell> ce = firstrow.cellIterator();
				
				int k = 0;
				int column = 0;
				while(ce.hasNext())
				{
					Cell value = ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
					{
						column = k;

					}
					k++;
				}
		        //System.out.println("Column: "+ column);
		        
		        while(rows.hasNext())
		        {
		        	Row r = rows.next();
		        	if(r.getCell(column).getStringCellValue().equalsIgnoreCase(dataName))
		        	{
						Iterator<Cell> cv = r.cellIterator();
				        while(cv.hasNext())
				        {
				        	Cell c = cv.next();
				        	if (c.getCellType() == CellType.STRING) 
				        	{
				        	    a.add(c.getStringCellValue());
				        	} else {
				        		
				        	    a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
				        	}
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
	


	

