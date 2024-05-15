package com.qa.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.qa.pages.AbstractPage;

public class FlightSearchPage extends AbstractPage{

	public FlightSearchPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(css="#passengers")
	WebElement passengers;
	
	@FindBy(css=" #search-flights")
	WebElement searchFlightButton;
	
	
	@Override
	public boolean specificElementIsPresent() {
		this.wait.until(ExpectedConditions.visibilityOf(this.searchFlightButton));
		return searchFlightButton.isDisplayed();
	}

	public void selectPassengers(String passengersCount) {
		Select s = new Select(this.passengers);
		s.selectByValue(passengersCount);
	}
	
	public void maximizeWindow() {
		this.driver.manage().window().maximize();
	}
	
	public void clickSearchFlightButton() {
		searchFlightButton.click();
	}
	
	
}
