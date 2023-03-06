Feature: Login Page

  Scenario: Successful Login
    Given login page is open
    When user enters the user ID
    And user enters the password
    And clicks on the login button
    Then success message is displayed in the landing page
