package com.saucedemo.Pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

import com.saucedemo.AbstractComponents.AbstractComponents;

public class CheckoutCompletePage extends AbstractComponents {

	WebDriver driver;
	
	public CheckoutCompletePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	


}
