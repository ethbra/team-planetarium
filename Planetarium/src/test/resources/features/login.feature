@US2
@excludeReset
Feature: Logging in

  Background:
    Given user is on Login page

  Scenario Outline: Successful login attempt
    When user provides username "<username>"
    And user provides password "<password>"
    And user submits their inputs
    Then user is sent to Home page

    Examples:
      | username | password        |
      | Batman   | Iamthenight1939 |


  Scenario Outline: Failed login attempt
    When user provides username "<username>"
    And user provides password "<password>"
    And user submits their inputs
    Then user receives browser alert "<alert>"
    And user stays on Login page

    Examples:
      | username        | password        | alert               |
      | Batman          | WeKillTheBatman | Invalid credentials |
      | Joker           | ImSuperEvil123  | Invalid credentials |
      | WeKillTheBatman | Iamthenight1939 | Invalid credentials |
