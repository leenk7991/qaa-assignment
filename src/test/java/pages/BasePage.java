package pages;

import enums.Urls;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import managers.FileReaderManager;
import org.apache.commons.text.CaseUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected String url = Urls.LOCAL.getUrl();

  public BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait =
        new WebDriverWait(
            this.driver, FileReaderManager.getInstance().getConfigReader().getWebDriverWait());
  }

  public WebElement getElement(String elementName) {
    elementName = CaseUtils.toCamelCase(elementName, false);
    try {
      return (WebElement) this.getClass().getField(elementName).get(this);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(
          "Could nor find element: " + elementName + "in page: " + this.toString());
    }
  }

  public void assertUrl() {
    String currentUrl = driver.getCurrentUrl();
    Pattern pattern = Pattern.compile(url);
    Matcher matcher = pattern.matcher(currentUrl);
    Assert.assertTrue(matcher.find());
  }

  public abstract void assertPageTitle();

  public void navigateToPage() {
    driver.get(url);
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
