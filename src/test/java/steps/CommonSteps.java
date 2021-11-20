package steps;

import cucumber.TestContext;
import enums.ContextTypes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;

public class CommonSteps {
  TestContext testContext;
  BasePage currentPage;

  public CommonSteps(TestContext context) {
    testContext = context;
    currentPage = testContext.getPageManager().getHomePage();
  }

  public BasePage getCurrentPage() {
    return (BasePage) testContext.getScenarioContext().getContext(ContextTypes.CURRENT_PAGE);
  }

  public BasePage change_page_object(String pageName) {
    currentPage = testContext.getPageObjectMap().getPage(pageName);
    testContext.getScenarioContext().setContext(ContextTypes.CURRENT_PAGE, currentPage);
    return currentPage;
  }

  @When("^(?i)the user goes to the (.+) page$")
  public void user_goes_to_page(String pageName) {
    currentPage = navigate_through_navbar(pageName);
    currentPage.navigateToPage();
  }

  @When("^(?i)the user navigates to (?:the )?(.+)$")
  public BasePage navigate_to_page(String pageName) {
    currentPage = change_page_object(pageName);
    currentPage.navigateToPage();
    testContext.getWebDriverManager().getDriver().get(currentPage.getUrl());
    testContext.getWebDriverManager().getDriver().get(currentPage.getUrl());
    return currentPage;
  }

  @Given("^(?i)the user is on the (.+)$")
  public void user_is_on_page(String pageName) {
    navigate_to_page(pageName);
  }

  @When("^(?i)the user navigates to the (.+) page through the top navbar$")
  public BasePage navigate_through_navbar(String pageName) {
    HomePage homepage = (HomePage) currentPage;
    homepage.clickOn_TopNavLink(pageName);
    return change_page_object(pageName + " Page");
  }

  @When("^(?i)the user navigates to the (.+) page through the side navbar")
  public BasePage navigate_through_side_navbar(String pageName) {
    HomePage homepage = (HomePage) currentPage;
    homepage.clickOn_SideBarLink(pageName);
    return change_page_object(pageName + " Page");
  }

  @Then("^(?i)the user should be redirected to the (.+)$")
  public void assert_redirection(String pageName) {
    currentPage = change_page_object(pageName);
    currentPage.assertUrl(currentPage.getUrl());
    currentPage.assertPageTitle(currentPage.getTitle());
  }

  @Then("^the page should display (?:the |a )?(.+)$")
  public void assert_element_displayed(String elementName) {
    currentPage = getCurrentPage();
    currentPage.assertElementDisplayed(elementName);
  }

  @When("^the user clicks (?:the |on )?(.+)$")
  public void click_on_element(String elementName) {
    currentPage = getCurrentPage();
    currentPage.clickElement(elementName);
  }

  @When("^the user enters \"(.+)\" for (?:the |a )?(.+)$")
  public void enter_text(String text, String elementName) {
    currentPage = getCurrentPage();
    currentPage.inputTextToElement(elementName, text);
  }

  @Then("^(?:the|a) (.+) should be displayed with text$")
  public void assert_element_displayed_with_text(String elementName, String text) {
    currentPage = getCurrentPage();
    currentPage.assertElementDisplayedWithText(elementName, text);
  }
}
