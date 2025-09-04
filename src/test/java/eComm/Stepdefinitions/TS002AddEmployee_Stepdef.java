package eComm.Stepdefinitions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import eComm.Drivermanager.DriverFactory;
import eComm.Pageobjects.Helpers;
import eComm.Pageobjects.Homepage;
import eComm.Pageobjects.PIMAddemployeepage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class TS002AddEmployee_Stepdef {

	private Homepage homepage = new Homepage(DriverFactory.getdriver());
	private PIMAddemployeepage PIMAddemppage = new PIMAddemployeepage(DriverFactory.getdriver());

	private DriverFactory driverfactory;
	private WebDriver driver;

	@When("I navigate to {string} module")
	public void i_navigate_to_pim_module(String module) throws InterruptedException {
		homepage.clickonmodulename(module);
	}

	@When("I click on Add employee")
	public void i_click_on_add_employee() throws Exception {
		homepage.clickonelembytext("Add Employee");
		Thread.sleep(1000);

	}

	@And("Upload profile picture")
	public void Uploadprofilepicture() throws Exception {
		PIMAddemppage.uploadprofile();

	}

	@When("Enter employee name, employee id & click on save")
	public void enter_employee_name_employee_id_click_on_save() throws Exception {
		PIMAddemppage.entertextbyplaceholder("First Name", "Hunter");
		PIMAddemppage.entertextbyplaceholder("Middle Name", "Mac");
		PIMAddemppage.entertextbyplaceholder("Last Name", "John");

		RandomStringUtils random = new RandomStringUtils();
		String id = random.randomAlphanumeric(5);
		System.out.println(id);
		PIMAddemppage.enterempid(id);
		PIMAddemppage.clicksavebtn();

		Thread.sleep(10000);
	}

	@And("Select Nationality, Marital status, DOB & Gender")
	public void select_nationality_marital_status_dob_gender() throws Exception {

		PIMAddemppage.selectdropdownvaluebytextprovided("Nationality", "Maldivan");
		PIMAddemppage.selectdropdownvaluebytextprovided("Marital Status", "Single");
		PIMAddemppage.selectdatefromcalendar("2000", "March", "24", 2);
		PIMAddemppage.selectradio("Male");

		Thread.sleep(5000);
	}

	@Given("Click on {string}")
	public void click_on(String savebtno) throws InterruptedException {
		PIMAddemppage.ClickonSavebyindexno(savebtno);
	}

	@And("Select blood type")
	public void select_blood_type() throws Exception {
		PIMAddemppage.selectdropdownvaluebytextprovided("Blood type", "A+");

		Thread.sleep(2000);
	}

	@And("Adding an attachment")
	public void adding_an_attachment() throws InterruptedException {
		PIMAddemppage.sendattachment();
		Thread.sleep(3000);
	}

	@And("Validate the attached file in table displayed")
	public void Validatetheattachedfileintabledisplayed() {
		PIMAddemppage.validateuploadedfilename();
	}

	@And("move to {string} section")
	public void movetosection(String sectionname) throws InterruptedException {
		PIMAddemppage.navigatetogivensection(sectionname);
//		Thread.sleep(3000);
	}

	@Given("enter address, telephone and email details")
	public void enter_address_telephone_and_email_details() throws InterruptedException {
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 1, "Test 1");
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 2, "Test 2");
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 3, "Test 3");
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 4, "Test 4");
		PIMAddemppage.enterNameEmailTNandAddreess("Address", 5, "Test 5");
		PIMAddemppage.selectdropdownvaluebytextprovided("Country", "Maldives");
		String homeno = Helpers.generateRandomnum();
		String mobileno = Helpers.generateRandomnum();
		String workno = Helpers.generateRandomnum();
		PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 1, homeno);
		PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 2, mobileno);
		PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 3, workno);
		String workemail = "Test" + Helpers.generateRandomString() + "@gmail.com";
		String otheremail = "Test" + Helpers.generateRandomString() + "@gmail.com";
		PIMAddemppage.enterNameEmailTNandAddreess("Email", 1, workemail);
		PIMAddemppage.enterNameEmailTNandAddreess("Email", 2, otheremail);
		Thread.sleep(2000);
	}

	@Given("Add and enter {int} emergency contact details")
	public void add_and_enter_emergency_contact_details(int noofcontactstoadd) throws InterruptedException {
		for (int i = 1; i <= noofcontactstoadd; i++) {
			PIMAddemppage.clickonAddbyindex(1);
			String name = Helpers.generateRandomString();
			String relation = Helpers.generateRandomString();
			PIMAddemppage.enterNameEmailTNandAddreess("Save Emergency Contact - Name & rela", 1, name);
			PIMAddemppage.enterNameEmailTNandAddreess("Save Emergency Contact - Name & rela", 2, relation);
			String homeno = Helpers.generateRandomnum();
			String mobileno = Helpers.generateRandomnum();
			String workno = Helpers.generateRandomnum();
			PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 1, homeno);
			PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 2, mobileno);
			PIMAddemppage.enterNameEmailTNandAddreess("Telephone", 3, workno);
			PIMAddemppage.ClickonSavebyindexno("save1");
			Thread.sleep(5000);
			ArrayList<String> arraylist1 = new ArrayList<>(Arrays.asList(name, relation, homeno, mobileno, workno));
			System.out.println("Actual data passed for emergency contact " + i + " is " + arraylist1);

