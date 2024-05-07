Feature: Verify TechCrunch Website

  Background:
    Given I am on the TechCrunch website

  Scenario: Verify latest news section
    Then each news has an author
    And each news has an image
    And close the browser

  Scenario: Verify full news content
    When I click on a news from the latest news list
    Then the browser title is the same as the news title
    And the links within the news content are valid
  #  And close the browser
