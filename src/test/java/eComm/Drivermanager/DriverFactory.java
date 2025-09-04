package eComm.Drivermanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	
	public static ThreadLocal<WebDriver> Thrdlocaldriver = new ThreadLocal<>();
	
	public WebDriver init_driver(String browser, String DriverAutodownloadstatus) {
		
		System.out.println("Browser = " + browser);
		
		if(browser.equalsIgnoreCase("edge")) {
			if(DriverAutodownloadstatus.equals("YES")) {
				WebDriverManager.edgedriver().setup();
				Thrdlocaldriver.set(new EdgeDriver());
			}
			else {
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/Drivers/msedgedriver.exe");
				Thrdlocaldriver.set(new EdgeDriver());	
			}
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			if(DriverAutodownloadstatus.equals("YES")) {
				WebDriverManager.firefoxdriver().setup();
				Thrdlocaldriver.set(new FirefoxDriver());
			}
			else {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"/Drivers/geckodriver.exe");
				Thrdlocaldriver.set(new FirefoxDriver());
			}
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			if(DriverAutodownloadstatus.equals("YES")) {
				WebDriverManager.chromedriver().setup();
				Thrdlocaldriver.set(new ChromeDriver());
			}
			else {
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
				Thrdlocaldriver.set(new ChromeDriver());
			}
		}
		getdriver().manage().deleteAllCookies();
		getdriver().manage().window().maximize();
		getdriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return getdriver();	
	}
	
	public static synchronized WebDriver getdriver() {
		return Thrdlocaldriver.get();
	}
}
