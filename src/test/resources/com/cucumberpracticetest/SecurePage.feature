@securepage
Feature: Secure Page

  @positive @secure
  Scenario: Verify fields in the Secure Page
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And clicks on the login button
    When page is redirected to "https://the-internet.herokuapp.com/secure"
    Then message "You logged into a secure area!" is displayed
    And the header "Secure Area" is displayed
    And the sub-header "Welcome to the Secure Area. When you are done click logout below." is displayed
    And the logout button is displayed
    Then clicks on the logout button

  @positive @logout
  Scenario: Verify logout button
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And clicks on the login button
    When clicks on the logout button
    Then page is redirected to "https://the-internet.herokuapp.com/login"
