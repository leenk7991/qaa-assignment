package pages;

import enums.PageTitles;
import enums.Urls;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class BooksPage extends HomePage {

  static String url = Urls.BOOKS.getUrl();
  static String title = PageTitles.BOOKS.getTitle();

  @FindBy(xpath = "//h1[contains(@class, \"page-header\")]")
  WebElement pageHeader;

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

  private List<WebElement> bookRows;

  public BooksPage(WebDriver driver) {
    super(driver);
    bookRows = getAllBookTableRows();
    System.out.println("inside books page");
    System.out.println(url);
    System.out.println(title);
  }

  public WebElement getRowByBookName(String bookName) {
    for (WebElement row : bookRows) {
      if (getBookFromRow(row).getText().equals(bookName)) return row;
    }
    throw new AssertionError("Could not row by book name:  " + bookName);
  }

  public WebElement getBookFromRow(WebElement row) {
    return row.findElement(By.xpath("/td[1]"));
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
    clickOn_EditFirstBook();
  }

  private void clickOn_DeleteSpecificBook(WebElement row) {
    row.findElement(By.xpath("/td//a[text()=\"Delete\"]")).click();
  }

  private void clickOn_DeleteFirstBook() {
    editButton.click();
  }

  public void assertRowDeleted(WebElement row) {
    Assert.assertTrue(bookRows.size() > getAllBookTableRows().size());
  }

  public void assertBookName(WebElement row, String name) {}

  private List<WebElement> getAllBookTableRows() {
    return driver.findElements(By.xpath("//table/tbody/tr"));
  }
}
