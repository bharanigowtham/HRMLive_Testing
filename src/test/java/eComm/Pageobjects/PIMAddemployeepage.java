package eComm.Pageobjects;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.locators.RelativeLocator;

public class PIMAddemployeepage {

	private WebDriver driver;

	public PIMAddemployeepage(WebDriver driver) {
		this.driver = driver;
	}

	private By empid = By.xpath("((//div[@class='oxd-input-group oxd-input-field-bottom-space'])[5]//div)[2]//input");
	private By savebtn = By.xpath("//button[@type='submit']");
	private By profilepicbtn = By.cssSelector("input[type=file]");

	public void uploadprofile() throws InterruptedException {
		Thread.sleep(2000);
		WebElement ele = driver.findElement(profilepicbtn);
		File file = new File(".//Uploadfiles//Profilepicture.JPG");
		ele.sendKeys(file.getAbsolutePath());
		Thread.sleep(2000);
	}

	/*
	 * Thread.sleep(1000); driver.findElement(By.xpath(
	 * "//div[@class='orangehrm-action-header']//button[@type='button']")).click();
	 * Thread.sleep(1000); Helpers.Scrolldownbyjs("+200"); File file = new
	 * File("C:\\Users\\bharani.g\\Desktop\\employee.pdf"); WebElement browserbtn =
	 * driver.findElement(By.cssSelector("input[type=file]"));
	 * browserbtn.sendKeys(file.getAbsolutePath()); Thread.sleep(1000); WebElement
	 * uploadedfiletext =
	 * driver.findElement(By.xpath("//div[@class='oxd-file-input-div']")); String
	 * actualtext = uploadedfiletext.getText(); System.out.println(actualtext);
	 */

	public void entertextbyplaceholder(String placeholdertext, String name) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//input[@placeholder='" + placeholdertext + "']"));
		ele.sendKeys(name);
		Thread.sleep(200);
	}

	public void enterempid(String id) throws InterruptedException {
		Thread.sleep(2000);
		WebElement ele = driver.findElement(empid);
		Actions actions = new Actions(driver);
		actions.click(ele).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build()
				.perform();
		Thread.sleep(2000);
		ele.sendKeys(id);

	}

	public void clicksavebtn() throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(savebtn).click();
	}

	public void selectdropdownvaluebytextprovided(String field, String value) throws InterruptedException {
		try {
			if (field.equalsIgnoreCase("Nationality") || field.equalsIgnoreCase("Relationship")	|| field.equalsIgnoreCase("Issued By") 
					|| field.equalsIgnoreCase("Job Title") || field.equalsIgnoreCase("Country") || field.equalsIgnoreCase("Pay Grade") 
					|| field.equalsIgnoreCase("Reporting Method")|| field.equalsIgnoreCase("Level") || field.equalsIgnoreCase("Skill") 
					|| field.equalsIgnoreCase("Language") || field.equalsIgnoreCase("Membership")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[1]")).click();
				Thread.sleep(2000);
				WebElement ele = driver.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),'" + value + "')]"));
				Helpers.WaitforvisibilityofElement(ele, 30);
				ele.click();
				Thread.sleep(500);
			} else if (field.equalsIgnoreCase("Marital status") || field.equalsIgnoreCase("Job Category") || field.equalsIgnoreCase("Pay Frequency")
					|| field.equalsIgnoreCase("Fluency") || field.equalsIgnoreCase("Subscription Paid By") ) {
				Thread.sleep(500);
				driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[2]")).click();
				WebElement ele = driver
						.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),'" + value + "')]"));
				Helpers.WaitforvisibilityofElement(ele, 30);
				ele.click();
				Thread.sleep(500);
			} else if (field.equalsIgnoreCase("Blood type") || field.equalsIgnoreCase("Sub Unit") || field.equalsIgnoreCase("Currency")
					|| field.equalsIgnoreCase("Competency") || field.equalsIgnoreCase("Currency")) {
				Thread.sleep(500);
				driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[3]")).click();
				WebElement ele = driver
						.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),'" + value + "')]"));
				Helpers.WaitforvisibilityofElement(ele, 30);
				ele.click();
				Thread.sleep(500);
			} else if (field.equalsIgnoreCase("Location") || field.equalsIgnoreCase("Account Type")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[4]")).click();
				Thread.sleep(2000);
				WebElement ele = driver
						.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),'" + value + "')]"));
				Helpers.WaitforvisibilityofElement(ele, 30);
				ele.click();
				Thread.sleep(500);
			} else if (field.equalsIgnoreCase("Employment Status")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[5]")).click();
				Thread.sleep(2000);
				WebElement ele = driver
						.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),'" + value + "')]"));
				Helpers.WaitforvisibilityofElement(ele, 30);
				ele.click();
				Thread.sleep(500);
			}
