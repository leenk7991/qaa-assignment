package steps;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.FileReaderManager;

public class Hooks {
  TestContext testContext;

  public Hooks(TestContext context) {
    testContext = context;
  }

  @Before
  public void beforeSteps() {
    testContext
        .getWebDriverManager()
        .getUrl(FileReaderManager.getInstance().getConfigReader().getAppUrl());
    testContext.setPageObjectMap();
  }

  @After
  public void afterSteps() {
    testContext.getWebDriverManager().closeDriver();
  }
}
