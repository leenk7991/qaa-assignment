package managers;

import java.lang.reflect.InvocationTargetException;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.BooksPage;
import pages.EditBookPage;
import pages.HomePage;

public class PageManager {
  WebDriver driver;
  private HomePage homePage;
  private BooksPage booksPage;
  private EditBookPage editBookPage;

  public PageManager(WebDriver driver) {
    this.driver = driver;
  }

  public BasePage getPage(String pageName) {
    try {
      return (BasePage) this.getClass().getMethod("get" + pageName, Object[].class).invoke(this);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Page: " + pageName + "was not found");
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return getHomePage();
  }

  public HomePage getHomePage() {
    return (homePage == null) ? homePage = new HomePage(driver) : homePage;
  }

  public BooksPage getBooksPage() {
    return (booksPage == null) ? booksPage = new BooksPage(driver) : booksPage;
  }

  public EditBookPage getEditBookPage() {
    return (editBookPage == null) ? editBookPage = new EditBookPage(driver) : editBookPage;
  }
}
