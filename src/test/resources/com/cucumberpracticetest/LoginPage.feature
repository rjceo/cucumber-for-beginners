@loginpage
Feature: Login Page

  @positive @login
  Scenario: Verify user is able to successfully log in
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And clicks on the login button
    Then page is redirected to "https://the-internet.herokuapp.com/secure"
    And message "You logged into a secure area!" is displayed
    And clicks on the logout button

  @positive @fields
  Scenario: Verify labels and fields
    Given login page "https://the-internet.herokuapp.com/login" is open
    Then the label "Username" is displayed
    And the empty textbox below the "Username" is displayed
    Then the label "Password" is displayed
    And the empty textbox below the "Password" is displayed
    Then the login button is displayed

  @negative @username
  Scenario: Verify user is not able to login with an incorrect username
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith_incorrect" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And clicks on the login button
    Then message "Your username is invalid!" is displayed
    And page remains at "https://the-internet.herokuapp.com/login"

  @negative @password
  Scenario: Verify user is not able to login with an incorrect password
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "INCORRECT_PASSWORD" as the Password
    And clicks on the login button
    Then message "Your password is invalid!" is displayed
    And page remains at "https://the-internet.herokuapp.com/login"