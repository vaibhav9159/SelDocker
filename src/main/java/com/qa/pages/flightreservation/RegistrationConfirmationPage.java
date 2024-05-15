package com.qa.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage{

	
	@FindBy(css = "#go-to-flights-search")
	private WebElement goToFlightSearchButton;

	
	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToFlightSearch() {
		this.goToFlightSearchButton.click();
	}

	@Override
	public boolean specificElementIsPresent() {
		this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
		return this.goToFlightSearchButton.isDisplayed();
	}
	
}
