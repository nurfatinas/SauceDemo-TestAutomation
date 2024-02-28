package com.nurfatinportfolio.Pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.nurfatinportfolio.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#user-name")
	WebElement usernameInput;
	
	@FindBy(css="#password")
	WebElement passwordInput;
	
	@FindBy(css="#login-button")
	WebElement loginButton;
	
	@FindBy(css="[data-test='error']")
	WebElement errorPrompt;
	
	@FindBy(css="[class='app_logo']")
	WebElement swagLabsHeader;
	
	public ProductsPage userLogin(String username, String password){
		goTo();
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
		ProductsPage productsPage = new ProductsPage(driver);
		
		return productsPage;
	}
	
	public boolean errorPromptExists(){
		try {
			waitForWebElementToAppear(errorPrompt);
			return true;

		} catch(TimeoutException e) {
			return false;
			
		}
	}
	public String getErrorText() {
		return errorPrompt.getText();  
	}
	
	public String getUrl(){
		String url = driver.getCurrentUrl();
		
		return url;
	}
		
	public void goTo(){
		driver.get("https://www.saucedemo.com/");
		
	}
	
	
	
	
}
