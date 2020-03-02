package ang_ele_find;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.ExecuteScript;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApplyonMinistryofTesting {
	
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
		public void applyForJobs(){
		driver.get("https://www.ministryoftesting.com/jobs");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<WebElement> joblinks= new ArrayList<>();
		joblinks= (ArrayList<WebElement>) driver.findElements(By.xpath("//div[@class='card job']/a"));
	
		for (WebElement link : joblinks) {
		
			System.out.println(link.getText());
		
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			js.executeScript("arguments[0].click();",link);
			
		WebElement applynowbtn =driver.findElement(By.id("action_btn"));
		applynowbtn.click();
	
		WebElement username= driver.findElement(By.xpath("//input[@id='job_application_name']"));
		username.sendKeys("Vishal Shirsath");
		WebElement email= driver.findElement(By.xpath("//input[@id='job_application_email']"));
		email.sendKeys("vishalshirsath.1111@gmail.com");
		WebElement message= driver.findElement(By.xpath("//textarea[@id='job_application_message']"));
		message.sendKeys("I am interested in applying for open position in your organization.");
		WebElement browse= driver.findElement(By.xpath("//input[@id='job_application_file']"));
		browse.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			Runtime.getRuntime().exec("C:\\Users\\shirsvis\\Desktop\\Test_upload\\Fileupload.exe");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File upload not done!!");
		}
		
		//driver.navigate().back();
		//driver.navigate().back();
		
		
		
		}
			
		}
		
		@AfterClass
		
		public void CloseDriver(){
			
			driver.close();
			
		}
}
