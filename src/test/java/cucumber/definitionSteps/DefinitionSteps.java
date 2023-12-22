package cucumber.definitionSteps;

import cucumber.setUp.CucumberContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import pages.ActionsPage;
import pages.HomePage;
import pages.LoginPortalPage;
import pages.OptionsPage;
import webDriverFactory.driver.DriverProvider;

import java.util.List;

import static utils.Constants.FIRST;
import static utils.Constants.TIMEOUT;
import static utils.EnvironmentContext.baseUrl;

public class DefinitionSteps {
  private final CucumberContext context;
  private ActionsPage actionsPage;
  private OptionsPage optionsPage;
  private HomePage homePage;
  private LoginPortalPage loginPortalPage;
  private String getAlertMessage;

  public DefinitionSteps(CucumberContext cucumberContext) {
    this.context = cucumberContext;
  }

  @Before
  public void setUp() {
    context.setUp();
    homePage = context.homePage;
    loginPortalPage = context.loginPortalPage;
    actionsPage = context.actionsPage;
    optionsPage = context.optionsPage;
  }

  @After
  public void tearDown() {
    context.driver.quit();
    DriverProvider.removeInstance();
  }

  @Given("User opens Home page")
  public void openHomePage() {
    homePage.openHomePage(baseUrl);
  }

  @When("User clicks on the login portal link")
  public void clickOnTheLoginPortalLink() {
    homePage.clickOnLoginPortalLink();
  }

  @And("User switches to another tab")
  public void switchToAnotherTab() {
    List<String> tabsList = homePage.tabsList();
    homePage.switchToTab(tabsList.get(FIRST));
  }

  @And("^User logs in with name - \"([A-Za-z]+)\", password - \"(.{8,})\"$")
  public void logIn(String username, String password) {
    loginPortalPage.implicitWait(TIMEOUT);
    loginPortalPage.loginWithDefaultUser(username, password);
  }

  @And("User accepts the alert")
  public void acceptTheAlert() {
    Alert alert = loginPortalPage.switchToAlert();
    getAlertMessage = loginPortalPage.getAlertText(alert);
    loginPortalPage.acceptAlert(alert);
  }

  @Then("^User sees the \"([a-z]+\s[a-z]+)\" message in alert window$")
  public void verifyMessageInAlertWindow(String expectedResult) {
    Assert.assertEquals(getAlertMessage, expectedResult);
  }

  @When("User clicks on the Actions link")
  public void clickOnActionsLink() {
    homePage.clickOnActionsLink();
  }

  @When("User moves element to container")
  public void moveElementToContainer() {
    actionsPage.implicitWait(TIMEOUT);
    actionsPage.moveElementToContainer();
  }

  @Then("the container text should contain {string}")
  public void verifyContainerText(String expectedResult) {
    Assert.assertTrue(actionsPage.getDroppableContainerText().contains(expectedResult));
  }

  @When("User performs double click")
  public void performDoubleClick() {
    actionsPage.implicitWait(TIMEOUT);
    actionsPage.performDoubleClick();
  }

  @Then("the double clickable item should have color {string}")
  public void verifyDoubleClickableItemColor(String expectedResult) {
    Assert.assertEquals(actionsPage.getDoubleClickableItemColor(), expectedResult);
  }

  @When("User clicks and holds on the box")
  public void clickAndHoldOnTheBox() {
    actionsPage.implicitWait(TIMEOUT);
    actionsPage.clickAndHold();
  }

  @Then("the box color should be {string}")
  public void verifyBoxColor(String expectedResult) {
    Assert.assertEquals(actionsPage.getClickableBoxColor(), expectedResult);
  }

  @And("the box text should be {string}")
  public void verifyBoxText(String expectedResult) {
    Assert.assertEquals(actionsPage.getClickableBoxText(), expectedResult);
  }

  @And("Users select the {string} option")
  public void selectOption(String selectOption) {
    optionsPage.implicitWait(TIMEOUT);
    optionsPage.selectOption(selectOption);
  }

  @Then("Users sees the {string} option text")
  public void verifyOptionText(String expectedResult) {
    Assert.assertEquals(optionsPage.getSelectedOptionText(), expectedResult);
  }

  @And("User clicks on the Green radio button")
  public void clickOnTheGreenRadioButton() {
    optionsPage.implicitWait(TIMEOUT);
    optionsPage.clickOnGreenRadioButton();
  }

  @Then("^User sees the value \"([a-z]+)\"$")
  public void verifyRadioButtonValue(String expectedResult) {
    Assert.assertEquals(optionsPage.getGreenRadioButtonValue(), expectedResult);
  }

  @And("User selects the first, second option checkboxes")
  public void selectOptionCheckboxes() {
    optionsPage.implicitWait(TIMEOUT);
    optionsPage.clickOnFirstOptionCheckbox();
    optionsPage.clickOnSecondOptionCheckbox();
    optionsPage.clickOnThirdOptionCheckbox();
  }

  @Then("User sees that the first and second checkboxes are selected")
  public void verifySelectedOptions() {
    Assert.assertTrue(optionsPage.isSelectedFirstOptionCheckbox() &&
            optionsPage.isSelectedSecondOptionCheckbox()
            && optionsPage.isNotSelectedThirdOptionCheckbox());
  }

  @When("User clicks on the Options link")
  public void userClicksOnTheOptionsLink() {
    context.homePage.clickOnOptionsLink();
  }
}
