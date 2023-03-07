Feature: Login Page

  @positive
  Scenario: Successful Login
    Given login page "https://the-internet.herokuapp.com/login" is open
    When user enters "tomsmith" as the Username
    And user enters "SuperSecretPassword!" as the Password
    And clicks on the login button
    Then page is redirected to "https://the-internet.herokuapp.com/secure"
    And message "You logged into a secure area!" is displayed

#  Scenario Outline: <sn> Outline Example
#    Given example
#    When <a>
#    And  <b>
#    Then ex
#
#    Examples:
#      | sn | a        | b        |
#      | 01 | asdfsf   | asdfasdf |
#      | 02 | asdfasdf | asdfas   |