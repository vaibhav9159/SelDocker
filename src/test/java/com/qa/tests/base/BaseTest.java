package com.qa.tests.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.qa.utils.Config;
import com.qa.utils.Constants;

import Listeners.TestListener;

@Listeners({TestListener.class})
public abstract class BaseTest {

	protected WebDriver driver;
	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
	
//	@BeforeTest
//	public void setDriver() {
//		if(Boolean.getBoolean("selenium.grid.enabled")) {
//			try {
//				this.driver= getRemoteDriver();
//			} catch (MalformedURLException e) {
//				
//				e.printStackTrace();
//			}
//		}else {
//			this.driver= getLocalDriver();
//		}
//		
//		this.driver= new ChromeDriver();
//	}
	
	
	@BeforeSuite
	public void setupConfig() {
		Config.initialize();
	}
	
	@BeforeTest
	public void setDriver(ITestContext ct) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver() ;
        ct.setAttribute(Constants.DRIVER, this.driver);
	}
	
	
	
	
//	private WebDriver getRemoteDriver() {
//		Capabilities c;
//		if(System.getProperty("browser").equalsIgnoreCase("chrome")) {
//			c = new ChromeOptions();
//		}
//		else {
//			c = new FirefoxOptions();
//		}
//		try {
//			return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),c);
//		} catch (MalformedURLException e) {
//			
//			e.printStackTrace(); return null;
//		}
//	}
	
	 private WebDriver getRemoteDriver() throws MalformedURLException {
	        Capabilities capabilities = new ChromeOptions();
	        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
	            capabilities = new FirefoxOptions();
	        }
	        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
	        String hubHost = Config.get(Constants.GRID_HUB_HOST);
	        String url = String.format(urlFormat, hubHost);
	        log.info("grid url: {}", url);
	        return new RemoteWebDriver(new URL(url), capabilities);
	    }
	
	private WebDriver getLocalDriver() {
		return new ChromeDriver();
	}
	
	@AfterTest
	public void tearDown() {
		this.driver.quit();
	}
	
}
