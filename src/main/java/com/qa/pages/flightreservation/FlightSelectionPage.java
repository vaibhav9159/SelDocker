package com.qa.pages.flightreservation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.AbstractPage;

public class FlightSelectionPage extends AbstractPage{

	public FlightSelectionPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(css="input[name='departure-flight']")
	List<WebElement> departureFlights;
	
	@FindBy(css="input[name='arrival-flight']")
	List<WebElement> arrivalFlights;
	
	@FindBy(css="#confirm-flights")
	WebElement confirmFlightButton;
	
	@Override
	public boolean specificElementIsPresent() {
		this.wait.until(ExpectedConditions.visibilityOf(confirmFlightButton));
		return confirmFlightButton.isDisplayed();
	}
	
	public void selectFlights() {
		int random = ThreadLocalRandom.current().nextInt(0,departureFlights.size());
		departureFlights.get(random).click();
		arrivalFlights.get(random).click();
	}
	public void confirmFlightButton() throws InterruptedException {
		Thread.sleep(2000);
		confirmFlightButton.click();
	}
	public void scrollDown() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,300)");
	}
}
