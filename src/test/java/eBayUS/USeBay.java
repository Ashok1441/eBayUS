package eBayUS;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class USeBay {
	
	
	public static WebDriver driver;
	
	
	@BeforeClass
	@Parameters({"browserName"})
	public void openBrowser(String browserName) throws IOException {
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
			
	}
	
	@Test(priority = 1)
	@Parameters({"url"})
	public void eBayUS(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//verifying homepage
		String homePageActualTitle = driver.getTitle();
		String homePageExpectedTitle="Electronics, Cars, Fashion, Collectibles & More | eBay";
		Assert.assertEquals(homePageActualTitle, homePageExpectedTitle,"ActualTitle and ExpectedTitle both are not Same");
		
		//clicking sign option
		driver.findElement(By.xpath("(//a[text()='Sign in'])[1]")).click();
		driver.findElement(By.id("create-account-link")).click();
		
		//explicitly Wait for locating the element
		By ele = By.id("ifhItem0");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated( ele));
		
		//clicking FAQs option
		driver.findElement(By.id("ifhItem0")).click();
	}
	
	@Test(priority = 1)
	public void verification() {
		
		String actualText = driver.findElement(By.id("ifhFaqControlOC2766")).getText();
		String exceptedText="Do I need a business account?";
		Assert.assertEquals(actualText, exceptedText, "Actual and Excepted text are not Matched");
		
		driver.findElement(By.xpath("//li[@id=\"ifhFaqItemOC2766\"]//b")).click();
		String actualText1 =driver.findElement(By.xpath("//div[@id='ifhFaqContentOC2766']//div//li[3]")).getText();
		String exceptedText1="Buy items for your business";
		Assert.assertEquals(actualText1, exceptedText1);	
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
