Feature: NHS Jobs Search Functionality

  As a jobseeker
  I want to search for jobs on NHS Jobs
  So that I can find relevant opportunities

  Scenario: Search with valid keyword and location
    Given I am on the NHS Jobs search page
    When I enter "Nurse" in the keyword field
    And I enter "London" in the location field
    And I click the search button
    And I sort the results by "Date Posted (newest)"
    Then I should see job results related to "Nurse" in "London"

  Scenario: Search with empty keyword and location
    Given I am on the NHS Jobs search page
    When I leave the keyword and location fields empty
    And I click the search button
    Then I should see all results

  Scenario: Search with invalid characters
    Given I am on the NHS Jobs search page
    When I enter "@@@@" in the keyword field
    And I enter "12345" in the location field
    And I click the search button
    Then I should see no results or a validation message

  Scenario: Search with abbreviation of keyword
    Given I am on the NHS Jobs search page
    When I enter "dr" in the keyword field
    And I enter "London" in the location field
    And I click the search button
    Then I should see job results related to "dr" in "London"

  Scenario: Search with valid keyword only
    Given I am on the NHS Jobs search page
    When I enter "Nurse" in the keyword field
    And I click the search button
    Then I should see job results related to keyword "Nurse"

    Scenario: Search with valid location only
      Given I am on the NHS Jobs search page
      When I enter "London" in the location field
      And I click the search button
      Then I should see job results related to location "London"

  Scenario: Search with leading and trailing spaces with keyword
    Given I am on the NHS Jobs search page
    When I enter "    Nurse  " in the keyword field
    And I click the search button
    Then I should see job results related to keyword "Nurse"

  Scenario: Search with spaces within the keyword
    Given I am on the NHS Jobs search page
    When I enter "Nur    se" in the keyword field
    And I click the search button
    Then I should see job results related to keyword "Nurse"

  Scenario: Search with trailing spaces in location
    Given I am on the NHS Jobs search page
    When I enter "    London   " in the location field
    And I click the search button
    Then I should see job results related to location "London"

  Scenario: Search with spaces within the location
    Given I am on the NHS Jobs search page
    When I enter "Lon   don" in the location field
    And I click the search button
    Then I should see job results related to location "London"

  Scenario: Search jobs by entering keyword, location, and employer name
    Given I am on the NHS Jobs search page
    When I enter "Nurse" in the keyword field
    And I enter "London" in the location field
    And I click on more search options
    And I enter "NHS England" as the employer name
    And I click the search button
    Then I should see job results related to "Nurse" in "London" for employer "NHS England"


  Scenario: Search jobs by entering keyword, location, and selecting distance
    Given I am on the NHS Jobs search page
    When I enter "Nurse" in the keyword field
    And I enter "London" in the location field
    And I select "+10 Miles" as the distance
    And I click the search button
    Then I should see job results related to "Nurse" in "London"
