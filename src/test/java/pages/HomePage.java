package pages;

import enums.LinksText;
import enums.PageTitles;
import enums.Urls;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(xpath = "//div[contains(text(), \"Hello \")]")
  protected WebElement greetingMessage;

  @FindBy(id = "navbar")
  protected WebElement navbar;

  @CacheLookup
  @FindBy(id = "searchForm")
  protected WebElement searchForm;

  @CacheLookup
  @FindBy(id = " searchID")
  protected WebElement searchInput;

  @CacheLookup
  @FindBy(xpath = "//button[text()=\"Search\"]")
  protected WebElement searchButton;

  @CacheLookup
  @FindBy(xpath = "//a[contains(@class, \"navbar-brand\")]")
  protected WebElement projectName;

  private String url = Urls.HOME.getUrl();
  private String title = PageTitles.HOME.getTitle();

  public HomePage(WebDriver driver) {
    super(driver);
    System.out.println("inside home page");
    System.out.println(url);
    System.out.println(title);
  }

  protected String getLinkSelector(String name) {
    return String.format("//a[@text()=%s]", name);
  }

  protected WebElement getTopNavLink(String name) {
    return driver.findElement(By.xpath("//div[@id=\"navbar\"]" + getLinkSelector(name)));
  }

  protected WebElement getSideBarLink(String name) {
    return driver.findElement(
        By.xpath("//div[contains(@class, \"sidebar\")]" + getLinkSelector(name)));
  }

  @Override
  public void assertUrl() {
    Assert.assertEquals(url, driver.getCurrentUrl());
  }

  @Override
  public void assertPageTitle() {
    Assert.assertEquals(title, driver.getTitle());
  }

  public void clickOn_SideHomeLink() {
    getSideBarLink(LinksText.HOME.getText()).click();
  }

  public void clickOn_TopHomeLink() {
    getTopNavLink(LinksText.HOME.getText()).click();
  }

  public void clickOn_SideBooksLink() {
    getSideBarLink(LinksText.BOOKS.getText()).click();
  }

  public void clickOn_TopBooksLink() {
    getTopNavLink(LinksText.BOOKS.getText()).click();
  }

  public void clickOn_SideAuthorsLink() {
    getSideBarLink(LinksText.AUTHORS.getText()).click();
  }

  public void clickOn_TopAuthorsLink() {
    getTopNavLink(LinksText.AUTHORS.getText()).click();
  }

  public void clickOn_ProjectName() {
    projectName.click();
  }

  public void clickOn_TopNavLink(String name) {
    switch (name.toLowerCase()) {
      case "books":
        clickOn_TopBooksLink();
        break;
      case "home":
        clickOn_TopHomeLink();
        break;
      case "authors":
        clickOn_TopAuthorsLink();
    }
  }

  public void clickOn_SideBarLink(String name) {
    switch (name.toLowerCase()) {
      case "books":
        clickOn_SideBooksLink();
        break;
      case "home":
        clickOn_SideHomeLink();
        break;
      case "authors":
        clickOn_SideAuthorsLink();
    }
  }

  @Override
  public void navigateToPage() {
    driver.get(url);
  }
}
