

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import necessary Selenium WebDriver classes
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.apache.commons.lang3.RandomStringUtils;

public class FeedbackTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	// declare variables used for Create and Edit
	String tcomment = RandomStringUtils.randomAlphabetic(200);
	String trating = RandomStringUtils.randomAlphabetic(20);
	String ecomment = RandomStringUtils.randomAlphabetic(200);
	String erating = RandomStringUtils.randomAlphabetic(20);


	// Load website as a new page
	@Test(priority = 0)
	public void checkWeb() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd/");

		// Assert the title to check that we are indeed in the correct website
		Assert.assertEquals(webDriver.getTitle(), "crowd");

		System.out.println("title: " + webDriver.getTitle());

	}

	// check feedback(create)
	@Test(priority = 1)
	public void checkCreateFeed() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd/feedCreate.jsp");

		Assert.assertEquals(webDriver.getTitle(), "Feedback");

		webDriver.findElement(By.xpath("/html/body/form/input[1]")).sendKeys(tcomment);
		webDriver.findElement(By.xpath("/html/body/form/select")).sendKeys(trating);


		webDriver.findElement(By.xpath("/html/body/form/input[2]")).submit();

		System.out.println("Feedback successful!");

	}



	// check feedback dashboard
	@Test(priority = 2)
	public void checkDashboard() {

		webDriver.navigate().to("http://localhost:8090/devopscrowd/FeedbackServlet/dashboard");

		// click on add new comment
		webDriver.findElement(By.xpath("/html/body/div/div/div/a")).click();
		Assert.assertEquals(webDriver.getTitle(), "Feedback");


	}

	
	
	// check edit feedback
		@Test(priority = 3)
		public void checkEditFeed() {
			
			webDriver.navigate().to("http://localhost:8090/devopscrowd/FeedbackServlet/dashboard");
			
			// click on edit button
			webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[5]/a[1]")).click();
			Assert.assertEquals(webDriver.getTitle(), "Edit Feedback");
			
			webDriver.navigate().to("http://localhost:8090/devopscrowd/feedEdit.jsp");

	// edit details
			webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[1]/input")).clear();
			webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[1]/input")).sendKeys(ecomment);
			
			webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[2]/input")).clear();
			webDriver.findElement(By.xpath("/html/body/div/div/div/form/fieldset[2]/input")).sendKeys(erating);

			webDriver.findElement(By.xpath("/html/body/div/div/div/form/button")).submit();

			System.out.println("Feedback Edited " + ecomment + erating);

		}
		
		// check delete feedback
		@Test(priority = 4)
		public void checkDeleteFeed() {

			webDriver.navigate().to("http://localhost:8090/devopscrowd/FeedbackServlet/dashboard");

			// click delete
			webDriver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[5]/a[2]")).click();

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

