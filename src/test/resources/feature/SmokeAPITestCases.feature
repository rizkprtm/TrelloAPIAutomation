@SmokeAPI
Feature: Validation of Rest API test cases for Trello Application

  @TestCase_API
  Scenario: To verify API call functionality for Board , List and Card
    Given Create a board through API call
    And Get board to verify through API call
    When Create ToDo "To-Do" list for the board through API call
    And Create Inprogress "Inprogress" list for the board through API call
    And Create Completed "Completed" list for the board through API call
    And Create In Testing "In Testing" list for the board through API call
    And Create Done "Done" list for the board through API call
    And Create Deployed "Deployed" list for the board through API call
    When Create a card for the list through API call
    And Get to-do card to verify through API call
    Then Move cards from todo to inprogress
    And Get inprogress card to verify through API call
    Then Move cards from inprogress to completed
    And Get completed card to verify through API call
    Then Move cards from completed to in testing
    And Get in testing card to verify through API call
    Then Move cards from in testing to done
    And Get done card to verify through API call
    Then Move cards from done to deployed
    And Get deployed card to verify through API call
