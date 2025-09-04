package eComm.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Recruitmentpage {
	
	private WebDriver driver;
	
	public Recruitmentpage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By vacanciesBtn = By.xpath("//a[normalize-space()='Vacancies']");
	private By add_Btn = By.xpath("//button[normalize-space()='Add']");
	private By noofPos = By.xpath("");
	
	public void clickVacanciesBtn() {
		try {
			//Helpers.WaitforvisibilityofElement(vacanciesBtn, 10);
			WebElement vacnBtn = driver.findElement(vacanciesBtn);
			vacnBtn.click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickAddbtn() {
		try {
			WebElement addBtn = driver.findElement(add_Btn);
			addBtn.click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterTextbyvalueprovided(String eleName, int index, String value) {
		
		try {
			if(eleName.equalsIgnoreCase("Vacancy Name") || eleName.equalsIgnoreCase("Number of Positions"))
			{
				WebElement ele = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])["+index+"]"));
				Helpers.entervalue(ele, value);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void selectdropdownvaluebytextprovided(String fieldName,String value) {
		
		try {
			if(fieldName.equalsIgnoreCase("Job Title")) {
				WebElement ele = driver.findElement(By.xpath("//div[@class='oxd-select-wrapper'][1]"));
				ele.click();
				Thread.sleep(1000);
				WebElement ele1 = driver.findElement(By.xpath("//div[@role='listbox']//div//span[contains(text(),"+value+")]"));
				ele1.click();
				Thread.sleep(500);
			}
		}
		catch(Exception e) {
			
		}	
	}

}
