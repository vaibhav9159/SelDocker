package com.qa.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.AbstractPage;

public class LoginPage extends AbstractPage{

	public LoginPage(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(css="#username")
	WebElement loginUser;
	
	@FindBy(css="#password")
	WebElement loginPass;
	
	@FindBy(css="#login")
	WebElement loginButton;
	
	@Override
	public boolean specificElementIsPresent() {
		
		this.wait.until(ExpectedConditions.visibilityOf(loginButton));
		return this.loginButton.isDisplayed();
	}
	
	public void goToUrl(String url) {
		this.driver.get(url);
	}
	
	public void enterUserCredsAndLogin(String u, String p) {
		this.loginUser.sendKeys(u);
		this.loginPass.sendKeys(p);
		this.loginButton.click();
	}

}
