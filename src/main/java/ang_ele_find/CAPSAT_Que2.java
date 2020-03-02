package ang_ele_find;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CAPSAT_Que2 {
	WebDriver driver;

	@BeforeClass
	public void prepareDriver() {

		// Create a instance of ChromeOptions class
		ChromeOptions options = new ChromeOptions();

		// Add chrome switch to disable notification -
		// "**--disable-notifications**"
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "C:\\AUTOMATION_WORKSPACE\\Selenium\\Drivers\\chromedriver.exe");
		//Manage capabilities for driver.
		driver = new ChromeDriver(options);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("nativeEvents", true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		capabilities.setCapability(InternetExplorerDriver.LOG_LEVEL, "DEBUG");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void gotoWikiAndConfirmItems(){
		driver.get("http://www.wikipedia.org");
		WebElement englinks= driver.findElement(By.xpath("//a[@id='js-link-box-en']//bdi"));
		System.out.println("The number of articles in English:" + englinks.getText());
		englinks.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		WebElement searchwiki =driver.findElement(By.xpath("//input[@placeholder='Search Wikipedia']"));
		searchwiki.sendKeys("Anna University");
		driver.findElement(By.id("searchButton")).click();
		String text= driver.findElement(By.xpath("//table[@class='infobox vcard' and caption]//tr[3]//td")).getText();
		
		if (text.contains("Knowledge")){
			
			System.out.println("Text is present");
		}
		else{
			
			System.out.println("Text is not present.");
		}
		assertTrue(driver.findElement(By.xpath("//a[@title='A. P. J. Abdul Kalam']")).getText().contains("A. P. J. Abdul Kalam"));
		System.out.println("APJ found in  page source");
		
		
		
	}
	
	@AfterClass
	public void closeBrowser(){
		
		driver.close();
	}
}
