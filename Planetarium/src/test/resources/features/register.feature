@US1
Feature: User Registration

  Background:
    Given user is on Login page
    When user clicks register
    Then they are sent to the Register page

  Scenario Outline: Successful Registration
    When user provides username "<username>"
    And user provides password "<password>"
    And user submits their inputs
    Then user receives browser alert "<alert>"
    And user is sent back to the Login page

    Examples:
      | username       | password         | alert              |
      | Super_man-2001 | Krypton-was_2000 | Account created successfully |


  Scenario Outline: Failed
    When user provides username "<username>"
    And user provides password "<password>"
    And user submits their inputs
    Then user receives browser alert "<alert>"
    And user stays on the Registration page

    Examples:
      | username                        | password                        | alert            |
      | Batman                          | Krypton-was_2000                | Invalid username |
      | Bane                            | Krypton-was_2000                | Invalid username |
      | wonder_woman_for_the_DC_theming | Krypton-was_2000                | Invalid username |
      | 2face                           | Krypton-was_2000                | Invalid username |
      | joker!!!!!!?)                   | Krypton-was_2000                | Invalid username |
      | Super_man-2001                  | B4ts                            | Invalid password |
      | Super_man-2001                  | ThisPhraseIsThirtyOneCharacters | Invalid password |
      | Super_man-2001                  | 3atman                          | Invalid password |
      | Super_man-2001                  | AlfredIsTheBestButlerToExist    | Invalid password |
      | Super_man-2001                  | batman1                         | Invalid password |
      | Super_man-2001                  | BATMAN1                         | Invalid password |
      | Super_man-2001                  | Robin                           | Invalid password |