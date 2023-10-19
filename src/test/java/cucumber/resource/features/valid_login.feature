Feature: Valid Login to SauceDemo

  @Regression @Positive
  Scenario: Login with valid credentials
    Given User on the SauceDemo login page
    When User enter username
    And User enter valid password
    And User click the "Login" button
    Then User should be logged in successfully