package Locators;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestLocators {
	WebDriver driver;

	
	@Test(enabled=true)
	public void test() {
		WebDriver driver;
		this.driver = new ChromeDriver();
		this.driver.navigate().to("https://google.co.in");
		this.driver.get("https://www.amazon.in/");
		
		this.driver.navigate().back();
		this.driver.manage().window().maximize();
		
		
		LinkedHashSet<String> handles = (LinkedHashSet<String>) this.driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while(it.hasNext()) {
		System.out.println("Via HASHSET------>"+	this.driver.switchTo().window(it.next()).getTitle());
		}
		String parentwindwID = this.driver.getWindowHandle(); System.out.println("PID----------->"+parentwindwID);
	}
	

	@Test(enabled=false)
	public void search2dArr() throws InterruptedException
	{	driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/Admin/Customer/List");
		driver.manage().window().maximize();
		
	System.out.println(	genericSearch("Indian Cricket Team"));
		
	}
	public boolean genericSearch(String passName) throws InterruptedException
	{
		driver.findElement(By.cssSelector(".buttons button")).click();
		driver.findElement(By.cssSelector(".nav-item.has-treeview:nth-of-type(4)")).click();
		driver.findElement(By.cssSelector(".mt-2 li:nth-of-type(4) li:nth-of-type(1) a")).click();
		Thread.sleep(4000);
		
		int rows = driver.findElements(By.cssSelector("#customers-grid tbody tr")).size();
		int col = driver.findElements(By.cssSelector("#customers-grid tbody tr:first-of-type td")).size();
		System.out.println(rows + "  " + col);
		boolean flag = false;
		
		for(int i= 1; i<rows; i++)
		{
			for(int j=1; j<=col; j++)
			{
				String name = driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td["+j+"]")).getText();
		//		System.out.println(i +"  " + j + " "+ name);
				if(name.contains(passName))
				{
					System.out.println(name + " row-->" +i + " col-->"+ j);
					flag = true;
					break;
				}
			}
		}
//		if(flag = true)
//		{
//			return true;
//		}
		return flag;
	}
	
	
	@Test(enabled=false)
	public void svg() throws InterruptedException {
		
		driver= new ChromeDriver();
		driver.get("https://www.google.co.in/");
		driver.manage().window().maximize();
		driver.findElement(By.name("q")).sendKeys("India population" , Keys.chord(Keys.ENTER));
		
//		int y = ((svg.getSize().getHeight())/2) - svg.getSize().getHeight();
			//	int x = ((svg.getSize().getWidth())/2) - svg.getSize().getWidth();
		
	List <WebElement> svg = driver.findElements(By.xpath("//*[name()='svg']//*[local-name()='g' and @class='xTick']//*[@class='label']"));
	Actions a = new Actions(driver);
		
	String	country=null;
	for(WebElement e: svg) {
			a.moveToElement(e).perform();
			
			country = driver.findElement(By.xpath("//span[text()='India' and @class='AleqXe']")).getText();
			String	data = driver.findElement(By.xpath("(//*[@class='kpd-lv'])[2]")).getText();
			
			
			System.out.println("data--> " +data);
			
			
			String coord = driver.findElement(By.xpath("//*[name()='svg']//*[local-name()='line' and contains(@class,'highlight-line')]")).getAttribute("x1");
			System.out.println("x1--> " +coord);
	}
		System.out.println("country--->" + country);
		
	}

	
	
	@Test(enabled = false)
	public void addToCartlocs() throws InterruptedException, IOException
	{	
		driver= new ChromeDriver();
		String prodName = "IPHONE 13 PRO";
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("kashi@kashi.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shiva07#");
		driver.findElement(By.id("login")).click();
		Thread.sleep(1000);
		
		List <WebElement> list = driver.findElements(By.cssSelector(".mb-3 .card-body"));
		Thread.sleep(2000);
		
			long stime = System.currentTimeMillis();		
		WebElement item=list.stream().filter(e->e.getText().contains(prodName)).findFirst().orElse(null);
		Thread.sleep(1000);
		JavascriptExecutor js =((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(1000);

	System.out.println(	item.findElement(By.cssSelector("button:nth-of-type(2)")).getText());
	item.findElement(By.cssSelector("button:nth-of-type(2)")).click();
		
		long etime = System.currentTimeMillis();
		System.out.println(etime-stime);	
		
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(9));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		
		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("ul li:nth-of-type(4) button")).click();
		
		
		List<WebElement> itemName = driver.findElements(By.cssSelector("div.cart ul h3"));
		boolean ans = itemName.stream().anyMatch(e->e.getText().contains(prodName));
		if(ans==true) {
			driver.findElement(By.cssSelector(".subtotal  ul li .btn-primary")).click();
		}
		Thread.sleep(2000);
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").perform();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[text()=' India']")).click();
		driver.findElement(By.cssSelector("a.action__submit")).click();
	}
	
	
	@Test(enabled = false)
	public void searchDropDown() throws InterruptedException
	{	
		driver = new ChromeDriver();
	
		driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");
		
		driver.manage().window().maximize();
		driver.findElement(By.id("justAnInputBox")).click();

		genericMultipleNestedCheckbox(driver,"choice 2 1", "choice 2 2", "choice 2 3", "choice 6", "choice 6 2 1", "choice 3");
		Thread.sleep(3000);
	}	
	
	public static void genericMultipleNestedCheckbox(WebDriver driver, String...value) {
		List<WebElement> options = driver.findElements(By.cssSelector(".comboTreeItemTitle "));		
		
		if(value[0].equalsIgnoreCase("all")) {
			for(WebElement e:options) {
				e.click();
			}
		}
		else if(!value[0].equalsIgnoreCase("all")) {
			for(WebElement e:options) {
				String t = e.getText();
				System.out.println(t);
				
				for(String s: value) {
					if(s.equals(t)) {
						e.click();
						//break;
					}
				}
			}	
		}
		
	}
	
	@Test(enabled=false)
	public void calendarSImplECode() throws InterruptedException {
		String month = "December";
		String date = "29";
		String year = "2027";
		String monthInNum = "12";
		
		String expected [] = {monthInNum,date,year};
		
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers"); driver.manage().window().maximize();
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("div.react-date-picker__inputGroup")).click();  Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("button.react-calendar__navigation__label")).click();  	Thread.sleep(1000);
	
		driver.findElement(By.cssSelector("button.react-calendar__navigation__label")).click();  Thread.sleep(1000);	
		
		driver.findElement(By.xpath("//button[text()='"+year+"']")).click();  Thread.sleep(1000);
		
	//	driver.findElement(By.xpath("//button/abbr[text()='"+month+"']")).click();  Thread.sleep(1000);
		
		driver.findElements(By.cssSelector("button[class*='months__month'] abbr")).get(Integer.parseInt(monthInNum)-1).click();
		
		driver.findElement(By.xpath("//button[@class='react-calendar__tile react-calendar__month-view__days__day']/abbr[text()='"+date+"']")).click();
		
		List<WebElement> val =driver.findElements(By.cssSelector("input.react-date-picker__inputGroup__input"));
		String ans;
		for(int i=0;i<val.size();i++) {
		 ans = val.get(i).getAttribute("value");
		 Assert.assertEquals(ans, expected[i]);
			System.out.println(ans);
		}
		
	}
	
	
	@Test(enabled=false)
	public void calendar() throws InterruptedException {
		String month = "December";
		String date = "29";
		String year = "2027";
		
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers"); driver.manage().window().maximize();
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("div.react-date-picker__inputGroup")).click();
		Thread.sleep(1000);
		
		String text = driver.findElement(By.cssSelector("button.react-calendar__navigation__label span")).getText();
		System.out.println(text);
		Thread.sleep(1000);
			
			driver.findElement(By.cssSelector("button[class*='react-calendar__navigation__label']")).click();
			Thread.sleep(1000);
		
		String currentYear=driver.findElement(By.cssSelector("button.react-calendar__navigation__label span")).getText().trim();
		System.out.println(currentYear);
		
		while(!driver.findElement(By.cssSelector("button.react-calendar__navigation__label span")).getText().trim().equals(year)) {
			
			driver.findElement(By.cssSelector("button.react-calendar__navigation__next-button")).click();
		}
			
		/// simple way to select year without using any loop via xpath
