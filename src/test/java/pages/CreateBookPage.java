package pages;

import enums.PageTitles;
import enums.Urls;
import org.openqa.selenium.WebDriver;

public class CreateBookPage extends EditBookPage {
  private static String title = PageTitles.CREATE_BOOK.getTitle();
  private static String url = Urls.CREATE_BOOK.getUrl();

  public CreateBookPage(WebDriver driver) {
    super(driver);
  }

  public String getUrl() {
    return url;
  }

  public String getTitle() {
    return title;
  }
}
