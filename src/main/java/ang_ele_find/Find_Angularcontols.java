package ang_ele_find;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class Find_Angularcontols {
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

	
	public void searchJustDial() throws InterruptedException {

		driver.get("https://www.justdial.com/Pune/search?q=Dietitians");
		/*
		 * driver.findElement(By.id("hotkeys_text_27")).click(); WebElement
		 * dietcian= driver.findElement(By.xpath(
		 * "//div[@class='wrapper filter-section']//li[1]//a[1]"));
		 * dietcian.click(); System.out.println("I am on dietcian page\n");
		 */
		/*
		 * WebElement ele=driver.findElement(By.xpath(
		 * "//section[@id='bcard0']//p[contains(@class,'contact-info')]"));
		 * System.out.println("The phone number: "+ele.getText());
		 */

		HashMap<String, String> map = new HashMap();

		String[] keys = { "mobilesv icon-yz", "mobilesv icon-wx", "mobilesv icon-vu", "mobilesv icon-ts",
				"mobilesv icon-rq", "mobilesv icon-po", "mobilesv icon-nm", "mobilesv icon-lk", "mobilesv icon-ji",
				"mobilesv icon-acb", "mobilesv icon-dc", "mobilesv icon-ba", "mobilesv icon-fe", "mobilesv icon-hg" };
		String[] values = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "-", "(", ")" };
		
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}

		map.forEach((key, value) -> System.out.println(key + " : " + value));

		List<String> x = new ArrayList<String>();

		List<WebElement> contactInfo = driver
				.findElementsByXPath("//i[@class='res_contactic resultimg']//following-sibling::span/a/b");

		List<WebElement> contactname = driver.findElementsByXPath("//span[@class='lng_cont_name']");

		for (WebElement webElement : contactInfo) {

			// System.out.println("New element");
			List<WebElement> spaninfo = webElement.findElements(By.xpath(".//span"));

			for (WebElement webElement2 : spaninfo) {

				// System.out.println(webElement2.getAttribute("class"));

				x.add(webElement2.getAttribute("class"));

			}

		}

		for (WebElement contacte : contactname) {

			System.out.println(contacte.getText());

			for (String element : x) {

				System.out.print(map.get(element));
			}

			System.out.println("\n");
		}

	}
	@Test
	public void logintoOTIS() throws InterruptedException {

		
		driver.get("https://otis.service.tieto.com/otis/");
		driver.manage().window().maximize();
	 
	driver.findElement(By.xpath("(//a[@class='btn btn-md btn-primary btn-block outline'])[1]")).click();
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys("stefan.johansson@tieto.com");;
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("anything");;

	
		Thread.sleep(3000);

	driver.findElement(By.xpath("//input[@name='captcha']")).click();
	driver.findElement(By.xpath("//button[@class='submit btn btn-md btn-primary btn-block']")).click();;
	Thread.sleep(3000);
	
	
	assertEquals(driver.getCurrentUrl(), "https://otis.service.tieto.com/otis/");
	
	System.out.println("Logged in to OTIS\n");
	Thread.sleep(3000);
	
	}

	
public void searchPatientfromHome() throws InterruptedException{
		
		WebElement serchpt=driver.findElement(By.xpath("//input[@placeholder='Ex personnr: 19900101-0101']"));
		serchpt.click();
		Thread.sleep(2000);
	WebElement	ssninlist =driver.findElement(By.xpath("//a[contains(text(),' 19121212-1212 Axelsson Theodor')]"));
			Actions action = new Actions(driver);
	action.moveToElement(ssninlist).click().build().perform();
	Thread.sleep(2000);
	
	String header=	driver.findElement(By.xpath("//h3[@translate='Create new treatment']")).getText();
		
			assertEquals(header, "Skapa ny behandling");
	
		
	}
	
	

		@Test
		public void searchPatient() throws InterruptedException{
			
		driver.findElement(By.xpath("//a[contains(text(),'Meny')]")).click();
		
		
		Thread.sleep(3000);
	

//new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='popover lc-user-tag-popover ng-tns-c1-0 ng-trigger ng-trigger-fullWidth ng-star-inserted show']")));

		//driver.findElement(By.xpath("//div[@class='popover lc-user-tag-popover ng-tns-c1-0 ng-trigger ng-trigger-fullWidth ng-star-inserted show']")).click();
		WebElement element = driver.findElement(By.xpath("//span[@translate='Search patient']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//span[@translate='Search patient']")).click();
		//Thread.sleep(2000);
		
		WebElement searchfield=driver.findElement(By.xpath("//div[@class='input-group mb-3']//input[@placeholder='Ex personnr: 19900101-0101']"));
		searchfield.sendKeys("196007213348");
		
		//driver.findElement(By.xpath("//div[@class='col-9 ng-tns-c1-0 ng-star-inserted show']")).click();
		//driver.findElement(By.xpath("//input[@name='search' and @placeholder='YYYYMMDDXXXX']")).sendKeys("191212121212");
	WebElement searchbutton=	driver.findElement(By.xpath("//button[@type=\'submit\']"));
	searchbutton.click();
		
		
	Thread.sleep(4000);
	
		
	String header=	driver.findElement(By.xpath("//h2[@translate='Create new treatment']")).getText();
		
			assertEquals(header, "Skapa ny behandling");
			//driver.quit();
		}
	
@Test
public void createCompleteOpreg() throws InterruptedException{


	
	
	//driver.findElement(By.xpath("//div[@class='lc-container-max-width lc-viewport']")).click();
	 WebDriverWait wait = new WebDriverWait(driver,30);
	
	
	          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@translate='New elective operation decision']/following-sibling::a[contains(text(),'Fullständig')]")));

	
	WebElement fullopreg =driver.findElement(By.xpath("//h2[@translate='New elective operation decision']/following-sibling::a[contains(text(),'Fullständig')]"));
	
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", fullopreg);
	
	
	Thread.sleep(4000);
	//Click on the link of admin info
	Actions act =new Actions(driver);
	WebElement admininfo=driver.findElement(By.xpath("//h2[contains(text(),'Administrativ info')]"));
	act.moveToElement(admininfo).click().build().perform();
	
	//driver.findElement(By.xpath("//h2[contains(text(),'Administrativ info')]")).click();
	Select registeredby=new Select(driver.findElement(By.xpath("//select[@formcontrolname='registeredByUserId']")));
	registeredby.selectByVisibleText("Shirsath Vishal");
	//-----------------
	
	Select priority=new Select(driver.findElement(By.xpath("//select[@formcontrolname='priorityId']")));
	priority.selectByVisibleText("Elektiv: Dubbel Förtur - 14 dagar");
	
	driver.quit();
     }
	

	
	
}

