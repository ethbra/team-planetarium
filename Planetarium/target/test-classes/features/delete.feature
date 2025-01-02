@US5
Feature: Deleting Moons/Planets

  Background:
    Given user on Home page
    And user is logged in

  Scenario Outline: Delete Planet
      When user selects "<body-type>" option
      And user enters "<name>"
      And user presses delete
      Then browser alert says "<alert>"
      And table reflects deletion by "<alert>"


      Examples:
      |body-type        |name                     |alert                |
      |planet           |Mars                     |                     |
      |planet           |Proxima B                |Invalid planet name  |
      |moon             |Luna                     |                     |
      |moon             |Non Est                  |Invalid moon name    |


    Scenario: Moons removed on planet delete
      When user selects planet option
      When user enters "Mars"
      And user presses delete
      Then "Titan" is deleted