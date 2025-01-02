@US4
Feature: Add Moons/Planets to Home Page

  Background:
    Given user logs in

  Scenario Outline: Adding Planets
    When user selects planet option
    And user enters planet "<planet_name>"
    And user enters file "<file>"
    And user clicks to submit
    Then browser alert says "<alert>"
    And table reflects "<alert>"

    ## TODO: change file to append root directory instead of full path in here
    Examples:
      | planet_name                        | file                                                                                                         | alert                       |
      | My 1st _Planet-                    | moon-1.jpg      |                             |
      | ThisNameIsOverThirtyCharactersLong |                                                                                                              | Invalid planet name         |
      |                                    |                                                                                                              | Invalid planet name         |
      | Pr()xim@ Centaur! B                |                                                                                                              | Invalid planet name         |
      | Earth                              |                                                                                                              | Invalid planet name         |
      | My 2nd _Planet-                    |                                                                                                              |                             |
      | My 3rd _Planet-                    | rick-roll-rick-astley.gif| Invalid File Type  |
      | My 4th _Planet-                    | gearth.png                |                    |
  Scenario Outline: Adding Moons
    When user selects moon option
    And user enters moon "<moon_name>"
    And user enters owner planet "<planet_id>"
    And user clicks to submit
    Then browser alert says "<alert>"
    And table reflects "<alert>"


    Examples:
      | moon_name                        | planet_id | alert                     |
      | My 4th_Moon-                     | 1         |                           |
      |                                  | 1         | Invalid moon name         |
      | ThisMoonHasTooManyCharactersHere | 1         | Invalid moon name         |
      | 404 M**n N*t F()und!             | 1         | Invalid moon name         |
      | Luna                             | 1         | Invalid moon name         |
      | My 5th_Moon-                     | -99999    | Invalid planet ID         |

  Scenario Outline: Adding Moons With File
    When user enters moon "<moon_name>"
    And user enters owner planet "<planet_id>"
    And user enters file "<file>"
    Then browser alert says "<alert>"
    And table reflects "<alert>"

    Examples:
      | moon_name    | planet_id | file                                                                                                                         | alert             |
      | My 6th_Moon- | 1         | rick-roll-rick-astley.gif       | Invalid File Type |
