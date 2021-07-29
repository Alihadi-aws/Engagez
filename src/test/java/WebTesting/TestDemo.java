/***
 * author: Ali Hadi
 * date: 29 August 2021
 * objective: Engagez take home coding assessment 
 */

package WebTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDemo {

	public WebDriver driver;
	public Properties properties;
	public FileInputStream Readfile;
	public FileOutputStream Writefile;

	@BeforeClass
	public void dateSetup() throws IOException, InterruptedException {
		// Properties to manipulate I/O
		properties = new Properties();
		Readfile = new FileInputStream(
				"Enter the file path on your system\\input.properties"); // IMPORTANT 
		properties.load(Readfile);

		// Get the browser from the Properties file (User specified)
		if (properties.getProperty("Browser").equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (properties.getProperty("Browser").equals("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (properties.getProperty("Browser").equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {
			System.out.println("Input.properties does not contian the browser values. Please fix it.");
			System.exit(0);
		}

		// Invoke the URL from the Properties file (User specified)
		driver.get(properties.getProperty("URL"));
		// Setup delay
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@BeforeClass
	public void login() throws InterruptedException {
		
		driver.findElement(By.id("expo2-dock-signin")).click(); // login click 
		driver.findElement(By.xpath("//input[@type='text' and @id='edit-name']")).sendKeys(properties.getProperty("username")); // relative xpath to enter username 	
		System.out.println("\nLogin is successful.");
		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(4000);
	}
	
	
	@AfterClass
	public void Terminate() {
		driver.close();
		driver.quit();

	}

	@Test(priority = 1, description = "Click on Sessions")
	public void testOne() throws InterruptedException {

		// Browse the Sessions section
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[2]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[1]/a")).click();  // back home 
		Thread.sleep(4000);		
	
	}

	@Test(priority = 2, description = "Click on Partner show case")
	public void testTwo() throws InterruptedException {

		// Browse the Partner show case  section
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[3]/a")).click();
		Thread.sleep(4000);		// 4 seconds pause
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[1]/a")).click();  // back home 
		Thread.sleep(4000);		// 4 seconds pause
	}
	
	@Test(priority = 3, description = "Click on Resources")
	public void testThree() throws InterruptedException {

		// Browse the Resources section
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[4]/a")).click();
		Thread.sleep(4000);		// 2 seconds pause
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[1]/a")).click();  // back home 
		Thread.sleep(4000);		// 2 seconds pause
	}
	
	@Test(priority = 4, description = "Click on Ask experts")
	public void testFour() throws InterruptedException {

		// Browse the Ask experts section
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[5]/a")).click();
		Thread.sleep(4000);		// 2 seconds pause
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[1]/a")).click();  // back home 
		Thread.sleep(4000);		// 2 seconds pause
	}
	
	@Test(priority = 5, description = "Click on General Sessions")
	public void testFive() throws InterruptedException {

		// Browse the General Sessions section
		driver.findElement(By.id("expo-content-custom-location-trigger")).click();
		Thread.sleep(4000);		// 2 seconds pause
		driver.findElement(By.xpath("//*[@id=\"expo2-header-location-menu\"]/ul/li[1]/a")).click();  // back home 
		Thread.sleep(4000);		// 2 seconds pause
	}

}
