package eComm.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {

	private WebDriver driver;

	private By usernam = By.name("username");
	private By passwrd = By.name("password");
	private By loginbtn = By.xpath("//button[@type='submit']");
	private By usernamecapture = By.xpath("(//div[@class='orangehrm-login-form']//div//p)[1]");
	private By pswdcapture = By.xpath("(//div[@class='orangehrm-login-form']//div//p)[2]");

	public Loginpage(WebDriver driver) {
		this.driver = driver;
	}

	public String getpagetitle() {
		return driver.getTitle();
	}

	public void enterusername(String uname) throws InterruptedException {
//		driver.findElement(username).clear();
		Thread.sleep(2000);
		driver.findElement(usernam).sendKeys(uname);
	}

	public void enterpassword(String pwd) throws InterruptedException {
//		driver.findElement(password).clear();
		Thread.sleep(1000);
		driver.findElement(passwrd).sendKeys(pwd);
	}

	public void clickloginbtn() {
		driver.findElement(loginbtn).click();
	}

	public String capturevalue (String field) {
		if(field.equals("username")) {
			String value = driver.findElement(usernamecapture).getText();
			return value;
			}
		else {
			String value = driver.findElement(pswdcapture).getText();
			return value;
		}
		
		
	}
}
