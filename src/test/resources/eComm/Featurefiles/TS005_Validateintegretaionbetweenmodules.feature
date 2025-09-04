@Validateaddedrecruitment @Regression @TS005

Feature: Validate integration between PIM module & my info module

Background: As an admin I have to login into the system
Given I launch url & have to login to system with valid credentials
|Username| Password |
| Admin  | admin123 |

@TS005_TC001
Scenario: To test whether PIM & Myinfo module are integrated
Given I navigate to "PIM" module
When I click on employee list
And I Select employment status as "Full-Time Permanent"
And I click on search
Then employee details should be display in a table
And I Click on employee name
Then I should be redirected to My info module
