package pages;

import enums.PageTitles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditBookPage extends BooksPage {

  private static String title = PageTitles.EDIT_BOOK.getTitle();
  private static String url = "^books/\\d+/edit$";
  @FindBy(id = "title")
  protected WebElement titleInput;
  @FindBy(id = "year")
  protected WebElement yearInput;
  @FindBy(xpath = "//button[text()=\"Save\"]")
  protected WebElement saveButton;

  public EditBookPage(WebDriver driver) {
    super(driver);
  }

  public String getUrl() {
    return url;
  }

  public String getTitle() {
    return title;
  }
}