//			WebElement firstrow = driver.findElement(By.xpath("(//div[@class='oxd-table-body']//div[@class='oxd-table-card'])[1]"));

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

			// Name 1
			// (//div[@class='oxd-table-body']//div[@class='oxd-table-card']//div[@role='cell'])[2];
		}
	}

	@And("Add and enter {int} dependent details")
	public void Add_and_enter_dependent_details(int noofdeptoadd) throws Exception {
		for (int i = 0; i < noofdeptoadd; i++) {
			PIMAddemppage.clickonAddbyindex(1);
			String name = Helpers.generateRandomString();
			Random random = new Random();
			int dategen = random.nextInt(31);
			String date = String.valueOf(dategen);
			System.out.println("Generated date = " + dategen);
			PIMAddemppage.enterNameEmailTNandAddreess("Name", 1, name);
			PIMAddemppage.selectdropdownvaluebytextprovided("Relationship", "Child");
			PIMAddemppage.selectdatefromcalendar("2018", "March", date, 1);
			PIMAddemppage.ClickonSavebyindexno("save1");
			Thread.sleep(5000);
			Helpers.Scrollupbyjs();
		}
	}

	@And("Add and fill the document {string} details")
	public void Add_and_fill_the_document_details(String documenttype) throws Exception {
		PIMAddemppage.clickonAddbyindex(1);
		if (documenttype.equalsIgnoreCase("Passport")) {
			PIMAddemppage.selectradio(documenttype);
		} else if (documenttype.equalsIgnoreCase("Visa")) {
			PIMAddemppage.selectradio(documenttype);
		}
		String gen_num = Helpers.generateRandomnum();
		WebElement ele = DriverFactory.getdriver()
				.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		ele.sendKeys(gen_num);
		String date = Helpers.gendatenum();
		PIMAddemppage.selectdatefromcalendar("2018", "March", date, 1); // Issued date
		Helpers.Scrollupbyjs();
//		WebElement expirydate = DriverFactory.getdriver().findElement(By.xpath("(//div[@class='oxd-date-wrapper'])[2]"));
//		expirydate.click();
//		Thread.sleep(1000);
//		expirydate.sendKeys("2028-29-08");
		String str_gen = Helpers.generateRandomString();
		WebElement eligibletstatus = DriverFactory.getdriver()
				.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]"));
		Helpers.WaitforvisibilityofElement(eligibletstatus, 10);
		eligibletstatus.sendKeys(str_gen);
		PIMAddemppage.selectdropdownvaluebytextprovided("Issued By", "Maldives");
	}

	@And("fill the job details")
	public void fill_the_job_details() throws Exception {
		String date = Helpers.gendatenum();
		PIMAddemppage.selectdatefromcalendar("2024", "December", "2", 1);
		PIMAddemppage.selectdropdownvaluebytextprovided("Job Title", "Account Assistant");
		PIMAddemppage.selectdropdownvaluebytextprovided("Job Category", "Professionals");
		PIMAddemppage.selectdropdownvaluebytextprovided("Sub Unit", "Client Service");
		PIMAddemppage.selectdropdownvaluebytextprovided("Location", "HQ - CA, USA");
		PIMAddemppage.selectdropdownvaluebytextprovided("Employment Status", "Full-Time Permanent");
	}

	@And("Add and fill the salary details")
	public void Add_and_fill_the_salary_details() throws Exception {
		PIMAddemppage.clickonAddbyindex(1);
		PIMAddemppage.enterNameEmailTNandAddreess("Salary Component", 1, "Monthly Pay");
		PIMAddemppage.selectdropdownvaluebytextprovided("Pay Grade", "Grade 1");
		PIMAddemppage.selectdropdownvaluebytextprovided("Pay Frequency", "Monthly");
		PIMAddemppage.selectdropdownvaluebytextprovided("Currency", "United States Dollar");
		PIMAddemppage.enterNameEmailTNandAddreess("Amount", 1, "50000");
	}

	@And("Add and fill the supervisor and subordinate details")
	public void Add_and_fill_the_supervisor_and_subordinate_details() throws InterruptedException {
		PIMAddemppage.clickonAddbyindex(1);
		PIMAddemppage.enterNameEmailTNandAddreess("Name", 1, "John");
		Thread.sleep(3000);
		WebElement nameList = DriverFactory.getdriver()
				.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		nameList.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		PIMAddemppage.selectdropdownvaluebytextprovided("Reporting Method", "Direct");
		PIMAddemppage.ClickonSavebyindexno("save1");
		Thread.sleep(3000);
		PIMAddemppage.clickonAddbyindex(2);
		Helpers.Scrolldownbyjs("200");
		PIMAddemppage.enterNameEmailTNandAddreess("Name", 1, "Mac");
		Thread.sleep(2000);
		WebElement nameList1 = DriverFactory.getdriver()
				.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		nameList1.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		PIMAddemppage.selectdropdownvaluebytextprovided("Reporting Method", "Indirect");
		PIMAddemppage.ClickonSavebyindexno("save1");
	}

	@And("Add and fill the {int} work experience, {int} education  details, {int} skills, {int} languages")
	public void Add_and_fill_the_work_experience_details(int workexp, int educ, int skillcnt, int langcnt)
			throws Exception {
		PIMAddemppage.clickonAddbyindex(1);
		PIMAddemppage.enterNameEmailTNandAddreess("Company", 1, "ABC Enterprises");
		PIMAddemppage.enterNameEmailTNandAddreess("Job Title", 2, "Quality Engineer");
		PIMAddemppage.selectdatefromcalendar("2022", "April", "20", 1);
		PIMAddemppage.selectdatefromcalendar("2024", "December", "10", 2);
		PIMAddemppage.ClickonSavebyindexno("save1");
		String[] level = { "", "College Undergraduate", "High School Diploma" };
		String[] specialization = { "", "Mechanical", "Manufacturing" };
		String[] year = { "", "2021", "2019" };
		for (int i = 1; i <= educ; i++) {
			PIMAddemppage.clickonAddbyindex(2);
			Thread.sleep(2000);
			PIMAddemppage.selectdropdownvaluebytextprovided("Level", level[i]);
			System.out.println(level[i]);
			String inst = Helpers.generateRandomString();
			PIMAddemppage.enterNameEmailTNandAddreess("Institute", 1, inst);
			PIMAddemppage.enterNameEmailTNandAddreess("Major/Specialization", 2, specialization[i]);
			PIMAddemppage.enterNameEmailTNandAddreess("Year", 3, year[i]);
			String gpa = Helpers.customizenumber_gen(10);
			PIMAddemppage.enterNameEmailTNandAddreess("GPA/Score", 4, gpa);
			PIMAddemppage.selectdatefromcalendar("2016", "March", "14", 1);
			PIMAddemppage.selectdatefromcalendar("2020", "April", "21", 2);
			PIMAddemppage.ClickonSavebyindexno("save1");
			Thread.sleep(5000);
			ArrayList<String> educat_det_passed = new ArrayList<>(Arrays.asList(level[i], year[i], gpa));
			System.out.println("Passed: " + educat_det_passed);
			ArrayList<String> educfromTable = new ArrayList<>();

			for (int j = 2; j <= 4; j++) {
				WebElement elem = DriverFactory.getdriver()
						.findElement(By.xpath("((//div[@class='oxd-table-body']//div[@class='oxd-table-card'])[" + i
								+ " + 1]//div[@role='cell'])[" + j + "]"));
				String value = elem.getText();
				educfromTable.add(value);
			}

			System.out.println("From Table : " + educfromTable);
			if (educat_det_passed.containsAll(educfromTable)) {
				System.out.println("Education  : Passed and captured data are same...");
			} else {
				System.out.println("Education: Passed and captured data are DIFFERENT...");
			}
		}
		PIMAddemppage.clickonAddbyindex(3);
		String skill[] = { "", "Java", "JavaScript", "Seleniumwebdriver", "SQL", "Swift", "UI/UX Design" };
		for (int i = 1; i <= skillcnt; i++) {
			PIMAddemppage.clickonAddbyindex(3);
			PIMAddemppage.selectdropdownvaluebytextprovided("Skill", skill[i]);
			String yearsofexp = Helpers.customizenumber_gen(5);
			PIMAddemppage.enterNameEmailTNandAddreess("Years of Experience", 1, yearsofexp);
			PIMAddemppage.ClickonSavebyindexno("save1");
			HashMap<String, String> hmp1 = new HashMap<>();
			hmp1.put(skill[i], yearsofexp);
			System.out.println("Passed skill data => " + hmp1);
			Thread.sleep(5000);
			HashMap<String, String> hmp2 = new HashMap<>();
			String skillfromtable = null;
			String yearsfromtable = null;
			for (int j = 2; j <= 3; j++) {
				WebElement elem = DriverFactory.getdriver()
						.findElement(By.xpath("(((//div[@class='oxd-table-body'])[3]//div[@class='oxd-table-card'])["
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
		}

		for (int i = 1; i <= langcnt; i++) {
			PIMAddemppage.clickonAddbyindex(4);
			String langs[] = { "", "Arabic", "Chinese", "English", "French", "Russian" };
			String fluency[] = { "Writing", "Speaking", "Reading" };
			String competency[] = { "Poor", "Basic", "Good", "Mother Tongue" };
			PIMAddemppage.selectdropdownvaluebytextprovided("Language", langs[i]);
			Random random = new Random();
			int random_fluency = random.nextInt(fluency.length);
			PIMAddemppage.selectdropdownvaluebytextprovided("Fluency", fluency[random_fluency]);
			Random random1 = new Random();
			int random_competency = random1.nextInt(competency.length);
			PIMAddemppage.selectdropdownvaluebytextprovided("Competency", competency[random_competency]);
			ArrayList<String> arraylist1 = new ArrayList<>(
					Arrays.asList(langs[i], fluency[random_fluency], competency[random_competency]));
			System.out.println("Passed data >>> " + arraylist1);
			PIMAddemppage.ClickonSavebyindexno("save1");
			Thread.sleep(5000);
			ArrayList<String> arraylist2 = new ArrayList<>();
			for (int j = 2; j <= 4; j++) {
				WebElement elem = DriverFactory.getdriver()
						.findElement(By.xpath("(((//div[@class='oxd-table-body'])[4]//div[@class='oxd-table-card'])["
								+ i + "]//div[@role='cell'])[" + j + "]"));
				String datafromtable = elem.getText();
				arraylist2.add(datafromtable);
			}
			System.out.println(arraylist2);
			if (arraylist1.containsAll(arraylist2)) {
				System.out.println("Passed and captured data of Language are SAME........");
			} else {
				System.out.println("Passed and captured data of Language are DIFFERENT........");
			}
		}
	}

	@And("Add {int} assigned membership details")
	public void Add_assigned_membership_details(int membershipcount) throws Exception {
		for (int i = 1; i <= membershipcount; i++) {
			PIMAddemppage.clickonAddbyindex(1);
			String membership[] = { "", "ACCA", "British Computer Society (BCS)",
					"Chartered Institute of Marketing (CIM)", "CIMA" };
			String subscriptionpaidby[] = { "", "Company", "Individual" };
			String currency[] = { "", "Malaysian Ringgit", "Maldive Rufiyaa", "Myanmar Kyat", "Omani Rial",
					"Qatari Rial" };
			PIMAddemppage.selectdropdownvaluebytextprovided("Membership", membership[i]);
			PIMAddemppage.selectdropdownvaluebytextprovided("Subscription Paid By", subscriptionpaidby[i]);
			String subcriptionamount = Helpers.generateRandomnumbygivenlength(5);
			PIMAddemppage.enterNameEmailTNandAddreess("Subscription Amount", 1, subcriptionamount);
			String subsamt_doub = subcriptionamount + ".00";
			PIMAddemppage.selectdropdownvaluebytextprovided("Currency", currency[i]);
			PIMAddemppage.selectdatefromcalendar("2020", "April", "15", 1);
			PIMAddemppage.selectdropdown_Today(2);
			PIMAddemppage.ClickonSavebyindexno("save1");
			Thread.sleep(5000);
			Helpers.Scrollupbyjs();

			HashMap<String, List<String>> hmp1 = new HashMap<>();
			hmp1.put(membership[i], Arrays.asList(subscriptionpaidby[i], subsamt_doub, currency[i]));
			System.out.println(hmp1);

			HashMap<String, List<String>> hmp2 = new HashMap<>();
			ArrayList<String> al = new ArrayList<>();
			String membershipfromtable = null;
			for (int j = 2; j <= 5; j++) {
				WebElement elem = DriverFactory.getdriver()
						.findElement(By.xpath("(((//div[@class='oxd-table-body'])[1]//div[@class='oxd-table-card'])["
								+ i + "]//div[@role='cell'])[" + j + "]"));
				if (j == 2) {
					membershipfromtable = elem.getText();
				} else {
					al.add(elem.getText());
				}
				hmp2.put(membershipfromtable, al);
			}
			System.out.println(hmp2);

			if (hmp1.equals(hmp2)) {
				System.out.println("Membership data passed and captured from table are SAME.....");
			} else {
				System.out.println("Membership data passed and captured from table are DIFFERENT.....");
			}
			hmp1.clear();
			hmp2.clear();

		}

	}
}
