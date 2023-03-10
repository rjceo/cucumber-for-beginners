Feature: Login Page

  @positive
  Scenario: Successful Login
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And clicks on the login button
    Then page is redirected to "https://the-internet.herokuapp.com/secure"
    And message "You logged into a secure area!" is displayed

  @positive
  Scenario: Verify labels and fields
    Given login page "https://the-internet.herokuapp.com/login" is open
    Then element xpath "//div/label[@for='username']" is displayed
    And element xpath "//div/label[@for='username']/following-sibling::input[@id='username']" is displayed
    And element xpath "//div/label[@for='password']" is displayed
    And element xpath "//div/label[@for='password']/following-sibling::input[@id='password']" is displayed
    And element xpath "//button[@type='submit']" is displayed

  @negative @username
  Scenario: Verify user is not able to login with an incorrect username
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith_incorrect" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And clicks on the login button
    Then element xpath "//div[@id='flash'][contains(text(),'Your username is invalid!')]" is displayed
    And page remains at "https://the-internet.herokuapp.com/login"

  @negative @password
  Scenario: Verify user is not able to login with an incorrect password
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "INCORRECT_PASSWORD" as the Password
    And clicks on the login button
    Then element xpath "//div[@id='flash'][contains(text(),'Your password is invalid!')]" is displayed
    And page remains at "https://the-internet.herokuapp.com/login"


