Feature: [REGRESSION] Books

  Scenario: User navigates to the books page through the top navbar successfully
    Given the user is on the home page
    When the user navigates to the books page through the top navbar
    Then the user should be redirected to the Books Page
    And the page should display the Books Table

  Scenario: User navigates to the books page through the side navbar successfully
    Given the user is on the home page
    When the user navigates to the books page through the side navbar
    Then the user should be redirected to the Books Page
    And the page should display the Books Table

  Scenario: Books Table is not empty
    Given the user is on the Books Page
    Then the Books Table should not be empty

  Scenario: User edits a book entry
    Given the user is on the Books Page
    And a Book Entry
    Then the Books Table should not be empty


