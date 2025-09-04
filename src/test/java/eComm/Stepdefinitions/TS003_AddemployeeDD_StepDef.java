package eComm.Stepdefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Helpers;
import eComm.Pageobjects.PIMAddemployeepage;
import eComm.Utilities.Configpropertiesreader;
import eComm.Utilities.ExcelReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class TS003_AddemployeeDD_StepDef {
		
	private PIMAddemployeepage PIMAddemppage = new PIMAddemployeepage(DriverFactory.getdriver());
	String sheetname = "Addemp";
	ExcelReader excelreader = new ExcelReader();
	
	@And("Enter emp name, emp id from {string} & {int} & click on save")
	public void enterempNameandIdandclickonsave(String sheetname, int rowno) throws Exception {
	
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		
		PIMAddemppage.entertextbyplaceholder("First Name", testdata.get(rowno).get("Firstname"));
		PIMAddemppage.entertextbyplaceholder("Last Name", testdata.get(rowno).get("Lastname"));
		String id_status = testdata.get(rowno).get("Employee_ID");
		if(id_status.equalsIgnoreCase("Random")) {
			PIMAddemppage.enterempid(Helpers.generateRandomAlphaNumeric(5));
		}
		else {
			PIMAddemppage.enterempid(testdata.get(rowno).get("Employee_ID"));
		}
		PIMAddemppage.ClickonSavebyindexno("save1");
		Thread.sleep(5000);
	}
	
	@And("Selecting Nationality, Marital status, DOB & Gender from {string} & {int}")
	public void SelectingNationalityMaritalstatusDOBandGender(String sheetname, int rowno) throws Exception {
		
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);

		PIMAddemppage.selectdropdownvaluebytextprovided("Nationality", testdata.get(rowno).get("Nationality"));
		PIMAddemppage.selectdropdownvaluebytextprovided("Marital status", testdata.get(rowno).get("Marital status"));
		String dob = testdata.get(rowno).get("DOB"); // DD-MM-YYYY
		String[] dobarray = dob.split("-");
		String month = Helpers.mapmonthwithgivennum(dobarray[1]);
		System.out.println(month);
		PIMAddemppage.selectdatefromcalendar(dobarray[2], month, dobarray[0], 2);
		PIMAddemppage.selectradio(testdata.get(rowno).get("Gender"));
		Thread.sleep(5000);
	}
	
	@And("Select given blood type from {string} & {int}")
	public void Select_given_blood_type(String sheetname, int rowno) throws Exception{
		
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);

		PIMAddemppage.selectdropdownvaluebytextprovided("Blood type", testdata.get(rowno).get("Bloodtype"));
	}
	
	@And("enter address, telephone and email details from {string} & {int}")
	public void enter_address_telephone_and_email_details_from(String sheetname, int rowno) throws Exception {
		
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 1, testdata.get(rowno).get("Address1"));
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 2, testdata.get(rowno).get("Address2"));
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 3, testdata.get(rowno).get("Address3"));
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 4, testdata.get(rowno).get("Address4"));
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 5, testdata.get(rowno).get("Address5"));
		PIMAddemppage.selectdropdownvaluebytextprovided("Country", testdata.get(rowno).get("Country"));
		PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 1, testdata.get(rowno).get("Homeno"));
		PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 2, testdata.get(rowno).get("Mobileno"));
		PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 3, testdata.get(rowno).get("Workno"));
		if(testdata.get(rowno).get("Workemail").equals("Random")) {
			String workemail = "Test" + Helpers.generateRandomString() + "@gmail.com";
			PIMAddemppage.enterNameEmailTNandAddreess("Email", 1, workemail);
		}
		else {
			PIMAddemppage.enterNameEmailTNandAddreess("Email", 1, testdata.get(rowno).get("Workemail"));
		}
		if(testdata.get(rowno).get("Otheremail").equals("Random")) {
			String otheremail = "Test1" + Helpers.generateRandomString() + "@gmail.com";
			PIMAddemppage.enterNameEmailTNandAddreess("Email", 2, otheremail);
		}
		else {
			PIMAddemppage.enterNameEmailTNandAddreess("Email", 2, testdata.get(rowno).get("Otheremail"));
		}
		Thread.sleep(2000);
	}
	
	@And("Add and enter emergency contact details from {string} & {int}")
	public void Add_and_enter_emergency_contact_details_from(String sheetname, int rowno) throws Exception {
		
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		
		String cnt = testdata.get(rowno).get("Noofemeergencycontactstoadd");
		int count = Integer.parseInt(cnt);
		System.out.println(count); //2
		for(int i=1;i<=count;i++)
		{				
			PIMAddemppage.clickonAddbyindex(1);
			PIMAddemppage.enterNameEmailTNandAddreess("Save Emergency Contact - Name & rela", 1, testdata.get(rowno).get("Name_EC"));
			PIMAddemppage.enterNameEmailTNandAddreess("Save Emergency Contact - Name & rela", 2, testdata.get(rowno).get("Relation_EC"));
			PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 1, testdata.get(rowno).get("Homeno"));
			PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 2, testdata.get(rowno).get("Mobileno"));
			PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 3, testdata.get(rowno).get("Workno"));
			PIMAddemppage.ClickonSavebyindexno("save1");
			Thread.sleep(5000);			
			
			ArrayList<String> arraylist1 = new ArrayList<>(Arrays.asList(testdata.get(rowno).get("Name_EC"),
																		testdata.get(rowno).get("Relation_EC"),
																		testdata.get(rowno).get("Homeno"),
																		testdata.get(rowno).get("Mobileno"),
																		testdata.get(rowno).get("Workno")  ));
			System.out.println("Actual data passed for emergency contact " + i + " is " + arraylist1);
			
			ArrayList<String> arraylist2 = new ArrayList<>();
			for (int j = 2; j <= 6; j++) {
				WebElement elem = DriverFactory.getdriver().findElement(By
						.xpath("((//div[@class='oxd-table-body'][1]//div[@class='oxd-table-card'])["+i+"]//div[@role='cell'])["
								+ j + "]"));
				String value = elem.getText();
				arraylist2.add(value);
			}
			Thread.sleep(2000);
			System.out.println("Captured data from table for emergency contact " + i + " is " + arraylist2);

			if (arraylist1.equals(arraylist2)) {
				System.out.println("ALL DATAS ARE SAME AS EXPECTED");
			} else {
				System.out.println("Data passed and captured data are different...");
			}
			arraylist1.clear();
			arraylist2.clear();                  
			rowno++;
		}
	}            
	
	@And("Add and enter dependent details from {string} & {int}")
	public void Add_and_enter_dependent_details_from(String sheetname, int rowno) throws Exception {
		
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		
		String cnt = testdata.get(rowno).get("Noofdependantstoadd");
		int count = Integer.parseInt(cnt);
		System.out.println(count); 				
		
		for(int i=1;i<=count;i++) {
			PIMAddemppage.clickonAddbyindex(1);
			PIMAddemppage.enterNameEmailTNandAddreess("Name", 1, testdata.get(rowno).get("Dep_name"));
			String relation = testdata.get(rowno).get("Relationship");
			if(relation.equalsIgnoreCase("Child")) {
				PIMAddemppage.selectdropdownvaluebytextprovided("Relationship", testdata.get(rowno).get("Relationship"));
			}
			else if(relation.equalsIgnoreCase("Other")) {
				PIMAddemppage.selectdropdownvaluebytextprovided("Relationship", testdata.get(rowno).get("Relationship"));
				Thread.sleep(2000);
				PIMAddemppage.enterNameEmailTNandAddreess("Please Specify", 2, testdata.get(rowno).get("Please Specify"));				
			}
			String dob = testdata.get(rowno).get("Dep_DateofBirth"); // DD-MM-YYYY
			String[] dobarray = dob.split("-");
			String month = Helpers.mapmonthwithgivennum(dobarray[1]);
			System.out.println(month);
			PIMAddemppage.selectdatefromcalendar(dobarray[2], month, dobarray[0], 1);
			PIMAddemppage.ClickonSavebyindexno("save1");
			Thread.sleep(5000);
			rowno++;
		}	
	}
	
	@And("Add and enter document details from {int}")
	public void Add_and_fill_the_document_details_from(int rowno) throws Exception, IOException {
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		
		PIMAddemppage.clickonAddbyindex(1);
		String documenttype = testdata.get(rowno).get("Document type");
		if (documenttype.equalsIgnoreCase("Passport")) {
			PIMAddemppage.selectradio(documenttype);
		} else if (documenttype.equalsIgnoreCase("Visa")) {
			PIMAddemppage.selectradio(documenttype);
		}
		WebElement ele = DriverFactory.getdriver().findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")); //Issued date
		ele.sendKeys(testdata.get(rowno).get("Number"));
		String dob = testdata.get(rowno).get("Issued Date"); // DD-MM-YYYY
		String[] dobarray = dob.split("-");
		String month = Helpers.mapmonthwithgivennum(dobarray[1]);
		System.out.println(month);
		PIMAddemppage.selectdatefromcalendar(dobarray[2], month, dobarray[0], 1);
		WebElement eligibletstatus = DriverFactory.getdriver()
				.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]"));
		Helpers.WaitforvisibilityofElement(eligibletstatus, 10);
		eligibletstatus.sendKeys(testdata.get(rowno).get("Eligible Status"));
		PIMAddemppage.selectdropdownvaluebytextprovided("Issued By", testdata.get(rowno).get("Issued By"));
	}
	
	@And("fill the job details from {int}")
	public void fill_the_job_details_from(int rowno) throws Exception, IOException {
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
		String joineddate = testdata.get(rowno).get("Issued Date"); // DD-MM-YYYY
		String[] joineddatearray = joineddate.split("-");
		String month = Helpers.mapmonthwithgivennum(joineddatearray[1]);
		System.out.println(month);
		PIMAddemppage.selectdatefromcalendar(joineddatearray[2], month, joineddatearray[0], 1);
		PIMAddemppage.selectdropdownvaluebytextprovided("Job Title", testdata.get(rowno).get("Job Title"));
		PIMAddemppage.selectdropdownvaluebytextprovided("Job Category", testdata.get(rowno).get("Job Category"));
		PIMAddemppage.selectdropdownvaluebytextprovided("Sub Unit", testdata.get(rowno).get("Sub Unit"));
		PIMAddemppage.selectdropdownvaluebytextprovided("Location", testdata.get(rowno).get("Location"));
		PIMAddemppage.selectdropdownvaluebytextprovided("Employment Status", testdata.get(rowno).get("Employment Status"));
	}
	
	@And("Add and fill the salary details from {int}")
	public void Add_and_fill_the_salary_details_from(int rowno) throws Exception {
		try {
			List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);
			PIMAddemppage.clickonAddbyindex(1);
			PIMAddemppage.enterNameEmailTNandAddreess("Salary Component", 1, testdata.get(rowno).get("Salary Component"));
			PIMAddemppage.selectdropdownvaluebytextprovided("Pay Grade", testdata.get(rowno).get("Pay Grade"));
			PIMAddemppage.selectdropdownvaluebytextprovided("Pay Frequency", testdata.get(rowno).get("Pay Frequency"));
			PIMAddemppage.selectdropdownvaluebytextprovided("Currency", testdata.get(rowno).get("Currency"));
			PIMAddemppage.enterNameEmailTNandAddreess("Amount", 1, testdata.get(rowno).get("Sal_Amount"));
			
			String status = testdata.get(rowno).get("Include Direct Deposit Details");
			if(status.equalsIgnoreCase("Yes")) {
				WebElement enablebtn = DriverFactory.getdriver().findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']"));
				enablebtn.click();
				Thread.sleep(1000);
				if(testdata.get(rowno).get("Account Number").equals("Random")) {
					PIMAddemppage.enterNameEmailTNandAddreess("Account Number", 1, Helpers.generateRandomnumbygivenlength(8));				
				}
				else {
					PIMAddemppage.enterNameEmailTNandAddreess("Account Number", 1, testdata.get(rowno).get("Account Number"));	
				}
				PIMAddemppage.selectdropdownvaluebytextprovided("Account Type", testdata.get(rowno).get("Account Type"));
				if(testdata.get(rowno).get("Routing Number").equals("Random")) {
					PIMAddemppage.enterNameEmailTNandAddreess("Routing Number", 2, Helpers.generateRandomnumbygivenlength(5));				
				}
				else {
					PIMAddemppage.enterNameEmailTNandAddreess("Routing Number", 2, testdata.get(rowno).get("Routing Number"));	
				}
				PIMAddemppage.enterNameEmailTNandAddreess("Deposit Amount", 3, testdata.get(rowno).get("Deposit Amount"));		
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@And("Add and fill the work experience, education  details, skills, languages from {int}")
	public void Add_and_fill_the_work_experience_education_details_skills_languages_from(int rowno) throws Exception, IOException {
		List<Map<String,String>> testdata = excelreader.getData(".//Testdata//Testdata_Addemp.xlsx", sheetname);

			String workexp = testdata.get(rowno).get("Noofworkexperience");
			int noofworkexper = Integer.parseInt(workexp);
			System.out.println(noofworkexper);
			int k = rowno;
			for(int i=1;i<=noofworkexper;i++) {
				PIMAddemppage.clickonAddbyindex(1);
				PIMAddemppage.enterNameEmailTNandAddreess("Company", 1, testdata.get(k).get("Company"));
				PIMAddemppage.enterNameEmailTNandAddreess("Job Title", 2, testdata.get(k).get("Job Title"));
				String jobFrom = testdata.get(rowno).get("Job From"); // Job from
				String[] jobFromarray = jobFrom.split("-");
				String month = Helpers.mapmonthwithgivennum(jobFromarray[1]);
				System.out.println(month);
				PIMAddemppage.selectdatefromcalendar(jobFromarray[2], month, jobFromarray[0], 1);
				String jobTo = testdata.get(rowno).get("Job to"); // Job to
				String[] jobtoarray = jobTo.split("-");
				String month1 = Helpers.mapmonthwithgivennum(jobtoarray[1]);
				System.out.println(month1);
				PIMAddemppage.selectdatefromcalendar(jobtoarray[2], month1, jobtoarray[0], 2);
				PIMAddemppage.ClickonSavebyindexno("save1");
				Thread.sleep(5000);
				k++;
			}

			String education = testdata.get(rowno).get("Noofeducationdetails");
			int Noofeducation = Integer.parseInt(education);
			System.out.println(Noofeducation);
			int m = rowno;
			for(int i=1;i<=Noofeducation;i++) {
				PIMAddemppage.clickonAddbyindex(2);
				Thread.sleep(2000);
				PIMAddemppage.selectdropdownvaluebytextprovided("Level", testdata.get(m).get("Level"));
				PIMAddemppage.enterNameEmailTNandAddreess("Institute", 1, testdata.get(m).get("Institute"));
				PIMAddemppage.enterNameEmailTNandAddreess("Major/Specialization", 2, testdata.get(m).get("Major/Specialization"));
				PIMAddemppage.enterNameEmailTNandAddreess("Year", 3, testdata.get(m).get("Year"));
				PIMAddemppage.enterNameEmailTNandAddreess("GPA/Score", 4, testdata.get(m).get("GPA/Score"));
				String startdate = testdata.get(m).get("Start Date"); // Job from
				String[] startdatearray = startdate.split("-");
				String month = Helpers.mapmonthwithgivennum(startdatearray[1]);
				System.out.println(month);
				PIMAddemppage.selectdatefromcalendar(startdatearray[2], month, startdatearray[0], 1);
				String enddate = testdata.get(m).get("End Date"); // Job from
				String[] enddatearray = enddate.split("-");
				String month1 = Helpers.mapmonthwithgivennum(enddatearray[1]);
				System.out.println(month1);
				PIMAddemppage.selectdatefromcalendar(enddatearray[2], month1, enddatearray[0], 2);
				PIMAddemppage.ClickonSavebyindexno("save1");
				Thread.sleep(5000);
				
				ArrayList<String> educat_det_passed = new ArrayList<>(Arrays.asList(
																		testdata.get(m).get("Level"),
																		testdata.get(m).get("Year"),
																		testdata.get(m).get("GPA/Score")));
				System.out.println("Passed data: " + educat_det_passed);
				
				ArrayList<String> educfromTable = new ArrayList<>();

				for (int j = 2; j <= 4; j++) {
					WebElement elem = DriverFactory.getdriver().findElement(By.xpath("((//div[@class='oxd-table-body'])[2]//div[@class='oxd-table-card']["+i+"]//div[@role='cell'])["+j+"]"));
					String value = elem.getText();
					educfromTable.add(value);
				}

				System.out.println("Data from Table : " + educfromTable);
				if (educat_det_passed.containsAll(educfromTable)) {
					System.out.println("Education : Passed and captured data are same...");
				} else {
					System.out.println("Education: Passed and captured data are DIFFERENT...");
				}
				educat_det_passed.clear();
				educfromTable.clear();
				m++;
			}

			String skill = testdata.get(rowno).get("Noofskilldetails");
			int noofskill = Integer.parseInt(skill);
			int n=rowno;
			for(int i=1;i<=noofskill;i++) {
				PIMAddemppage.clickonAddbyindex(3);
				PIMAddemppage.selectdropdownvaluebytextprovided("Skill", testdata.get(n).get("Skill"));
				PIMAddemppage.enterNameEmailTNandAddreess("Years of Experience", 1, testdata.get(n).get("Years of Experience"));
				PIMAddemppage.ClickonSavebyindexno("save1");
				
				HashMap<String, String> hmp1 = new HashMap<>();
				hmp1.put(testdata.get(n).get("Skill"), testdata.get(n).get("Years of Experience"));
				System.out.println("Passed skill data => " + hmp1);
				Thread.sleep(5000);
				HashMap<String, String> hmp2 = new HashMap<>();
				String skillfromtable = null;
				String yearsfromtable = null;
				for (int j = 2; j <= 3; j++) {
					WebElement elem = DriverFactory.getdriver().findElement(By.xpath("(((//div[@class='oxd-table-body'])[3]//div[@class='oxd-table-card'])["
									+ i + "]//div[@role='cell'])[" + j + "]"));
					if (j == 2) {
						skillfromtable = elem.getText();
					} else {
						yearsfromtable = elem.getText();
					}
					hmp2.put(skillfromtable, yearsfromtable);
				}
				System.out.println(" Retrieved skill data from table = " + hmp2);

				if (hmp1.equals(hmp2)) {
					System.out.println("Passed and captured data of skill are SAME...");
				} else {
					System.out.println("Passed and captured data of skill are DIFFERENT...");
				}
				hmp1.clear();
				hmp2.clear();
				System.out.println(hmp1);
				System.out.println(hmp2);
				n++;
			}
			
	}
}
