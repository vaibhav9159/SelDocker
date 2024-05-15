package com.qa.tests.vendorportal;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.pages.vendorportal.DashboardPage;
import com.qa.pages.vendorportal.LoginPage;
import com.qa.tests.base.BaseTest;
import com.qa.tests.vendorportal.model.VendorPortalTestData;
import com.qa.utils.Config;
import com.qa.utils.Constants;
import com.qa.utils.JsonUtil;

public class VendorPortalTest extends BaseTest{

	private LoginPage loginpage;
	private DashboardPage dashboardpage;
	private VendorPortalTestData testData;
	
	@BeforeMethod
	@Parameters("testDataPath")
	public void setDriver(String testDataPath) {
		this.loginpage = new LoginPage(driver);
		this.dashboardpage = new DashboardPage(driver);
		this.testData = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
	}
	
	@Test
	public void login() {
	
	//	loginpage.goToUrl(Config.get(Constants.VENDOR_PORTAL_URL));
		loginpage.goToUrl("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
		
		Assert.assertTrue(loginpage.specificElementIsPresent())	;
		
//		loginpage.enterUserCredsAndLogin("sam", "sam");
		loginpage.enterUserCredsAndLogin(testData.username(), testData.password());
	}
	
	@Test(dependsOnMethods="login")
	public void dashBoardTest() {
		
			Assert.assertTrue(dashboardpage.specificElementIsPresent())	;
		
			Assert.assertEquals(dashboardpage.getMonthlyEarning(), testData.monthlyEarning());
	        Assert.assertEquals(dashboardpage.getAnnualEarning(), testData.annualEarning());
	        Assert.assertEquals(dashboardpage.getProfitMargin(), testData.profitMargin());
	        Assert.assertEquals(dashboardpage.getAvailableInventory(), testData.availableInventory());

	        // order history search
	        dashboardpage.searchOrderHistoryBy(testData.searchKeyword());
	        Assert.assertEquals(dashboardpage.getSearchResultsCount(), testData.searchResultsCount());
		
	}
	
	 @Test(dependsOnMethods = "dashBoardTest")
	    public void logoutTest(){
		 	dashboardpage.logout();
	        Assert.assertTrue(loginpage.specificElementIsPresent());
	    }
	
}




