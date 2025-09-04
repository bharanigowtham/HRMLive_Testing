package eComm.Stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Helpers;
import eComm.Pageobjects.Homepage;
import eComm.Utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TS007_Takescreenshotformodules_StepDef {
	
	private WebDriver driver;
	private DriverFactory driverfactory;
	ExcelReader excelreader = new ExcelReader();
	private Homepage homepage = new Homepage(DriverFactory.getdriver());

	@Given("I navigate to {string}")
	public void i_navigate_to_from_and(String modulename) throws Exception {
		
		//List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		
		homepage.clickonmodulename(modulename);
		Thread.sleep(2000);
		
	}

	@Then("I have to capture screeenshots of the pages for the {string}")
	public void i_have_to_capture_screeenshots_of_the_pages(String modulename) throws InterruptedException {
		
		By footerelem = By.xpath("(//div[@class='oxd-layout-footer']//p)[2]");
		
		long scrollvalue = Helpers.getscrollvalue(footerelem);
		System.out.println(scrollvalue);
		
		/*	int val = (int) scrollvalue/10;
		System.out.println(val); */
		int j=0;
		
		if(modulename.equalsIgnoreCase("PIM") || modulename.equalsIgnoreCase("Recruitment") ) {
			j=200;
		}
		else if(modulename.equalsIgnoreCase("Leave") || modulename.equalsIgnoreCase("Time") || modulename.equalsIgnoreCase("Performance")
				|| modulename.equalsIgnoreCase("Dashboard") || modulename.equalsIgnoreCase("Claim") || modulename.equalsIgnoreCase("Buzz")) {
			j=100;
		}
		else if(modulename.equalsIgnoreCase("My Info")) {
			j=200;
		}
		
		for(int i=0;i<=scrollvalue;i+=j)
		{
			Helpers.Scrolldownbyjs(String.valueOf(i));
			Helpers.takethescreenshot(modulename);
			System.out.println(i);
			Thread.sleep(500);
		}
		
	}
}
