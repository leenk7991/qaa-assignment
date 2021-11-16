package cucumber;

import enums.PageObjects;
import managers.PageManager;
import managers.WebDriverManager;
import maps.PageObjectMap;

public class TestContext {
  private WebDriverManager webDriverManager;
  private PageManager pageManager;
  private ScenarioContext scenarioContext;
  private PageObjectMap pageObjectMap;

  public TestContext() {
    webDriverManager = new WebDriverManager();
    pageManager = new PageManager(webDriverManager.getDriver());
    scenarioContext = new ScenarioContext();
    pageObjectMap = PageObjectMap.getInstance();
    setPageObjectMap();
  }

  private void setPageObjectMap() {
    pageObjectMap.setPage(PageObjects.HOME, pageManager.getHomePage());
    pageObjectMap.setPage(PageObjects.BOOKS, pageManager.getBooksPage());
  }

  public WebDriverManager getWebDriverManager() {
    return webDriverManager;
  }

  public PageManager getPageManager() {
    return pageManager;
  }

  public ScenarioContext getScenarioContext() {
    return scenarioContext;
  }

  public PageObjectMap getPageObjectMap() {
    return pageObjectMap;
  }
}
