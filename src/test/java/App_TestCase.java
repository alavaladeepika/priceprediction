import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App_TestCase {
	private static ChromeDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*@Test
	public void testWebsite() throws InterruptedException{
	    System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	    driver.get("http://localhost:8081/priceprediction/index.html");
	    System.out.println("----------"+driver.getTitle()+"----------");
	    Assert.assertEquals("Price Prediction", driver.getTitle());
	    System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}*/
	
	@Test
	public void testPrediction() throws InterruptedException {
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.get("http://localhost:8081/priceprediction/index.html");
		
		driver.findElement(By.id("pickup_latitude")).sendKeys("40.12");
	    driver.findElement(By.id("pickup_longitude")).sendKeys("-73.456");
	    driver.findElement(By.id("drop_latitude")).sendKeys("42.134");
	    driver.findElement(By.id("drop_longitude")).sendKeys("-74.5678");
	    driver.findElement(By.id("datepicker")).sendKeys("12/10/2016");
	    driver.findElement(By.id("input-a")).sendKeys("23:00");
	    driver.findElement(By.id("pass_count")).sendKeys("2");

	    WebElement element = driver.findElement(By.id("predict"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
	    
	    System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}
}
