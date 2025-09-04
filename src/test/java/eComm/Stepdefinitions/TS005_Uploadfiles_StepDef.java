package eComm.Stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Homepage;
import eComm.Pageobjects.Webcontrol;
import eComm.Utilities.ExcelReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class TS005_Uploadfiles_StepDef {
	
	private DriverFactory driverfactory;
	private WebDriver driver;
	
	private Homepage homepage = new Homepage(DriverFactory.getdriver());
	private Webcontrol wcontrol = new Webcontrol(DriverFactory.getdriver());
	ExcelReader excelreader = new ExcelReader();

	String postedmessage = null;
	
	@When("I enter post message {string} in feed box")
	public void i_enter_post_message_in_feed_box(String postmessage) {
	    homepage.enterfeedmessage(postmessage);
	    postedmessage = postmessage;
	}

	@When("click on post")
	public void click_on_post() throws InterruptedException {
	   homepage.clickpostbtn();
	   Thread.sleep(3000);
	}

	@Then("post should be successful and should see same post in feed")
	public void post_should_be_successful_and_should_see_same_post_in_feed() {
	    String messgfromfeed = homepage.getfeedtextfromnewsfeed();
	    if(postedmessage.equals(messgfromfeed))
	    {
	    	System.out.println("Validation successful");
	    	System.out.println("Actual mesg = " + messgfromfeed);
	    	System.out.println("Expected mesg = " + postedmessage);
	    }
	    else {
	    	System.out.println("Fail: News text not as expected...");
	    	Assert.fail();
	    }
	}

	@When("I enter post message in feed box from {string} and {int}")
	public void i_enter_post_message_in_feed_box_from_and(String sheetname, int RowNo) throws InvalidFormatException, IOException {
		try {
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		String noofpost = testdata.get(0).get("Noofposttoupload");
		int noofpoststoupload = Integer.parseInt(noofpost);
		for(int i=1;i<=noofpoststoupload;i++) {
			homepage.enterfeedmessage(testdata.get(RowNo).get("Message"));
			homepage.clickpostbtn();
			Thread.sleep(2000);
			RowNo++;
		}
		System.out.println("All Posts uploaded....");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("post should be successful and should see same entered post in feed table for {string} and {int}")
	public void post_should_be_successful_and_should_see_same_entered_post_in_feed(String sheetname, int RowNo) throws InvalidFormatException, IOException {
		try {
			List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", "Uploadpost");
			String noofpost = testdata.get(0).get("Noofposttoupload");
			int noofpoststoupload = Integer.parseInt(noofpost);
			for(int i=noofpoststoupload;i<0;i--)
			{
				int j = noofpoststoupload-1;
				String postmessage = testdata.get(j).get("Message");
				String postmessagefromtable  = driverfactory.getdriver().findElement(By.xpath("//div[@class='oxd-grid-1 orangehrm-buzz-newsfeed-posts']/child::div["+i+"]/child::div//p[@class='oxd-text oxd-text--p orangehrm-buzz-post-body-text']")).getText();
				if(postmessage.equalsIgnoreCase(postmessagefromtable))
				{
					System.out.println("Post message from data = " + postmessage);
					System.out.println("Posted message from table = " + postmessagefromtable);
					System.out.println("Validation success: Messages are same....");
				}
				else {
					System.out.println("Post message from data = " + postmessage);
					System.out.println("Posted message from table = " + postmessagefromtable);
					System.out.println("Validation failed: messages not expected....");
				}
			}
			System.out.println("Validation completed....");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@When("I Click on share photos & upload a photo")
	public void i_click_on_share_photos_upload_a_photo() {
	   
	}

	@When("I enter post message")
	public void i_enter_post_message() {
	    
	}

	@When("click on share")
	public void click_on_share() {
	   
	}

	@Then("I should see same post in feed")
	public void i_should_see_same_post_in_feed() {
	    
	}
}
