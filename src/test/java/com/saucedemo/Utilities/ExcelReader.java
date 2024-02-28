package com.saucedemo.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.saucedemo.TestComponents.BaseTest;

/**
 * ExcelReader class extends BaseTest and provides methods to read data from an Excel workbook
 * It specifically retrieves data based on the specified column header (ProductList) from the "Data" sheet
 */

public class ExcelReader extends BaseTest{
	
	/**
     * Retrieve data from the Excel workbook based on the specified column header.
     * @param ProductList The column header indicating the data to be retrieved.
     * @return ArrayList of strings containing the data from the specified column.
     * @throws IOException If there is an issue reading the Excel file.
     */
	
	
    public ArrayList<String> getExcelData(String ProductList) throws IOException {

        // ArrayList to store the retrieved data
        ArrayList<String> a = new ArrayList<String>();

        // FileInputStream to read the Excel file
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\saucedemo\\TestData\\products.xlsx");

        // XSSFWorkbook to represent the Excel workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        try {
            // Iterate through sheets in the workbook
            int sheets = workbook.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                if (workbook.getSheetName(i).equalsIgnoreCase("Data")) {
                    // Retrieve the specified sheet
                    XSSFSheet sheet = workbook.getSheetAt(i);

                    // Iterate through rows in the sheet
                    Iterator<Row> rows = sheet.iterator();
                    Row firstrow = rows.next();

                    // Initialize column index to -1
                    int column = -1;

                    // Iterate through cells in the first row to find the specified column header
                    Iterator<Cell> ce = firstrow.cellIterator();
                    int k = 0;
                    while (ce.hasNext()) {
                        Cell value = ce.next();
                        if (value.getStringCellValue().equalsIgnoreCase(ProductList)) {
                            column = k;
                        }
                        k++;
                    }

                    // Throw an exception if the specified column header is not found
                    if (column == -1) {
                        throw new RuntimeException("Column with header " + ProductList + " not found in the sheet.");
                    }

                    // Iterate through remaining rows and retrieve data from the specified column
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

        // Return the ArrayList containing the retrieved data
        return a;
    }

}
	


	

