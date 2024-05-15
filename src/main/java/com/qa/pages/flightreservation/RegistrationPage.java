package com.qa.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.AbstractPage;

public class RegistrationPage extends AbstractPage{

//	private WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(css = "input#firstName")
	private WebElement firstNameInput;

	@FindBy(css = "input#lastName")
	private WebElement lastNameInput;

	@FindBy(css = "input#email")
	private WebElement email;

	@FindBy(css = "input#password")
	private WebElement password;

	@FindBy(css = "input[name='street']")
	private WebElement street;

	@FindBy(css = "input[name='city']")
	private WebElement city;

	@FindBy(css = "#inputState")
	private WebElement state;

	@FindBy(css = "input[name='zip']")
	private WebElement zip;
	
	@FindBy(css = "	#register-btn")
	private WebElement registerButton;


	public void goToURL(String url) {
		this.driver.get(url);
	}
	
	public void enterUserDetails(String firstName, String lastName ) {
		this.firstNameInput.sendKeys(firstName);
		this.lastNameInput.sendKeys(lastName);
	}
	
	public void enterUserCredentials(String email, String pass ) {
		this.email.sendKeys(email);
		this.password.sendKeys(pass);
	}
	
	public void enterUserAddress(String street, String city, String zip ) {
		this.street.sendKeys(street);
		this.city.sendKeys(city);
		this.zip.sendKeys(zip);
	}
	
	public void Register() {
		this.registerButton.click();
	}

	@Override
	public boolean specificElementIsPresent() {
		this.wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
		return firstNameInput.isDisplayed();
		
	}
	
}
