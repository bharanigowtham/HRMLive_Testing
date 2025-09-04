package eComm.Stepdefinitions;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Loginpage;
import eComm.Utilities.Configpropertiesreader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class TS001Loginfunctionality_Stepdef {

	
	private Configpropertiesreader config = new Configpropertiesreader();
	private Loginpage loginpage = new Loginpage(DriverFactory.getdriver());
	
	private DriverFactory driverfactory;
	private WebDriver driver;
	Properties prop;
	
	String actualpagetitle;
	String USERNAME_FINAL;
	String PASSWORD_FINAL;
	
	@Given("I launch url & have to login to system with valid credentials")
	public void i_launch_url_have_to_login_to_system_with_valid_credentials(DataTable datatable) throws Exception {
		prop = config.init_properties();
		String AppUrl = prop.getProperty("url");
		DriverFactory.getdriver().get(AppUrl);
		List<Map<String,String>> creds = datatable.asMaps();
		String uname = creds.get(0).get("Username");
		String pswd = creds.get(0).get("Password");
		
		loginpage.enterusername(uname);
		loginpage.enterpassword(pswd);
		loginpage.clickloginbtn();
		Thread.sleep(2000);
	}
	
	@Given("User is on login page")
	public void user_is_on_login_page() throws Exception {
		prop = config.init_properties();
		String AppUrl = prop.getProperty("url");
		DriverFactory.getdriver().get(AppUrl);
	}

	@When("User gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		actualpagetitle = loginpage.getpagetitle();
		System.out.println("Actual page title = " + actualpagetitle);
	}

	@SuppressWarnings("deprecation")
	@Then("Page title should be {string}")
	public void page_title_should_be(String expectedtitle) throws InterruptedException {
		Thread.sleep(3000);
		if (expectedtitle.equals(actualpagetitle)) {
			System.out.println("Title is matched");
		} else {
			Assert.fail("Title not matched");
		}

	}

	@When("User enters the Username as {string}")
	public void user_enters_the_username_as(String uname) throws InterruptedException {
		loginpage.enterusername(uname);
	}

	@When("User enters password as {string}")
	public void user_enters_password_as(String pswd) throws InterruptedException {
		loginpage.enterpassword(pswd);
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		loginpage.clickloginbtn();
	}

	@Then("Login Page validation should contain {string}")
	public void login_page_validation_should_contain(String validationmsg) {
		String pagsrc = DriverFactory.getdriver().getPageSource();
		if(pagsrc.contains(validationmsg))
			System.out.println("Validation as expected");
		else 
			System.out.println("No validation message displayed as expected");
	}
	
	@When("I capture username and password")
	public void Icapturusernameandpassword() throws InterruptedException {
		Thread.sleep(3000);
		String actualuname = loginpage.capturevalue("username");
		System.out.println(actualuname);
		String[] usernamedet = actualuname.split(":");
		USERNAME_FINAL = usernamedet[1].trim();
		System.out.println(USERNAME_FINAL);
		
		String actualpaswd = loginpage.capturevalue("password");
		System.out.println(actualpaswd);
		String[] passwordet = actualpaswd.split(":");
		PASSWORD_FINAL = passwordet[1].trim();
		System.out.println(PASSWORD_FINAL);
	}
	
	@When("I enter same username and password")
	public void Ientersameusernameandpassword() throws InterruptedException {
		
		loginpage.enterusername(USERNAME_FINAL);
		loginpage.enterpassword(PASSWORD_FINAL);
		
	}
	
	
}
