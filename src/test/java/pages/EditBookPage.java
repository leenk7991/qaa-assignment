package pages;

import enums.PageTitles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditBookPage extends BooksPage {

  static String title = PageTitles.EDIT_BOOK.getTitle();
  static String url = "^books/\\d+/edit$";

  @FindBy(id = "title")
  WebElement titleInput;

  @FindBy(id = "year")
  WebElement yearInput;

  @FindBy(xpath = "//button[text()=\"Save\"]")
  WebElement saveButton;

  public EditBookPage(WebDriver driver) {
    super(driver);
  }
}
