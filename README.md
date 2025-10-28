# NHS Job Search Automation Project

This project automates the testing of the NHS Jobs website using Selenium WebDriver, Cucumber (BDD), and TestNG.
It verifies that the job search functionality works correctly with different types of input, such as valid keywords,
trailing spaces, empty fields, and sorting by newest postings.

**Tech Stack:**
- Java
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- Maven
- Page Object Model (POM)
- IntelliJ IDEA

NHS-Search/
│
├── src/
│   ├── main/java/pages/             # Page Object Model classes
│   ├── test/java/stepdefinitions/   # Cucumber step definitions
│   ├── test/java/runners/           # Cucumber Test Runner classes
│   └── test/resources/features/     # Feature files (.feature)
│
├── pom.xml                          # Maven dependencies
└── README.md                        # Project documentation

**Features Tested:**
- Search for jobs by keyword and location
- Handle trailing and leading spaces in inputs
- Sort jobs by "Date posted (newest)"
- Validate that search results display correctly
- Handle empty keyword or location inputs
- Verify that job results contain the correct keyword

**Example Scenario: Search with keyword having trailing spaces**
```gherkin
Scenario: Search with keyword having trailing spaces
  Given I am on the NHS Jobs search page
  When I enter "Nurse   " as the job title
  And I enter "London" as the location
  And I click on the search button
  Then I should see job results related to "Nurse"
