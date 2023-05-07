@securepage
Feature: Secure Page

  @positive
  Scenario: Verify web elements in the Secure Page
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And element xpath "//button[@type='submit']" is clicked
    When page is redirected to "https://the-internet.herokuapp.com/secure"
    Then message "You logged into a secure area!" is displayed
    And element xpath "//div[@class='example']/h2[contains(text(),'Secure Area')]" is displayed
    And element xpath "//div[@class='example']/h2/following-sibling::h4[contains(text(),'Welcome to the Secure Area. When you are done click logout below.')]" is displayed
    And element xpath "//div[@class='example']/h2/following-sibling::h4/following-sibling::a[@class='button secondary radius']/i[contains(text(),'Logout')]" is displayed

  @positive @logout
  Scenario: Verify logout button
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And element xpath "//button[@type='submit']" is clicked
    When element xpath "//a[@class='button secondary radius'][@href='/logout']" is clicked
    Then page is redirected to "https://the-internet.herokuapp.com/login"
