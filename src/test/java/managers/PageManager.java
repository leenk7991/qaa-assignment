package managers;

import org.openqa.selenium.WebDriver;
import pages.*;

import java.lang.reflect.InvocationTargetException;

public class PageManager {
  WebDriver driver;
  private HomePage homePage;
  private BooksPage booksPage;
  private EditBookPage editBookPage;
  private CreateBookPage createBookPage;

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

  public CreateBookPage getCreateBookPage() {
    return (createBookPage == null) ? createBookPage = new CreateBookPage(driver) : createBookPage;
  }
}
