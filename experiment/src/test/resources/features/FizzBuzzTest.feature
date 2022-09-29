Feature: FizzBuzz

  Scenario Outline: Should print normal number
    Given Value is <value>
    When FizzBuzz convert
    Then <expected> is returned

    Examples:
    |value|expected|
    |1    |"1"     |
    |2    |"2"     |
    |98   |"98"    |

  Scenario: Should throw exception if negative number
    Given Value is -1
    When FizzBuzz convert
    Then "IllegalArgumentException" exception thrown
    And Exception message contains "Value should not be a negative number"

  Scenario: Should throw exception if value greater than 100
    Given Value is 101
    When FizzBuzz convert
    Then "IllegalArgumentException" exception thrown
    And Exception message contains "must be less than equals 100"

  Scenario: Should print Fizz
    Given Value is 3
    When FizzBuzz convert
    Then "Fizz" is returned

  Scenario: Should print Buzz
    Given Value is 5
    When FizzBuzz convert
    Then "Buzz" is returned

  Scenario: Should print FizzBuzz
    Given Value is 15
    When FizzBuzz convert
    Then "FizzBuzz" is returned