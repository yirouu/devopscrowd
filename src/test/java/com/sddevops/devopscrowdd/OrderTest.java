
package com.sddevops.devopscrowdd;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class OrderTest { // declare Selenium WebDriver
	private WebDriver webDriver;

	@Test(priority = 1)

	public void checkTitle() { // Load web page as a new page
		webDriver.navigate().to("http://localhost:8090/devopscrowd/ProductViewServlet/dashboard");

		// Assert the title to check that we are indeed in the correct web page
		Assert.assertEquals(webDriver.getTitle(), "Crowds");

		System.out.println("title: " + webDriver.getTitle());

	}

	@Test(priority = 0)
	public void checklogin() {
		webDriver.navigate().to("http://localhost:8090/devopscrowd/login.jsp");
		WebElement email = webDriver.findElement(By.xpath("/html/body/form/h1[1]/input"));
		WebElement password = webDriver.findElement(By.xpath("/html/body/form/h1[2]/input"));

		email.sendKeys("bob@gmail.com");
		password.sendKeys("password");

		webDriver.findElement(By.xpath("/html/body/form/div/input")).click();

		System.out.println("logged in to account");
	}

	@Test(priority = 2)
	public void checkoutProduct() {
		// Checking the process of adding a product to cart & placing order
		// Load web page to the product listings
		webDriver.navigate().to("http://localhost:8090/devopscrowd/ProductViewServlet/dashboard");
		String we = webDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/h5")).getText();

		System.out.println("product name: " + we);

		// Retrieve button by using its id & click to the details page
		WebElement button = webDriver.findElement(By.xpath("F/html/body/div/div/div/div[1]/div/div/a"));
		button.click();

		// Assert the new title to check that the title is the same as the product
		// selected
		Assert.assertEquals(webDriver.getTitle(), we);
		System.out.println("product selected: " + webDriver.getTitle());

		webDriver.findElement(By.xpath("/html/body/div/div[2]/div/a")).click();

		webDriver.navigate().to("http://localhost:8090/devopscrowd/CartServlet/cart");
		webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[4]/form/div/a[2]")).click();
		System.out.println("increase button pressed");

		WebElement we1 = webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[4]/form/div/input"));
		System.out.println("quantity: " + we1.getAttribute("value"));

		webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[4]/form/div/a[1]")).click();
		System.out.println("decrease button pressed");
		WebElement we2 = webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[4]/form/div/input"));

		System.out.println("quantity: " + we2.getAttribute("value"));

		webDriver.findElement(By.xpath("/html/body/div/div/div/a")).click();

	}

	@Test(priority = 3)
	public void checkOrders() {
		webDriver.navigate().to("http://localhost:8090/devopscrowd/OrderServlet/dashboard");
		WebElement we = webDriver.findElement(By.xpath("/html/body/div/div/div/table/tbody/tr[1]/td[8]/div/span"));
		System.out.println(we.getText());

		webDriver.findElement(By.xpath("/html/body/div/div/div/table/tbody/tr[1]/td[9]/a")).click();
		System.out.println("edit order button clicked");

		WebElement edit = webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[2]/input"));
		String status = edit.getAttribute("value");

		if (status == "approved") {
			edit.clear();
			edit.sendKeys("pending");
			webDriver.findElement(By.xpath("/html/body/div/div/div/form/button")).click();
			System.out.println("edited order status to pending");
		} else {
			edit.clear();
			edit.sendKeys("approved");
			webDriver.findElement(By.xpath("/html/body/div/div/div/form/button")).click();
			System.out.println("edited order status to approved");
		}

		String test = webDriver.findElement(By.xpath("/html/body/div/div/div/table/tbody/tr[last()]/td[2]/p"))
				.getText();
		System.out.println("testing last:" + test);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateToday = formatter.format(date);

		System.out.println(dateToday);

		Assert.assertTrue(test.contains(dateToday));
		webDriver.findElement(By.xpath("/html/body/div/div/div/table/tbody/tr[last()]/td[10]/a")).click();
		System.out.println("deleted order from admin side");

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
	public void afterTest() { // Quit the ChromeDriver and close all associated window at the end of test
		webDriver.quit();
	}

}
