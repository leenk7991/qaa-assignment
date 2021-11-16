package pages;

import org.apache.commons.text.CaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
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

  public abstract void assertUrl();

  public abstract void assertPageTitle();

  public abstract void navigateToPage();

  public void assertElementDisplayed(String elementName) {
    WebElement element = getElement(elementName);
    new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));
  }

  public void assertElementNotDisplayed(String elementName) {
    WebElement element = getElement(elementName);
    new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(element));
  }
}
