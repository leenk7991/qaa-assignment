package pages;

import enums.PageTitles;
import enums.Urls;
import managers.FileReaderManager;
import org.apache.commons.text.CaseUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
  protected static WebDriver driver;
  protected static String url = Urls.LOCAL.getUrl();
  protected static String title = PageTitles.HOME.getTitle();
  protected static WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait =
        new WebDriverWait(
            this.driver,
            Duration.ofSeconds(
                FileReaderManager.getInstance().getConfigReader().getWebDriverWait()));
    printPageInfo();
  }

  public static void printPageInfo() {
    System.out.println("url: " + url);
    System.out.println("title: " + title);
  }

  public void navigateToPage(String url) {
    driver.get(url);
  }

  public void navigateToPage() {
    driver.get(getUrl());
  }

  public String getUrl() {
    return this.url;
  }

  public String getTitle() {
    return this.title;
  }

  public WebElement getElement(String elementName) {
    elementName = CaseUtils.toCamelCase(elementName, false);
    try {
      return (WebElement) this.getClass().getField(elementName).get(this);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(
          "Could not find element: " + elementName + " in page: " + this.toString());
    }
  }

  public void assertUrl() {
    Assert.assertTrue(wait.until(ExpectedConditions.urlMatches(url)));
  }

  public void assertUrl(String url) {
    Assert.assertTrue(wait.until(ExpectedConditions.urlMatches(url)));
  }

  public void assertPageTitle(String title) {
    Assert.assertTrue(wait.until(ExpectedConditions.titleContains(title)));
  }

  public WebElement assertElementDisplayed(String elementName) {
    WebElement element = getElement(elementName);
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  public WebElement assertElementDisplayed(WebElement element) {
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  public Boolean assertElementNotDisplayed(String elementName) {
    WebElement element = getElement(elementName);
    return wait.until(ExpectedConditions.invisibilityOf(element));
  }

  public void assertElementDisplayedWithText(String elementName, String text) {
    WebElement element = assertElementDisplayed(elementName);
    Assert.assertEquals(element.getText(), text);
  }

  public void assertElementDisplayedWithValue(String elementName, String value) {
    WebElement element = assertElementDisplayed(elementName);
    Assert.assertEquals(element.getAttribute("value"), value);
  }

  public void inputTextToElement(String elementName, String text) {
    WebElement element = assertElementDisplayed(elementName);
    element.sendKeys(text);
  }

  public void clickElement(String elementName) {
    WebElement element = assertElementDisplayed(elementName);
    element.click();
  }
}
