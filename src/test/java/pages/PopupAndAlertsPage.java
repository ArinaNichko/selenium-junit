package pages;

import base.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class PopupAndAlertsPage extends BasePage {
  @FindBy(xpath = "//*[@id=\"button1\"]")
  private WebElement alertButton;

  @FindBy(id = "button2")
  private WebElement popupButton;

  @FindBy(xpath = "//*[@id=\"myModal\"]/div/div/div[3]/button")
  private WebElement closePopupButton;

  @FindBy(xpath = "//h4")
  private WebElement popupTitle;

  public PopupAndAlertsPage(WebDriver driver) {
    super(driver);
  }

  public void clickOnAlertButton() {
    javascriptExecutor.executeScript("arguments[0].click();", alertButton);
  }

  public void clickOnClosePopupButton() {
    elementWrapper(closePopupButton).click();
  }

  public String getPopupTitle() {
    return elementWrapper(popupTitle).getText();
  }

  public void clickOnPopupButton() {
    elementWrapper(popupButton).click();
  }

  public void waitVisibilityOfPopup() {
    log.info("Setting explicit wait of visibility of element: {}", closePopupButton.getAttribute("value"));
    WAIT.until(
            ExpectedConditions.visibilityOf(closePopupButton));
  }
}
