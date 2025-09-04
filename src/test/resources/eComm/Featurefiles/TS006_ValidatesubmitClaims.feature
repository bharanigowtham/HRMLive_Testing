@Regression @TS006
Feature: Submit new claim

Background: As an admin I have to login into the system
Given I launch url & have to login to system with valid credentials
|Username| Password |
| Admin  | admin123 |

@TS006_TC001
Scenario Outline: Validate user able to submit the claim
Given I navigate to "Claim" module 
When I click on "Submit Claim"
And I create claim request from "<Sheetname>" & <RowNo>
And Add expense details from "<Sheetname>" & <RowNo>
Then validate the expense details & consolidate expense amount from "<Sheetname>" & <RowNo>
And click on submit

Examples:
|Sheetname   | RowNo |
|SubmitClaims|   0    |


#@TS006_TC002
#Scenario: Validate user able to verify the details of the submitted claim
#Given I navigate to "Claim" module
#And I click on "Employee Claims"
#And Enter ref ID
#And Click on search
#Then Claims details should be displayed in table & validate the details of the same



