package steps;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;

public class CommonSteps {
  TestContext testContext;

  public CommonSteps(TestContext context) {
    testContext = context;
  }

  public BasePage change_page_object(String pageName) {
    BasePage currentPage = testContext.getPageObjectMap().getPage(pageName);
    testContext.getScenarioContext().setContext("currentPage", currentPage);
    return currentPage;
  }

  @When("^(?i)the user navigate to (.+)$")
  public BasePage navigate_to_page(String pageName) {
    BasePage page = change_page_object(pageName);
    page.navigateToPage();
    return page;
  }

  @Given("^(?i)the user is on the (.+)$")
  public void user_is_on_page(String pageName) {
    navigate_to_page(pageName);
  }

  @When("^(?i)the user navigates to the (.+) page through the top navbar$")
  public void navigate_through_navbar(String pageName) {
    HomePage homepage = (HomePage) testContext.getScenarioContext().getContext("currentPage");
    homepage.clickOn_TopNavLink(pageName);
    change_page_object(pageName + " Page");
  }

  @Then("^(?i)the user should be redirected to the (.+)$")
  public void assert_redirection(String pageName) {
    BasePage page = change_page_object(pageName);
    page.assertUrl();
    page.assertPageTitle();
  }

  @Then("^the page should display (?:the|a) (.+)$")
  public void assert_element_displayed(String elementName) {
    BasePage currentPage = (BasePage) testContext.getScenarioContext().getContext("currentPage");
  }
}
