Feature: Data table
  Scenario: Calculate bill
    Given Buy following items
      |Item name        |Quantity|Price|
      |Cucumber Sandwich|2       |20   |
    When Calculate bill
    Then a bill of $40 should be generated

  Scenario: Calculate bill with multiple items
    Given Buy following items
      |Item name        |Quantity|Price|
      |Cucumber Sandwich|2       |20   |
      |Milk tea         |1       |5    |
      |Cucumber Salad   |1       |10   |
    When Calculate bill
    Then a bill of $55 should be generated