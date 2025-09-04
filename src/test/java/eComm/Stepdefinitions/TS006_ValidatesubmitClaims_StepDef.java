package eComm.Stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Claimspage;
import eComm.Pageobjects.Helpers;
import eComm.Pageobjects.Webcontrol;
import eComm.Utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TS006_ValidatesubmitClaims_StepDef {
	
	private WebDriver driver;
	private DriverFactory driverfactory;
	private Claimspage claimspage = new Claimspage(driverfactory.getdriver());
	private Webcontrol wcontrol = new Webcontrol(driverfactory.getdriver());
	ExcelReader excelreader = new ExcelReader();
	
	@When("I click on {string}")
	public void i_click_on(String btnName) {
	    claimspage.selectClaimMethod(btnName);
	}

	@When("I create claim request from {string} & {int}")
	public void i_create_claim_request(String sheetname, int rowno) throws InvalidFormatException, IOException {
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
	    wcontrol.selectdropdownvaluebytextgiven(1, "Event", testdata.get(rowno).get("Event"));
	    wcontrol.selectdropdownvaluebytextgiven(2, "Currency", testdata.get(rowno).get("Currency"));
	    claimspage.clickCreate();
	    
	}

	@When("Add expense details from {string} & {int}")
	public void add_expense_details(String sheetname, int rowno) throws Exception {
		try {
			
			List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
			String noof = testdata.get(rowno).get("NoofExpensetoClaim");
			int noofexp = Integer.parseInt(noof);
			for(int i=1;i<=noofexp;i++)
			{
				claimspage.clickonAdd();
				wcontrol.selectdropdownvaluebytextgiven(1, "Expense Type", testdata.get(rowno).get("Expensetype"));
				String date = testdata.get(rowno).get("Date"); // DD-MM-YYYY
				System.out.println(date);
				String[] datearray = date.split("-");
				String datetoenter = datearray[2]+"-"+datearray[0]+"-"+datearray[1];
				System.out.println(datetoenter);
				claimspage.enterdate(datetoenter);
				claimspage.enteramount(testdata.get(rowno).get("Amount"));
				claimspage.enterNote(testdata.get(rowno).get("Note"));
				wcontrol.ClickSavebyindexno("save1", 1);
				Thread.sleep(3000);
				rowno++;
			}
			Helpers.Scrolldownbyjs("400");		
		}  
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate the expense details & consolidate expense amount from {string} & {int}")
	public void validate_the_expense_details_consolidate_expense_amount(String sheetname, int rowno) throws Exception {
	/*	System.out.println("Validation started...");
	   try {
	    	List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
	    	String noof = testdata.get(rowno).get("NoofExpensetoClaim");
			int noofexp = Integer.parseInt(noof);
			int start=0,start2=0,consolfromtable=0,consolfromTD=0,totalvalueinUI=0;
			for(int i=1;i<=noofexp;i++)
			{
				String amountval = driver.findElement(By.xpath("((//div[@role='table'])[1]//descendant::div[@class='oxd-table-card'])["+i+"]//div[5]")).getText();
				String[] amntval = amountval.split(".");
				String onlyamt = amntval[1];
				int amt = Integer.parseInt(onlyamt);
				consolfromtable = start + amt;
				start = consolfromtable;
				
				String amount = testdata.get(i-1).get("Amount");
				int amtfrmtd = Integer.parseInt(amount);
				consolfromTD = start2+amtfrmtd;
				start2=consolfromTD;
			}
			String amounttext = driver.findElement(By.xpath("//div[@class='orangehrm-bottom-container']//p")).getText();
			String[] amtarray = amounttext.split(":");
			String[] newamt = amtarray[1].trim().replaceAll(",","").split(".");
			totalvalueinUI = Integer.parseInt(newamt[0]);
			if(consolfromTD==consolfromtable && consolfromTD==totalvalueinUI)
			{
				System.out.println("Captured values are SAME");
			}
			else 
			{
				System.out.println("Captured values are DIFFFERENT");
			}
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    } */
	}
	
	@Then("click on submit")
	public void click_on_submit() {
	   claimspage.clickonsubmit();
	}
	
	@Given("Enter ref ID")
	public void enter_ref_id() {
	   
	}
	
	@Given("Click on search")
	public void click_on_search() {
	   
	}

	@Then("Claims details should be displayed in table & validate the details of the same")
	public void claims_details_should_be_displayed_in_table_validate_the_details_of_the_same() {
	    
	}
}
