package ang_ele_find;

import static org.testng.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

		public static void main(String[] args) throws InterruptedException {
		     //Instantiating FirefoxDriver
			System.setProperty("webdriver.chrome.driver", "C:\\AUTOMATION_WORKSPACE\\Selenium\\Drivers\\chromedriver.exe");

		     WebDriver driver = new ChromeDriver();

		    //Maximize the browser
		    driver.manage().window().maximize();

		    //Implicit wait for 10 seconds
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		    //To launch nextgenerationautomation.com  
		    driver.get("https://www.amazon.in/?ie=UTF8&tag=googinabkvernac-21&ascsubtag=_k_EAIaIQobChMIzsunqeSV5AIVgiQrCh3C6QVKEAAYASAAEgJ4e_D_BwE_k_&ext_vrnc=hi&gclid=EAIaIQobChMIzsunqeSV5AIVgiQrCh3C6QVKEAAYASAAEgJ4e_D_BwE");
			driver.manage().window().maximize();
		 
		/*driver.findElement(By.xpath("(//a[@class='btn btn-md btn-primary btn-block outline'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("stefan.johansson@tieto.com");;
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("anything");;

		
			Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@name='captcha']")).click();
		driver.findElement(By.xpath("//button[@class='submit btn btn-md btn-primary btn-block']")).click();;
		Thread.sleep(3000);
				
	System.out.println("Logged in to OTIS\n");
		Thread.sleep(3000);*/
				
		    //Wait for 5 seconds
		    Thread.sleep(5000);

		    //Used tagName method to collect the list of items with tagName "a"
		    //findElements - to find all the elements with in the current page. It returns a list of all  //webelements or an empty list if nothing matches
		    List<WebElement> links = driver.findElements(By.tagName("a"));	
				
		    //To print the total number of links
		    System.out.println("Total links are "+links.size());	

		    //used for loop to iterate over all links
		    for(int i=0; i<links.size(); i++) {
		      WebElement element = links.get(i);
		      //By using "href" attribute, we could get the url of the requried link
		      String url=element.getAttribute("href");

		      //calling verifyLink() method here. Passing the parameter as url which we collected in the above link See the detailed functionality of the verifyLink(url) method below
		      verifyLink(url);			
			  }	
		}
			
		// The below function verifyLink(String urlLink) verifies any broken links and return the server //status. 
		  public static void verifyLink(String urlLink) {
		      //Sometimes we may face exception "java.net.MalformedURLException". Keep the code in //try catch block to continue the broken link analysis
		      try {
		        //Use URL Class - Create object of the URL Class and pass the urlLink as parameter 
		        java.net.URL link = new java.net.URL(urlLink);
		        
		        
			//System.out.println("Accessed link is: "+link);
		        // Create pa connection using URL object (i.e., link)
		        HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();

		        //Set the timeout for 2 seconds
		        httpConn.setConnectTimeout(2000);

		        //connect using connect method
		        httpConn.connect();

		        //use getResponseCode() to get the response code. 
		        if(httpConn.getResponseCode()== 200)
		          System.out.println(urlLink+" - "+httpConn.getResponseMessage());

		        if(httpConn.getResponseCode()== 404)
		        System.out.println(urlLink+" - "+httpConn.getResponseMessage());
		        
		        if(httpConn.getResponseCode()== 401)
			        System.out.println(urlLink+" - "+httpConn.getResponseMessage());
		        
		        if(httpConn.getResponseCode()== 400)
			        System.out.println(urlLink+" - "+httpConn.getResponseMessage());
		        if(httpConn.getResponseCode()== 500)
			        System.out.println(urlLink+" - "+httpConn.getResponseMessage());
		        if(httpConn.getResponseCode()== 504)
			        System.out.println(urlLink+" - "+httpConn.getResponseMessage());
		    }
		    //getResponseCode method returns = IOException - if an error occurred connecting to the //server. 
		    catch (Exception e) {
			    e.printStackTrace();
		    }
		  } 
		
	}


