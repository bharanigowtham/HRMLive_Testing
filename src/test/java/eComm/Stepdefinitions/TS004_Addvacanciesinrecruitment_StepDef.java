package eComm.Stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Helpers;
import eComm.Pageobjects.Homepage;
import eComm.Pageobjects.PIMAddemployeepage;
import eComm.Pageobjects.Recruitmentpage;
import eComm.Utilities.ExcelReader;
import io.cucumber.java.en.When;

public class TS004_Addvacanciesinrecruitment_StepDef {
	
	private Homepage homepage = new Homepage(DriverFactory.getdriver());
	private PIMAddemployeepage PIMAddemppage = new PIMAddemployeepage(DriverFactory.getdriver());
	private Recruitmentpage recruitpage = new Recruitmentpage(DriverFactory.getdriver());
	ExcelReader excelreader = new ExcelReader();
	

	private DriverFactory driverfactory;
	private WebDriver driver;
	
	@When("I click on vacancies")
	public void i_click_on_vacancies() {
		recruitpage.clickVacanciesBtn();
	     
	}

	@When("I click on Add")
	public void i_click_on_add() {
		recruitpage.clickAddbtn();
	}
	
	@When("add required details from {string} & {int}")
	public void add_required_details(String sheetname, int RowNo) throws Exception, IOException {
	    try {
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		
		String total_vacancies = testdata.get(RowNo).get("Noofvacaniestoadd");
		int total_vac = Integer.parseInt(total_vacancies);
		
		for(int i=1;i<=total_vac;i++) {
			
			String vac_name = testdata.get(RowNo).get("Vacancy Name") + Helpers.generateRandomString();
			System.out.println(vac_name);
			recruitpage.enterTextbyvalueprovided("Vacancy Name", 2, vac_name);
			recruitpage.selectdropdownvaluebytextprovided("Job Title", testdata.get(RowNo).get("Job title"));
			
			DriverFactory.getdriver().findElement(By.xpath("//div//textarea[@placeholder='Type description here']")) 
			                         .sendKeys(testdata.get(RowNo).get("Description"));
			
			Thread.sleep(2000);
			DriverFactory.getdriver().findElement(By.xpath("//input[@placeholder='Type for hints...']")).click();
			Thread.sleep(2000);
			DriverFactory.getdriver().findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys(testdata.get(RowNo).get("Hiring Manager"));

			Thread.sleep(3000);
			String HM = testdata.get(RowNo).get("Hiring Manager");
			DriverFactory.getdriver().findElement(By.xpath("//div[@class='oxd-autocomplete-wrapper']//div[@role='listbox']//div//span[contains(text(),'"+HM+"')]")).click();
			recruitpage.enterTextbyvalueprovided("Number of Positions", 3, testdata.get(RowNo).get("Number of Positions"));
			PIMAddemppage.ClickonSavebyindexno("save1");
			RowNo++;
			Thread.sleep(2000);
			recruitpage.clickVacanciesBtn();
			recruitpage.clickAddbtn();
			Thread.sleep(1000);
		}	
	}
	    catch(Exception e )
		{
		 e.printStackTrace();
		}
	
		
	}
	
	
}
