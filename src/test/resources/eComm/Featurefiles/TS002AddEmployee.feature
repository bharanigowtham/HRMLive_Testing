@Smoke @homepagetest @TS002
Feature: Adding new employee to the system

Background: As an admin I have to login into the system
Given I launch url & have to login to system with valid credentials
|Username| Password |
| Admin  | admin123 |

@TS001_TC001
Scenario: Adding new employee to the system - Keyword driven
Given I navigate to PIM module
And I click on Add employee
#And Upload profile picture
And Enter employee name, employee id & click on save
And Select Nationality, Marital status, DOB & Gender
And Click on "save1"
And Select blood type
#And Click on "save2"
#And Adding an attachment
#And Click on "save3"
And Validate the attached file in table displayed
#And move to "contact details" section
And enter address, telephone and email details
#And Click on "save1"					
#And move to "Emergency Contacts" section
#And Add and enter 2 emergency contact details
#And move to "Dependents" section
And Add and enter 3 dependent details
#And move to "Immigration" section
And Add and fill the document "Visa" details
#And Click on "save1"
#And move to "Job" section
And fill the job details
#And Click on "save1"
#And move to "Salary" section
And Add and fill the salary details
#And Click on "save1"
#And move to "Report-to" section
#And Add and fill the supervisor and subordinate details				
#And move to "Qualifications" section
And Add and fill the 1 work experience, 2 education  details, 4 skills, 2 languages
And move to "Memberships" section
And Add 2 assigned membership details

#
#@TS001_TC002
#Scenario Outline: Adding new employee to the system - Data driven
#Given Read the input datas from the Excel
      #| TestData | <TestData> |
      #|sheetName | Addemp     |   
#When I navigate to PIM module
#And I click on Add employee
#And Upload profile picture
#And Enter emp name, emp id & click on save
#And Selecting Nationality, Marital status, DOB & Gender
#And Click on "save1"
#
#Examples:
#| TestData   |
#| Add_emp01 |


















