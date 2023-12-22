package pages;

import base.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Constants.JS_CLICK_SCRIPT;

@Slf4j
public class HomePage extends BasePage {
  @FindBy(id = "dropdown-checkboxes-radiobuttons")
  private WebElement optionsLink;

  @FindBy(css = "#popup-alerts")
  private WebElement popupAndAlertsLink;

  @FindBy(xpath = "//h1[contains(text(),'ACCORDION')]")
  private WebElement accordionItemsLink;

  @FindBy(css = "#login-portal")
  private WebElement loginPortalLink;

  @FindBy(css = "#actions")
  private WebElement actionsLink;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void openHomePage(String baseUrl) {
    log.info("Opened home page");
    driver.get(baseUrl);
  }

  public void clickOnOptionsLink() {
    log.info("Clicked on Options link");
    javascriptExecutor.executeScript(JS_CLICK_SCRIPT, optionsLink);
  }

  public void clickOnLoginPortalLink() {
    log.info("Clicked on Login portal link");
    javascriptExecutor.executeScript(JS_CLICK_SCRIPT, loginPortalLink);
  }

  public void clickOnPopupAndAlertsLink() {
    log.info("Clicked on Popup and alert link");
    javascriptExecutor.executeScript(JS_CLICK_SCRIPT, popupAndAlertsLink);
  }

  public void clickOnAccordionItemsLink() {
    log.info("Clicked on Accordion items link");
    javascriptExecutor.executeScript(JS_CLICK_SCRIPT, accordionItemsLink);
  }

  public void clickOnActionsLink() {
    log.info("Clicked on Actions link");
    javascriptExecutor.executeScript(JS_CLICK_SCRIPT, actionsLink);
  }
}
