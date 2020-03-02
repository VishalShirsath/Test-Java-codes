package ang_ele_find;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Automation_Thermo {
	
 ChromeDriver driver; 
	
	@BeforeClass
	public void prepareDriver() {

		// Create a instance of ChromeOptions class
		ChromeOptions options = new ChromeOptions();

		// Add chrome switch to disable notification -
		// "**--disable-notifications**"
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "C:\\AUTOMATION_WORKSPACE\\Selenium\\Drivers\\chromedriver.exe");

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


	public void HandlingGridElements (){
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		WebElement table= driver.findElement(By.id("customers"));
		
		List<WebElement> rows= table.findElements(By.tagName("tr"));
		
		System.out.println(rows.size());
		
		int i=0;

		for(WebElement eachrow:rows)
		{
		
			List<WebElement> columns= eachrow.findElements(By.tagName("td"));
			i++;
			
			System.out.print("Number of columns in "+ i + "th row is : "+columns.size());
			
			}
			
		System.out.println();
			
			
		}
	
	
	
	@Test
	public void TakeScreenshot() throws IOException{
		
		driver.get("https://www.thermofisher.com/in/en/home.html");
		
		TakesScreenshot tkescr=((TakesScreenshot)driver);
		
		File scrfile= tkescr.getScreenshotAs(OutputType.FILE);
		
		File DestFile=new File("C:\\Users\\shirsvis\\Desktop\\SCreenshot\\test.png");
		
		FileUtils.moveFile(scrfile, DestFile);
		
		
	}
	
	@AfterClass
	public void OnClose (){
		driver.close();
	}
	
	}
	
	

