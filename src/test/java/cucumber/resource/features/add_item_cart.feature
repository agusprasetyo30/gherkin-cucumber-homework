Feature: Shopping Cart on SauceDemo

  Scenario: Add an item to the shopping cart
    Given user logged in to SauceDemo
    When user browse the product catalog
    And user add an item to the shopping cart
    Then the item should appear in the shopping cart