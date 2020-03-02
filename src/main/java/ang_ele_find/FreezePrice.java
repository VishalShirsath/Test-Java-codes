package ang_ele_find;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.ws.WebFault;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FreezePrice {

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
	public void freezeList() throws InterruptedException {
		//Navigating to url
		driver.get("https://lnkd.in/fZQDZGx");
		Thread.sleep(3000);
		
		//Array for storing all elements titles from nextgenerationautomation site
		ArrayList<WebElement> frizename = new ArrayList<>();
		frizename = (ArrayList<WebElement>) driver.findElements(By.xpath("//span[@style='font-weight:bold;']//span[contains(text(),'.')]"));
		
		ArrayList<String> expected=null;
		ArrayList<String> actual = null;
		System.out.println("This is list from NG automation site: ");
		for (WebElement webElement : frizename) {

			 actual = new ArrayList<String>();
			  actual.add(webElement.getText().substring(3));
			System.out.println(actual);
		}
		
		//array to store all the buttons from nextgenerationautomation site
		ArrayList<WebElement> buyfrom = new ArrayList<>();
		buyfrom = (ArrayList<WebElement>) driver.findElements(By.xpath("//span[contains(text(),'Buy Now From Amazon India')]"));
		
		System.out.println("This is a list from Amazon site: ");

		for (WebElement webElement : buyfrom) {

			webElement.click();
			String parentWindow = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					driver.switchTo().window(windowHandle);
					ArrayList<WebElement> friname = new ArrayList<>();
					friname = (ArrayList<WebElement>) driver.findElements(By.id("productTitle"));

					//Array to store titles from Amazon 
					for (WebElement amfrizname : friname) {
						expected = new ArrayList<String>();
						expected.add(amfrizname.getText());
						System.out.println(expected);

					}
					driver.close(); // closing child window
					driver.switchTo().window(parentWindow); // cntrl to parent window
				}
				

			}
			
		}
		Assert.assertEquals(actual, expected);
	
	}

	@AfterClass
	public void closeDriver() {

		driver.close();
	}
}
