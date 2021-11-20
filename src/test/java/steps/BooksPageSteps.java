package steps;

import cucumber.TestContext;
import enums.ContextTypes;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.FileReaderManager;
import pages.BooksPage;
import testDataTypes.Book;

import java.util.List;
import java.util.Map;

public class BooksPageSteps {
  TestContext testContext;
  BooksPage booksPage;
  Book book;

  public BooksPageSteps(TestContext testContext) {
    this.testContext = testContext;
    booksPage = this.testContext.getPageManager().getBooksPage();
    testContext.getScenarioContext().setContext(ContextTypes.CURRENT_PAGE, booksPage);
  }

  @Given("^(?i)a book entry$")
  public Book get_book_entry() {
    book = FileReaderManager.getInstance().getBookDataReader().getBook();
    testContext.getScenarioContext().setContext(ContextTypes.BOOK, book);
    return book;
  }

  @Then("^(?i)the Books Table should (not )?be empty$")
  public void assert_book_table_not_empty(String notEmpty) {
    booksPage.assertBooksTablePopulated(!(notEmpty.equalsIgnoreCase("not ")));
  }

  @When("^(?i)the user clicks on Edit Book for the given book$")
  public void click_on_edit_book() {
    booksPage.clickOn_EditBook(booksPage.getRowByBookName(book.title));
  }

  @When("^(?i)the user clicks on delete Book for the given book$")
  public void click_on_delete_book() {
    booksPage.clickOn_DeleteBook(booksPage.getRowByBookName(book.title));
  }

  @Then("^the edited book should be displayed with$")
  public void assert_edited_book_is_displayed(DataTable table) {
    List<Map<String, String>> data = table.asMaps();
    booksPage.assertBookEdited(data.get(0).get("Title"), data.get(0).get("Year"));
  }

  @Then("^the new book should be displayed with$")
  public void assert_new_book_is_displayed(DataTable table) {
    List<Map<String, String>> data = table.asMaps();
    booksPage.assertNewBookCreated(data.get(0).get("Title"), data.get(0).get("Year"));
  }

  @Then("^the book should be deleted")
  public void assert_book_is_deleted() {
    booksPage.assertRowDeleted();
    booksPage.assertBookDoesNotExist(book.title);
  }
}
