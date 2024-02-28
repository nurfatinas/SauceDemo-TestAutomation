package com.saucedemo.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucedemo.Resources.ExtentReport;

/**
 * Listeners class implements the ITestListener interface to customize test execution behavior
 * It extends the BaseTest class and uses Extent Reports for logging and reporting
 */

public class Listeners extends BaseTest implements ITestListener{
	
    // ExtentTest object to manage the test in the Extent report
	ExtentTest test;
	
    // ExtentReports object obtained from the ExtentReport class
	ExtentReports extent = ExtentReport.getReportObject();
	
    // ThreadLocal to handle parallel test execution and avoid interference between threads
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); 
	
	@Override
	public void onTestStart(ITestResult result) {
        // Create a new ExtentTest for the current test method
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); ;
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
        // Log the test as passed in the Extent report
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
        // Log the test as failed and add a screenshot to the Extent report
		test.fail(result.getThrowable());
		
		try {
            // Obtain the WebDriver instance from the BaseTest class
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
        // Capture a screenshot and add it to the Extent report
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromBase64String(filePath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
        // Test skipped behavior can be implemented if needed
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Test failed but within success percentage behavior can be implemented if needed
		
	}

	@Override
	public void onStart(ITestContext context) {
        // Test context start behavior can be implemented if needed
		
	}

	@Override
	public void onFinish(ITestContext context) {
        // Flush the ExtentReports instance to generate the final test report
		extent.flush();
		
	}
	

}