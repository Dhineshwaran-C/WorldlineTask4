Feature: Login Action
    Description: This feature will test a LogIn and LogOut functionality

    Scenario: Login with valid Credentials
        Given User is on Home Page
        When User navigate to Login Page
        Then User enters username and password <userIndex>
        And User should get logged in
        Then Message displayed Login Successfully or not
        
    Examples:
        | userIndex |
        | 1 |
        | 2 |