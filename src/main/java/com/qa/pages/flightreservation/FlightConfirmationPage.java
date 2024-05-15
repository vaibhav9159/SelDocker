package com.qa.pages.flightreservation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qa.pages.AbstractPage;

public class FlightConfirmationPage extends AbstractPage{

	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
		
	}
	
	private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
	
	@FindBy(css=".card-body .row:nth-of-type(1) div:nth-of-type(2)")
	WebElement flightConfirmationID;
	
	@FindBy(css=".card-body .row:nth-of-type(3) div:nth-of-type(2)")
	WebElement totalPrice;

	@Override
	public boolean specificElementIsPresent() {
		
		 this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationID));
		 return this.flightConfirmationID.isDisplayed();
	}
	
	public String getFlightConfirmationID() {
		log.info("flightConfirmationID-->"+flightConfirmationID.getText());
		return flightConfirmationID.getText();
	}
	
	public String getTotalPrice() {
		log.info("totalPrice-->"+totalPrice.getText());
		return totalPrice.getText();
	}
	
	public void scrollDown() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,300)");
	}

}
