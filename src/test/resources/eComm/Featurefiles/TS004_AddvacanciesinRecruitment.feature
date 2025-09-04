@Recruitmentpagetest @Regression @TS004

Feature: Validate new vacancies addition to recruitment

Background: As an admin I have to login into the system
Given I launch url & have to login to system with valid credentials
|Username| Password |
| Admin  | admin123 |

@TS004_TC001
Scenario Outline:  Validate whether user able to add vacancies in recruitment module
Given I navigate to "Recruitment" module
When I click on vacancies
And I click on Add
And add required details from "<Sheetname>" & <RowNo>

Examples:
|   Sheetname   |RowNo|
|  Addvacancies |  0  |
