package com.sddevops.devopscrowdd;

import org.openqa.selenium.By;
//import necessary Selenium WebDriver classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.apache.commons.lang3.RandomStringUtils;

public class userTest {
	// declare Selenium WebDriver
	private RemoteWebDriver webDriver;

	// declare variables used for later
	String tname = RandomStringUtils.randomAlphabetic(10);
	String temail = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
	String eemail = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
	String tpassword = RandomStringUtils.randomAlphabetic(10);
	String epassword = RandomStringUtils.randomAlphabetic(10);
	String taddress = RandomStringUtils.randomAlphabetic(10);
	String tpostal = RandomStringUtils.randomNumeric(6);

	// Load website as a new page
	@Test(priority = 0)
	public void checkWeb() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/");

		// Assert the title to check that we are indeed in the correct website
		Assert.assertEquals(webDriver.getTitle(), "crowd");

		System.out.println("title: " + webDriver.getTitle());

	}

	// check register
	@Test(priority = 1)
	public void checkReg() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/register.jsp");

		Assert.assertEquals(webDriver.getTitle(), "Register");

		webDriver.findElement(By.xpath("//input[@name=\"yourName\"]")).sendKeys(tname);
		webDriver.findElement(By.xpath("//input[@name=\"yourEmail\"]")).sendKeys(temail);
		webDriver.findElement(By.xpath("//input[@name=\"yourPassword\"]")).sendKeys(tpassword);
		webDriver.findElement(By.xpath("//input[@name=\"yourAddress\"]")).sendKeys(taddress);
		webDriver.findElement(By.xpath("//input[@name=\"yourPostal\"]")).sendKeys(tpostal);

		webDriver.findElement(By.xpath("//input[@value=\"Register\"]")).submit();

		System.out.println("Account Registered " + temail + tpassword);

	}

	// check login
	@Test(priority = 2)
	public void checkLogin() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/login.jsp");

		Assert.assertEquals(webDriver.getTitle(), "Login");

		webDriver.findElement(By.xpath("//input[@name=\"yourEmail\"]")).sendKeys(temail);
		webDriver.findElement(By.xpath("//input[@name=\"yourPassword\"]")).sendKeys(tpassword);

		webDriver.findElement(By.xpath("//input[@value=\"Login\"]")).submit();

		System.out.println("Logged In " + temail + tpassword);

	}

	// check edit acc
	@Test(priority = 3)
	public void checkEditProfile() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/ProductViewServlet/dashboard");

		// click profile on navbar
		webDriver.findElement(By.xpath("//ul[2]/li[2]/a")).click();
		Assert.assertEquals(webDriver.getTitle(), "User Profile");

		// click edit button
		webDriver.findElement(By.xpath("//td/a[1]")).click();
		Assert.assertEquals(webDriver.getTitle(), "Edit Profile");

		// edit details
		webDriver.findElement(By.xpath("//input[@name=\"username\"]")).clear();
		webDriver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testing");

		webDriver.findElement(By.xpath("//input[@name=\"email\"]")).clear();
		webDriver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(eemail);

		webDriver.findElement(By.xpath("//input[@name=\"password\"]")).clear();
		webDriver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(epassword);

		webDriver.findElement(By.xpath("//input[@name=\"address\"]")).clear();
		webDriver.findElement(By.xpath("//input[@name=\"address\"]")).sendKeys("testing");

		webDriver.findElement(By.xpath("//input[@name=\"postal\"]")).clear();
		webDriver.findElement(By.xpath("//input[@name=\"postal\"]")).sendKeys("123456");

		webDriver.findElement(By.xpath("//button[@type=\"submit\"]")).submit();

		System.out.println("Account Edited " + eemail + epassword);

	}

	// check logout
	@Test(priority = 4)
	public void checkLogout() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/ProductViewServlet/dashboard");

		// click logout button on navbar
		webDriver.findElement(By.xpath("//li[3]/a")).click();

		System.out.println("Logged Out");

	}

	// login again to check if the email and password are updated
	@Test(priority = 5)
	public void checkNewLogin() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/login.jsp");

		Assert.assertEquals(webDriver.getTitle(), "Login");

		webDriver.findElement(By.xpath("//input[@name=\"yourEmail\"]")).sendKeys(eemail);
		webDriver.findElement(By.xpath("//input[@name=\"yourPassword\"]")).sendKeys(epassword);

		webDriver.findElement(By.xpath("//input[@value=\"Login\"]")).submit();

		System.out.println("Logged In" + eemail + epassword);

	}

	// check delete acc
	@Test(priority = 6)
	public void checkDeleteAcc() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/ProductViewServlet/dashboard");

		// click profile on navbar
		webDriver.findElement(By.xpath("//ul[2]/li[2]/a")).click();
		Assert.assertEquals(webDriver.getTitle(), "User Profile");

		// click delete
		webDriver.findElement(By.xpath("//td/a[2]")).click();

	}

	// check login for admin
	@Test(priority = 7)
	public void checkLoginAdmin() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/login.jsp");

		webDriver.findElement(By.xpath("//input[@name=\"yourEmail\"]")).sendKeys("admin@gmail.com");
		webDriver.findElement(By.xpath("//input[@name=\"yourPassword\"]")).sendKeys("admin");

		webDriver.findElement(By.xpath("//input[@value=\"Login\"]")).submit();

		System.out.println("Admin Logged In");

	}

	// check logout for admin
	@Test(priority = 8)
	public void checkLogoutAdmin() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/admin.jsp");

		// click logout button on navbar
		webDriver.findElement(By.xpath("//li[3]/a")).click();

		System.out.println("Admin Logged Out");

	}

	// Check Redirect On jsp page and navbar from register to login/ login to
	// register
	@Test(priority = 9)
	public void checkRedirect() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/login.jsp");
		webDriver.findElement(By.xpath("//h1/a")).click();
		System.out.println("Redirected to Register");

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/register.jsp");
		webDriver.findElement(By.xpath("//h1/a")).click();
		System.out.println("Redirected to Login");

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/login.jsp");
		webDriver.findElement(By.xpath("//ul[2]/li/a")).click();
		System.out.println("Redirected to Register using navbar");

		webDriver.navigate().to("http://localhost:8090/devopscrowd-web-project/register.jsp");
		webDriver.findElement(By.xpath("//ul[2]/li[2]/a")).click();
		System.out.println("Redirected to Login using navbar");

	}

	@BeforeTest
	public void beforeTest() {
		// Setting system properties of ChromeDriver
		// to amend directory path base on your local file path
		String chromeDriverDir = "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", chromeDriverDir);

		// initialize FirefoxDriver at the start of test
		webDriver = new ChromeDriver();

	}

	@AfterTest
	public void afterTest() {
		// Quit the ChromeDriver and close all associated window at the end of test
		webDriver.quit();

	}

}
