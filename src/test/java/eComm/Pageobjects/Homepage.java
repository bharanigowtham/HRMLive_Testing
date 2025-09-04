package eComm.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.locators.RelativeLocator;
//import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;

public class Homepage {
	
	private WebDriver driver;
	
	public Homepage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By addemployeebtn = By.xpath("//a[contains(text(),'Add Employee')]");
	private By empid = By.xpath("((//div[@class='oxd-input-group oxd-input-field-bottom-space'])[5]//div)[2]//input");
	private By savebtn = By.xpath("//button[@type='submit']");
	private By searchbtn = By.xpath("//button[normalize-space()='Search']");
	private By empnameintable = By.xpath("((//div[@class='orangehrm-container']//div[@class='oxd-table-body']//div)[2]//div//div)[4]");
	private By feedbox = By.xpath("//textarea[@placeholder=\"What's on your mind?\"]");
	private By postbtn = By.xpath("//button[@type='submit']");
	private By newsfeed = By.xpath("(((//div[@class='orangehrm-buzz-newsfeed']/child::div)[3]//div)[1]//p)[3]");
	
	public void clickonmodulename (String uname) throws InterruptedException {
		try {
			List<WebElement> allelem = driver.findElements(By.xpath("//div[@class='oxd-sidepanel-body']//ul//li//span"));
			for(WebElement module1 : allelem)
			{
				String modulename = module1.getText();				
				if(modulename.equals(uname)) 
				{
					System.out.println("Given module name = "+modulename);
					module1.click();
					Thread.sleep(2000);
				}
			}
		}
		catch(StaleElementReferenceException e) {
			e.printStackTrace();
		}	
	}
	
	public void clickonelembytext(String elemname) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'"+elemname+"')]"));
		ele.click();
		Thread.sleep(500);
	}
	
	public void clickSearchbtn() {
		try {
			driver.findElement(searchbtn).click();
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUsernameintable() {
		boolean sts = driver.findElement(By.xpath("(//div[@class='orangehrm-container']//div[@class='oxd-table-body']//div[contains(text(),'Full-Time Permanent')])[1]")).isDisplayed();
		return sts;
	}
	
	public void clickonempnameintable() {
		try {
			driver.findElement(empnameintable).click();
		}
		catch(Exception e) {
			
		}
	}
	
	public void enterfeedmessage(String postmessage) {
		try {
			driver.findElement(feedbox).sendKeys(postmessage);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void clickpostbtn() {
		try {
			driver.findElement(postbtn).click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getfeedtextfromnewsfeed() {
		String feedtext = null;
		try {
			feedtext= driver.findElement(newsfeed).getText();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return feedtext;
	}
	
	
}