//		driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
		
		Thread.sleep(1000);
		
		List<WebElement> monthList=driver.findElements(By.cssSelector("button[class*='months__month'] abbr"));
		for(int i=0;i<monthList.size();i++) {
			if(monthList.get(i).getText().equalsIgnoreCase(month)) {
				driver.findElements(By.cssSelector("button[class*='view__months__month']")).get(i).click();
			}
		}
		Thread.sleep(1000);
		
		List<WebElement> dateList=driver.findElements(By.cssSelector("button[class='react-calendar__tile react-calendar__month-view__days__day'] abbr"));
		for(int i=0;i<dateList.size();i++) {
			if(dateList.get(i).getText().contains(date)) {
				driver.findElements(By.cssSelector("button[class='react-calendar__tile react-calendar__month-view__days__day'] abbr")).get(i).click();
			}
		}
		
		
	}
	
	
	@Test(enabled=false)
	public void links() throws InterruptedException
	{
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		
		List<WebElement> footers=driver.findElements(By.cssSelector("div.gffoot a"));
		List<String> urls = new ArrayList<String>();
		
		for(int i=0;i<footers.size();i++) {
			String url = footers.get(i).getAttribute("href");
			urls.add(url);
		}
			urls.parallelStream().forEach(e->
			{
				try {
					checkBrokenURLs(e);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
					
		});
	}	
	

	public static void checkBrokenURLs(String Url) throws IOException {
		
		URL url = new URL(Url);
		HttpURLConnection conn = 	(HttpURLConnection)url.openConnection();
		conn.connect();
		if(conn.getResponseCode()>=400) {
			System.out.println(Url +" --->" + conn.getResponseMessage()+" -->"+ conn.getResponseCode());
		}
		
	}
	
	
	public void wait(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	@AfterTest(enabled=true)
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	//	driver.quit();
	}
	
}