//			else if(field.equalsIgnoreCase("Level")) {
//				Thread.sleep(2000);
//				driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[1]")).click();
//				Thread.sleep(2000);
//				WebElement ele = driver.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),'" + value + "')]"));
//				Helpers.WaitforvisibilityofElement(ele, 30);
//				ele.click();
//				Thread.sleep(500);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectdatefromcalendar(String yearsel, String monthSel, String datesel, int index)
			throws InterruptedException {
		try {
			WebElement dateDDbtn = driver.findElement(By.xpath("(//div[@class='oxd-date-wrapper'])[" + index + "]"));
			Helpers.WaitforClickableElement(dateDDbtn, 10);
			dateDDbtn.click();
			Thread.sleep(2000);
			WebElement yearDDbtn = driver.findElement(
					By.xpath("//div[@class='oxd-calendar-header']//ul/li[@class='oxd-calendar-selector-year']"));
			Helpers.WaitforClickableElement(yearDDbtn, 10);
			yearDDbtn.click();
			Helpers.Scrollupbyjs();
			Thread.sleep(1000);
			List<WebElement> allyears = driver.findElements(By.xpath("//li[@class='--active oxd-calendar-selector-year']//ul//li"));
			Thread.sleep(2000);
			for (WebElement year : allyears) {
				String yr = year.getText();
				if (yr.equals(yearsel)) {
					year.click();
					System.out.println(yr);
				}
			}
			Thread.sleep(1000);
			WebElement MonthDDbtn = driver.findElement(By.xpath("//li[@class='oxd-calendar-selector-month']//i")); // li[@class='oxd-calendar-selector-month']//i
//			 Helpers.WaitforClickableElement(MonthDDbtn, 10);
			Thread.sleep(2000);
			MonthDDbtn.click();
			Helpers.Scrollupbyjs();
			Thread.sleep(1000);
			List<WebElement> allMonth = driver.findElements(By.xpath("//li[@class='--active oxd-calendar-selector-month']//ul//li"));
			for (WebElement mon : allMonth) {
				String mn = mon.getText();
				if (mn.equals(monthSel)) {
					mon.click();
					System.out.println(mn);
				}
			}
			Thread.sleep(1000);
			List<WebElement> alldates = driver.findElements(By.xpath("//div[@class='oxd-calendar-wrapper']//div[@class='oxd-calendar-dates-grid']//div//div"));
			for (WebElement date : alldates) {
				String dt = date.getText();
				if (dt.equals(datesel)) {
					date.click();
					System.out.println(dt);
				}
			}
			Thread.sleep(2000);
			Helpers.Scrollupbyjs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectdropdown_Today(int index) {
		try {
			WebElement dateDDbtn = driver.findElement(By.xpath("(//div[@class='oxd-date-wrapper'])[" + index + "]"));
			dateDDbtn.click();
			Thread.sleep(500);
			WebElement ele = driver.findElement(By.cssSelector(".oxd-date-input-link.--today"));
			ele.click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selectradio(String radioname) throws InterruptedException {
		System.out.println(radioname);
		if (radioname.equalsIgnoreCase("Male") || radioname.equals("Passport")) {
			Thread.sleep(3000);
			WebElement ele = driver.findElement(By.xpath("(//div[@class='oxd-radio-wrapper'])[1]"));
//			WebElement ele = driver.findElement(By.xpath("//div[@class='oxd-radio-wrapper']//label//input[@value=1]"));
			Helpers.WaitforClickableElement(ele, 10);
			Thread.sleep(1000);
			ele.click();
		} else if (radioname.equalsIgnoreCase("Female") || radioname.equals("Visa")) {
			WebElement ele = driver.findElement(By.xpath("(//div[@class='oxd-radio-wrapper'])[2]"));
			Helpers.WaitforvisibilityofElement(ele, 10);
			ele.click();
			Thread.sleep(500);
		}
	}

	public void ClickonSavebyindexno(String savebtnno) throws InterruptedException {
		int index;
		if (savebtnno.equalsIgnoreCase("save1")) {
			index = 1;
			WebElement ele = driver.findElement(By.xpath("(//button[@type='submit'])[" + index + "]"));
			ele.click();
			Thread.sleep(1000);
		} else if (savebtnno.equalsIgnoreCase("save2")) {
			index = 2;
			WebElement ele = driver.findElement(By.xpath("(//button[@type='submit'])[" + index + "]"));
			ele.click();
			Thread.sleep(500);
		} else if (savebtnno.equalsIgnoreCase("save3")) {
			index = 3;
			WebElement ele = driver.findElement(By.xpath("(//button[@type='submit'])[" + index + "]"));
			ele.click();
			Thread.sleep(2000);
		}
	}

	public void sendattachment() throws InterruptedException {
		try {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='orangehrm-action-header']//button[@type='button']")).click();
			Thread.sleep(1000);
			Helpers.Scrolldownbyjs("+200");
			File file = new File(".//Uploadfiles//employee.pdf");
			WebElement browserbtn = driver.findElement(By.cssSelector("input[type=file]"));
			browserbtn.sendKeys(file.getAbsolutePath());
			Thread.sleep(1000);
			WebElement uploadedfiletext = driver.findElement(By.xpath("//div[@class='oxd-file-input-div']"));
			String actualtext = uploadedfiletext.getText();
			System.out.println(actualtext);
			// validation 1
			if (actualtext.equals("employee.pdf")) {
				System.out.println("File uploaded successfully...");
			} else {
				System.out.println("File not uploaded");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateuploadedfilename() {
		try {
			Helpers.Scrolldownbyjs("+800");
			// validation 2 - table
			WebElement filename = driver.findElement(By.xpath("((//div[@role='table']//div[@role='row'])[2]//div)[5]"));
			String filenamefromtable = filename.getText();
			if (filenamefromtable.equalsIgnoreCase("employee.pdf")) {
				System.out.println("Correct file name in table");
			} else {
				System.out.println("Incorrect file name");
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigatetogivensection(String sectionname) {
		try {
			Helpers.Scrollupfullbyjs();
			List<WebElement> allsections = driver.findElements(By.xpath("//div[@role='tablist']//div//a"));
			for (WebElement section : allsections) {
				String sect_name = section.getText();
				if (sect_name.equalsIgnoreCase(sectionname)) {
					System.out.println(sect_name);
					section.click();
					break;					
				} else {
					// System.out.println("Provide correct section name");
				}		
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterNameEmailTNandAddreess(String contactsection, int index, String value) {
		try {
			Thread.sleep(500);
			if (contactsection.equalsIgnoreCase("Address") || contactsection.equalsIgnoreCase("Save Emergency Contact - Name & rela")
					|| contactsection.equalsIgnoreCase("Name") || contactsection.equalsIgnoreCase("Salary Component") || 
					contactsection.equalsIgnoreCase("Company") || contactsection.equalsIgnoreCase("Job Title") || contactsection.equalsIgnoreCase("Institute")
					|| contactsection.equalsIgnoreCase("Major/Specialization") || contactsection.equalsIgnoreCase("Year") || contactsection.equalsIgnoreCase("GPA/Score")
					|| contactsection.equalsIgnoreCase("Years of Experience") || contactsection.equalsIgnoreCase("Subscription Amount") || contactsection.equalsIgnoreCase("Please Specify")) {
				WebElement ele = driver.findElement(By.xpath("((//div[@class='oxd-form-row'])[1]//input)[" + index + "]"));
				Helpers.entervalue(ele, value);
			} else if (contactsection.equalsIgnoreCase("Telephone") || contactsection.equalsIgnoreCase("Amount")  ) {			       
				WebElement ele = driver.findElement(By.xpath("((//div[@class='oxd-form-row'])[2]//input)[" + index + "]"));
				Helpers.entervalue(ele, value);
			} else if (contactsection.equalsIgnoreCase("Email") || contactsection.equalsIgnoreCase("Account Number") || contactsection.equalsIgnoreCase("Routing Number") 
						|| contactsection.equalsIgnoreCase("Deposit Amount")) {
				WebElement ele = driver.findElement(By.xpath("((//div[@class='oxd-form-row'])[3]//input)[" + index + "]"));
				Helpers.entervalue(ele, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickonAddbyindex(int index) {
		WebElement ele = driver.findElement(By.xpath("(//div[@class='orangehrm-action-header']//button[@type='button'])[" + index + "]"));
		Helpers.WaitforClickableElement(ele, 10);
		ele.click();
		
	}

}
