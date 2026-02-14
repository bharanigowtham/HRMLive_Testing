package eComm.Drivermanager;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import eComm.Utilities.Configpropertiesreader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	private Configpropertiesreader config;
	Properties prop;
	public static ThreadLocal<WebDriver> Thrdlocaldriver = new ThreadLocal<>();

	public WebDriver init_driver(String browser, String DriverAutodownloadstatus) throws Exception {

		System.out.println("Browser = " + browser);

		String headlessstatus = System.getProperty("headless");

		if (headlessstatus == null) {
			config = new Configpropertiesreader();
			prop = config.init_properties();
			headlessstatus = prop.getProperty("headless");
		}

		boolean isHeadless = Boolean.parseBoolean(headlessstatus);
		System.out.println("Headless status = " + isHeadless);
		switch (browser.toLowerCase()) {
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			if (isHeadless) {
				edgeOptions.addArguments("--headless=new");
				edgeOptions.addArguments("--window-size=1920,1080");
			}
			if (DriverAutodownloadstatus.equals("YES")) {
				WebDriverManager.edgedriver().setup();
				Thrdlocaldriver.set(new EdgeDriver(edgeOptions));
			} else {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "/Drivers/msedgedriver.exe");
				Thrdlocaldriver.set(new EdgeDriver(edgeOptions));
			}
			break;

		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			if (isHeadless) {
				chromeOptions.addArguments("--headless=new");
				chromeOptions.addArguments("--window-size=1920,1080");
			}
			if (DriverAutodownloadstatus.equals("YES")) {
				WebDriverManager.chromedriver().setup();
				Thrdlocaldriver.set(new ChromeDriver(chromeOptions));
			} else {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
				Thrdlocaldriver.set(new ChromeDriver(chromeOptions));
			}
			break;

		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (isHeadless) {
				firefoxOptions.addArguments("--headless=new");
				firefoxOptions.addArguments("--window-size=1920,1080");
			}

			if (DriverAutodownloadstatus.equals("YES")) {
				WebDriverManager.firefoxdriver().setup();
				Thrdlocaldriver.set(new FirefoxDriver(firefoxOptions));
			} else {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
				Thrdlocaldriver.set(new FirefoxDriver(firefoxOptions));
			}

		}

		getdriver().manage().deleteAllCookies();
		getdriver().manage().window().maximize();
		getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return getdriver();
	}

	public static synchronized WebDriver getdriver() {
		return Thrdlocaldriver.get();
	}
}
