@TS007
Feature: Take screenshot on pages

Background: As an admin I have to login into the system
Given I launch url & have to login to system with valid credentials
|Username| Password |
| Admin  | admin123 |

@TS007_TC001
Scenario Outline: Capture screenshot on page for each modules
Given I navigate to "<Modulename>"
Then I have to capture screeenshots of the pages for the "<Modulename>"

Examples:
|  Modulename |
|  PIM        |
#|  Leave      |
#|  My Info 		|
#|  Performance|




