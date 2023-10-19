Feature: Invalid Login to SauceDemo

  @Regression @Negative
  Scenario: Login with invalid credentials
    Given User on the SauceDemo login page
    When User enter username
    And User enter invalid password
    And User click the "Login" button
    Then User should see an error message