Feature: Checkout from the Shopping Cart on SauceDemo

  @Regression @Positive
  Scenario: Complete checkout from the shopping cart
    Given I have items in the shopping cart
    When I proceed to checkout
    And I provide shipping and payment information
    Then the checkout process should be successful