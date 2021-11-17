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
    And the page header should be displayed with text
    """
    Books
    """

  Scenario: User edits a book entry
    Given the user is on the Books Page
    And a Book Entry
    When the user clicks on Edit Book for the given book
    Then the user should be redirected to the Edit Book Page
    And the page header should be displayed with text
    """
    Edit Book
    """
    When the user enters "new book" for title input
    And the user enters "2020" for year input
    And the user clicks the save button
    Then the user should be redirected to the Books page
    And the new book should be displayed with
      | Title    | Year |
      | new book | 2020 |

  Scenario: User deletes a book
    Given the user is on the Books Page
    And a Book Entry
    When the user clicks on Delete Book for the given book
    Then the book should be deleted



