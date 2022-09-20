Feature: Menu Management

  Background: Setup Restaurant menu
    Given I have a menu item with name "Milk tea" and price $5
    When I add this menu item
    Then Menu item with name "Milk tea" should be added

  @SmokeTest
  Scenario: Add a menu item
    Given I have a menu item with name "Cucumber Sandwich" and price $20
    When I add this menu item
    Then Menu item with name "Cucumber Sandwich" should be added

  @RegularTest
  Scenario: Add another menu item
    Given I have a menu item with name "Cucumber Salad" and price $10
    When I add this menu item
    Then Menu item with name "Cucumber Salad" should be added

  @UAT
  @RegularTest
  Scenario: Add duplicate menu item
    Given I have a menu item with name "Milk tea" and price $5
    When I add this menu item
    Then Error message with value "Duplicate item"