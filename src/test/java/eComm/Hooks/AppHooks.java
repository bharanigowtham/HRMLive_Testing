package eComm.Hooks;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.reporter.FileUtil;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Helpers;
import eComm.Utilities.Configpropertiesreader;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class AppHooks {
	
	private DriverFactory driverfactory;
	private Configpropertiesreader config;
	private WebDriver driver;
	Properties prop;
	
	@Before(order=1)
	public void getpropertyvalues() throws Exception {
		config = new Configpropertiesreader();
		prop = config.init_properties();
	}
	
	@Before(order=2)
	public void launchbrowser() {
		String browservalue = prop.getProperty("browser");
		String driverautodownloadsts = prop.getProperty("driverautodownload");
		driverfactory = new DriverFactory();
		driver = driverfactory.init_driver(browservalue, driverautodownloadsts);
		
	}

//	@BeforeStep
//	public void exectionstarted() {
//		System.out.println("Step started");
//	}
//	
	@AfterStep
	public void capturescreenshot(Scenario scenario) throws Exception {
		//To save in folder...
//		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
//        Date date = new Date();
//		String stepname = scenario.getName();
//		String path = ".Screenshots\\" + stepname 
//				       + "."+ dateFormat.format(date) +".png" ;
//		File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(screenshots, new File(path));
		
		//To attach in report...
		String screenshotName = scenario.getName().replaceAll(" ","_");
		byte[] srcpath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(srcpath, "image/png", screenshotName);
	}
	
	@After(order=2)
	public void teardown(Scenario scenario) throws IOException
	{
		if(scenario.isFailed())
		{
			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
	        Date date = new Date();
			String stepname = scenario.getName();
			String path = ".Screenshots\\" + stepname 
					       + "."+ dateFormat.format(date) +".png" ;
			File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshots, new File(path));
		}
	}
	
	
	@After(order=1)
	public void closebrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	
	
	
}
