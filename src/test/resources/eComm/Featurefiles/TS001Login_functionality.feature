@Loginpagetest @Smoke @Regression @TS001
Feature: Login page functionalities test

#@High
@Loginpagetest_TC001 @Sample
Scenario: Validate Login page title

Given User is on login page
When User gets the title of the page
Then Page title should be "OrangeHRM"

#@Low
@Loginpagetest_TC002	
Scenario: Validate Login with valid credentials

Given User is on login page
When User enters the Username as "Admin"
And User enters password as "admin123"
And User clicks on Login button
And User gets the title of the page
Then Page title should be "OrangeHRM"

#@Medium
@Loginpagetest_TC003
Scenario: Validate Login with invalid credentials

Given User is on login page
When User enters the Username as "Admin"
And User enters password as "admin456"
And User clicks on Login button
Then Login Page validation should contain "Invalid credentials"

#@Low
@Loginpagetest_TC004
Scenario: Validate logging into app by capturing username and password from loginpage

Given User is on login page
When I capture username and password
And I enter same username and password
And User clicks on Login button
And User gets the title of the page
Then Page title should be "OrangeHRM" 

