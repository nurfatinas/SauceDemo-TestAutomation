package com.saucedemo.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.AbstractComponents.AbstractComponents;

public class CheckoutOverviewPage extends AbstractComponents {

	WebDriver driver;
	
	public CheckoutOverviewPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	


}
