package eBayUS;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AUSelleBayPage {
	

	public static WebDriver driver;
	String browserName="firefox";
	@Test
	public void eBay( ) throws IOException {
			if(browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(browserName.equals("edge")) {
				WebDriverManager.edgedriver().setup();
			    driver= new EdgeDriver();
			}
			else if(browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();	
			}
			else {
				Reporter.log("Given Browser name is Mismatched", true);
			}
			
			driver.get("https://www.ebay.com.au/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			String acHomeTitle=driver.getTitle();
			String exHomeTitle="Electronics, Cars, Fashion, Collectibles & More | eBay";
			Assert.assertEquals(acHomeTitle, exHomeTitle, "Title is Mismatched");
			Reporter.log("Australia eBay Home page is Displayed", true);
			
			driver.findElement(By.id("gh-p-2")).click();
			
			String acSellPageTitle=driver.getTitle();
			String exSellPageTitle="Selling on eBay | Electronics, Fashion, Home & Garden | eBay";
			Assert.assertEquals(acSellPageTitle, exSellPageTitle, "Title is Mismatched");
			Reporter.log("Australia eBay Selling page is Displayed", true);
			
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i-srvy")));
			driver.findElement(By.id("i-srvy")).click();
			
			driver.findElement(By.id("_ch09")).click();
			
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File des = new File("D:\\Selenium\\eBayUs\\Screenshot\\Australia SellingPage.png");
			Files.copy(src,des);
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("document.querySelector(\"#srvydeskolyin\").scrollBy(0,document.body.scrollHeight)");
			
		
			File src1 = ts.getScreenshotAs(OutputType.FILE);
			File des1 = new File("D:\\Selenium\\eBayUs\\Screenshot\\Australia AfterScrolling.png");
			Files.copy(src1,des1);
			
			driver.findElement(By.id("sbtsurvey")).click();
			driver.close();
			
	}

}
