package com.saucedemo.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentReport class provides a method to obtain an ExtentReports object configured with ExtentSparkReporter
 * The report is configured with a specified path, report name, and document title
 * Tester information and initial test creation are also set up
 */

public class ExtentReport {
	
	/**
	 * Obtain an ExtentReports object with configured ExtentSparkReporter.
	 * @return Configured ExtentReports object.
	 */
	
	public static ExtentReports getReportObject()
	{
		// Define the path for the HTML report file
		String path = System.getProperty("user.dir")+"//reports//index.html";
		
		// Create an ExtentSparkReporter with the specified path
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		// Create an ExtentReports object and attach the ExtentSparkReporter
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		// Set system information, e.g., tester's name
		extent.setSystemInfo("Tester", "Nurfatin Abdullah Shuhaimy");
		
		// Create an initial test in the report using the specified path
		extent.createTest("Test Results - SauceDemo WebUI Automation");
		
		// Return the configured ExtentReports object
		return extent;
		
	}

}
