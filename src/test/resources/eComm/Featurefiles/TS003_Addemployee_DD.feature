@Smoke @homepagetest1 @Regression @TS003
Feature: Adding new employee to the system

Background: As an admin I have to login into the system
Given I launch url & have to login to system with valid credentials
|Username| Password |
| Admin  | admin123 |

@TS003_TC003
Scenario Outline: Adding new employee to the system - Data driven
  
Given I navigate to "PIM" module
When I click on Add employee
And Upload profile picture
And Enter emp name, emp id from "<Sheetname>" & <RowNo> & click on save 
And Selecting Nationality, Marital status, DOB & Gender from "<Sheetname>" & <RowNo>
And Click on "save1"
And Select given blood type from "<Sheetname>" & <RowNo>
And Click on "save2"
And Adding an attachment
And Click on "save3"
And Validate the attached file in table displayed
And move to "contact details" section
And enter address, telephone and email details from "<Sheetname>" & <RowNo>
And Click on "save1"
And move to "Emergency Contacts" section
And Add and enter emergency contact details from "<Sheetname>" & <RowNo>
And move to "Dependents" section
And Add and enter dependent details from "<Sheetname>" & <RowNo>
And move to "Immigration" section
And Add and enter document details from <RowNo>
And Click on "save1"
And move to "Job" section
And fill the job details from <RowNo> 
And Click on "save1"
And move to "Salary" section
And Add and fill the salary details from <RowNo>
And Click on "save1"				
And move to "Qualifications" section
And Add and fill the work experience, education  details, skills, languages from <RowNo>
And move to "Memberships" section
And Add 2 assigned membership details

Examples:
|Sheetname|RowNo|
|  Addemp |  0  |
#|  Addemp |  3  |
#|  Addemp |  2  |