package Locators;

import org.testng.annotations.*;

public class TestOne {
	
	
	@BeforeMethod
	public void a() {
		System.out.println("before method");
	}

	
	@Test
	public void b() {
		System.out.println("test 1");
	}
	@Test(dependsOnMethods="b")
	public void c() {
		System.out.println("test 2");
	}
	@Test(dependsOnMethods="c")
	public void d() {
		System.out.println("test 3");
	}
	
	@AfterMethod
	public void e() {
		System.out.println("after method");
	}
}
