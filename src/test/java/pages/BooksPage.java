package pages;

import enums.PageTitles;
import enums.Urls;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BooksPage extends HomePage {

  private static List<WebElement> bookRows;
  private static Boolean firstVisit = true;
  private static String url = Urls.BOOKS.getUrl();
  private static String title = PageTitles.BOOKS.getTitle();

  @FindBy(xpath = "//h1[contains(@class, \"page-header\")]")
  protected WebElement pageHeader;

  @FindBy(tagName = "table")
  WebElement booksTable;

  @FindAll({
    @FindBy(xpath = "//table/tbody/tr/td//a[text()=\"Edit\"]"),
    @FindBy(xpath = "//a[contains(@href, \"/edit\")]")
  })
  WebElement editButton;

  @FindAll({
    @FindBy(xpath = "//table/tbody/tr/td//a[text()=\"Delete\"]"),
    @FindBy(xpath = "//a[contains(@href, \"/delete\")]")
  })
  WebElement deleteButton;

  @FindAll({
    @FindBy(xpath = "//a[contains(@href, \"books/create\")]"),
    @FindBy(xpath = "//a[contains(text(), \"Create Book\")]")
  })
  WebElement createBookButton;

  public BooksPage(WebDriver driver) {
    super(driver);
  }

  private static List<WebElement> getAllBookTableRows() {
    return wait.until(
        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table/tbody/tr")));
  }

  public void navigateToPage() {
    navigateToPage(getUrl());
  }

  public void navigateToPage(String url) {
    super.navigateToPage(url);
    if (firstVisit) bookRows = getAllBookTableRows();
    firstVisit = false;
  }

  public WebElement getRowByBookName(String bookName) {
    for (WebElement row : bookRows) {
      if (getBookFromRow(row).getText().equals(bookName)) return row;
    }
    throw new AssertionError("Could not row by book name:  " + bookName);
  }

  public WebElement getRowByBookName(String bookName, List<WebElement> bookRows) {
    for (WebElement row : bookRows) {
      if (getBookFromRow(row).getText().equals(bookName)) return row;
    }
    return null;
  }

  public WebElement getBookFromRow(WebElement row) {
    return row.findElement(By.xpath("/td[1]"));
  }

  public WebElement getYearFromRow(WebElement row) {
    return row.findElement(By.xpath("/td[2]"));
  }

  public String getBookNameFromRow(WebElement row) {
    return getBookFromRow(row).getText();
  }

  public String getBookYearFromRow(WebElement row) {
    return getYearFromRow(row).getText();
  }

  public void clickOn_EditBook(WebElement row) {
    clickOn_EditSpecificBook(row);
  }

  public void clickOn_EditBook() {
    clickOn_EditFirstBook();
  }

  private void clickOn_EditSpecificBook(WebElement row) {
    row.findElement(By.xpath("/td//a[text()=\"Edit\"]")).click();
  }

  private void clickOn_EditFirstBook() {
    editButton.click();
  }

  public void clickOn_DeleteBook(WebElement row) {
    clickOn_EditSpecificBook(row);
  }

  public void clickOn_DeleteBook() {
    clickOn_DeleteFirstBook();
  }

  private void clickOn_DeleteSpecificBook(WebElement row) {
    row.findElement(By.xpath("/td//a[text()=\"Delete\"]")).click();
  }

  private void clickOn_DeleteFirstBook() {
    deleteButton.click();
  }

  public void assertRowDeleted() {
    Assert.assertTrue(bookRows.size() > getAllBookTableRows().size());
  }

  public void assertBookName(WebElement row, String expectedName) {
    String actualName = getBookNameFromRow(row);
    Assert.assertEquals(actualName, expectedName);
  }

  public void assertBookYear(WebElement row, String expectedYear) {
    String actualYear = getBookYearFromRow(row);
    Assert.assertEquals(actualYear, expectedYear);
  }

  public void assertBooksTablePopulated(Boolean empty) {
    if (empty) {
      Assert.assertEquals(0, getAllBookTableRows().size());
    } else {
      Assert.assertTrue(getAllBookTableRows().size() > 0);
    }
  }

  public void assertBookExists(String bookName, String bookYear) {
    WebElement row = getRowByBookName(bookName);
    assertElementDisplayed(row);
    Assert.assertEquals(getYearFromRow(row), bookYear);
  }

  public void assertBookDoesNotExist(String bookName) {
    Assert.assertEquals(null, getRowByBookName(bookName, getAllBookTableRows()));
  }

  public void assertNewBookExists() {
    Assert.assertTrue(bookRows.size() < getAllBookTableRows().size());
  }

  public void assertNewBookCreated(String bookName, String bookYear) {
    assertNewBookExists();
    assertBookExists(bookName, bookYear);
  }

  public void assertBookEdited(String bookName, String bookYear) {
    Assert.assertEquals(bookRows.size(), getAllBookTableRows().size());
    assertBookExists(bookName, bookYear);
  }

  public String getUrl() {
    return url;
  }

  public String getTitle() {
    return title;
  }
}
