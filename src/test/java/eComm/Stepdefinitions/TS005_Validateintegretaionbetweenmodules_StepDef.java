package eComm.Stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Homepage;
import eComm.Pageobjects.Webcontrol;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TS005_Validateintegretaionbetweenmodules_StepDef {
	
	private DriverFactory driverfactory;
	private WebDriver driver;
	private Homepage homepage = new Homepage(DriverFactory.getdriver());
	private Webcontrol wcontrol = new Webcontrol(DriverFactory.getdriver());

	
	@When("I click on employee list")
	public void i_click_on_employee_list() throws InterruptedException {
		
		try {
			homepage.clickonelembytext("Employee List");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@When("I Select employment status as {string}")
	public void i_select_employment_status_as(String employmentStatus) {
		
		try {
			wcontrol.selectdropdownvaluebytextgiven(1, "Employment Status", employmentStatus);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@When("I click on search")
	public void i_click_on_search() {
		homepage.clickSearchbtn();
	}

	@Then("employee details should be display in a table")
	public void employee_details_should_be_display_in_a_table() {
		try 
		{
			boolean status = homepage.checkUsernameintable();
			if(status==true) {
				System.out.println("User displayed...");
				}
			else {
				System.out.println("User not displayed....");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Then("I Click on employee name")
	public void i_click_on_employee_name() {
		homepage.clickonempnameintable();
	}

	@Then("I should be redirected to My info module")
	public void i_should_be_redirected_to_my_info_module() {
		String myinfopageurl = DriverFactory.getdriver().getCurrentUrl();
		System.out.println("My info page url = " + myinfopageurl);
		if(myinfopageurl.contains("viewPersonalDetails"))
		{
			Assert.assertTrue(true);
			System.out.println("Navigated to my info page");
		}
		else {
			Assert.assertTrue(false);
			System.out.println("Navigation failed....");
		}
		
	}

}
