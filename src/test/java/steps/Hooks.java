package steps;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
  TestContext testContext;

  public Hooks(TestContext context) {
    testContext = context;
  }

  @Before
  public void BeforeSteps() {}

  @After
  public void AfterSteps() {
    testContext.getWebDriverManager().closeDriver();
  }
}
