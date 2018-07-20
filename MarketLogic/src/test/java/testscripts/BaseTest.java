package testscripts;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import generic.ConstantsData;

public class BaseTest {
	
		public WebDriver driver;
				
		/**
		 * Defining BeforeMethod  contents
		 * 
		 * It will open the respective browser and navigate to the defined url
		 * Parameter("browser") is configurable from testng.xml file
		 * 
		 * @author Dhirajkumar.pandey
		 */
				
		
		@Parameters("browser")
		@BeforeClass
		public void fnpreCondition(@Optional String browserobj)
		{
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

			
	        //Launching the Chrome
			if(browserobj.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");
				driver = new ChromeDriver(chromeOptions);
			
			}
			
			//Launching the IE
			else
				if(browserobj.equals("InternetExplorer"))
				{
					
					System.setProperty("webdriver.ie.driver", ".\\src\\test\\resources\\IEDriverServer.exe");
					driver= new InternetExplorerDriver();
					
				}
			
			// Launching the Firefox
				else
				{
					System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\geckodriver.exe");
					driver = new FirefoxDriver();
			    }
			
		        
			driver.get(ConstantsData.URL);

			driver.manage().window().maximize();
				
		}
		
		
		/**
		 * Defining AfterClass contents
		 *  
		 * It will close the driver
		 * 
		 * @author Dhirajkumar.pandey
		 * @throws InterruptedException 
		 */
		
		@AfterClass
		public void fnpostCondition() throws InterruptedException
		{
			driver.close();
		}
	
	}
