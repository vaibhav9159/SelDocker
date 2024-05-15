package com.qa.tests.flightreservation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.*;

import com.qa.pages.flightreservation.FlightConfirmationPage;
import com.qa.pages.flightreservation.FlightSearchPage;
import com.qa.pages.flightreservation.FlightSelectionPage;
import com.qa.pages.flightreservation.RegistrationConfirmationPage;
import com.qa.pages.flightreservation.RegistrationPage;
import com.qa.tests.base.BaseTest;
import com.qa.utils.Config;
import com.qa.utils.Constants;

public class FlightReservationTest extends BaseTest{

	private String passengersCount;
	private String expectedPrice;
	private RegistrationPage registrationpage;
	private RegistrationConfirmationPage registrationconfirmationPage;
	
	@BeforeMethod
	@Parameters({"passengersCount","expectedPrice"})
	public void setDriver(String passengersCount, String expectedPrice) {
		this.passengersCount=passengersCount;
		this.expectedPrice= expectedPrice;
	}
	
	@Test
	public void userRegistrationTest() {
	//	registrationpage = new RegistrationPage(driver);
		this.registrationpage = new RegistrationPage(driver);
	//	registrationpage.goToURL(Config.get(Constants.FLIGHT_RESERVATION_URL));
		registrationpage.goToURL("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
		
		AssertJUnit.assertTrue(registrationpage.specificElementIsPresent());
		registrationpage.enterUserDetails("Kashi Vishvanath", "Varansi");
		registrationpage.enterUserCredentials("kashi@uttarPradesh.co.in", "GauriShankar");
		registrationpage.enterUserAddress("80 ghat Kashi", "Varanasi", "226021");
		registrationpage.Register();
	}
	
	@Test(dependsOnMethods = "userRegistrationTest")
	public void userConfirmationTest() {
		this.registrationconfirmationPage = new RegistrationConfirmationPage(driver);
		AssertJUnit.assertTrue(registrationconfirmationPage.specificElementIsPresent());
		
		registrationconfirmationPage.goToFlightSearch();
	}
	
	@Test(dependsOnMethods = "userConfirmationTest")
	public void flightSearchTest() {
		FlightSearchPage  FlightSearchPage = new FlightSearchPage(driver);
		AssertJUnit.assertTrue(FlightSearchPage.specificElementIsPresent());
		
		FlightSearchPage.maximizeWindow();
		FlightSearchPage.selectPassengers(passengersCount);
		FlightSearchPage.clickSearchFlightButton();
	}
	
	@Test(dependsOnMethods = "flightSearchTest")
	public void FlightSelectionTest() throws InterruptedException {
		FlightSelectionPage  FlightSelectionPage = new FlightSelectionPage(driver);
		AssertJUnit.assertTrue(FlightSelectionPage.specificElementIsPresent());
		
		FlightSelectionPage.selectFlights();
		FlightSelectionPage.scrollDown();
		FlightSelectionPage.confirmFlightButton();	
	}
	
	@Test (dependsOnMethods = "FlightSelectionTest")
	public void flightConfirmationTest() {
		FlightConfirmationPage  FlightConfirmationPage = new FlightConfirmationPage(driver);
		AssertJUnit.assertTrue(FlightConfirmationPage.specificElementIsPresent());
		
		Assert.assertNotNull(FlightConfirmationPage.getTotalPrice());	
		Assert.assertNotNull(FlightConfirmationPage.getFlightConfirmationID());
		AssertJUnit.assertEquals(FlightConfirmationPage.getTotalPrice(), expectedPrice);
	}

}
