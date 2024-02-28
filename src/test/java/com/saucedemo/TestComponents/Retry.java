package com.saucedemo.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry class implements the IRetryAnalyzer interface to control test retry behavior
 * It allows a test method to be retried a specified number of times (maxTry) on failure
 */

public class Retry implements IRetryAnalyzer {
	
    // Counter to keep track of the number of retries
	int count = 0;
	
    // Maximum number of retries allowed
	int maxTry = 1;
		
    /**
     * Implements the retry logic based on the count and maxTry.
     * @param result The ITestResult object representing the result of the test method.
     * @return True if the test should be retried, false otherwise.
     */
	
		@Override
		public boolean retry(ITestResult result) {
			// TODO Auto-generated method stub
			if(count<maxTry)
			{
	            // Increment the retry count and return true to retry the test
				count++;
				return true;
			}
			
	        // Return false to indicate that no more retries should be attempted
			return false;
		}
}
