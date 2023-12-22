package testCases.popupAndAlert;

import base.BaseTest;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import pages.HomePage;
import pages.PopupAndAlertsPage;

import java.util.List;

import static utils.Constants.FIRST;
import static utils.Constants.TIMEOUT;
import static utils.EnvironmentContext.baseUrl;

@ExtendWith(ReportPortalExtension.class)
public class PopupAndAlertsTests extends BaseTest {
  private static PopupAndAlertsPage popupAndAlertsPage;
  private static HomePage homePage;

  @BeforeEach
  public void setPopupAndAlertsPage() {
    popupAndAlertsPage = pageFactoryManager.getPage(PopupAndAlertsPage.class);
  }

  @Test
  public void checkAlert() {
    homePage.openHomePage(baseUrl);
    homePage.clickOnPopupAndAlertsLink();

    List<String> tabsList = homePage.tabsList();
    homePage.switchToTab(tabsList.get(FIRST));

    popupAndAlertsPage.implicitWait(TIMEOUT);
    popupAndAlertsPage.clickOnAlertButton();

    Alert alert = popupAndAlertsPage.switchToAlert();
    String getAlertMessage = popupAndAlertsPage.getAlertText(alert);

    popupAndAlertsPage.acceptAlert(alert);

    Assertions.assertEquals(getAlertMessage, "I am an alert box!");
  }

  @Test
  public void checkPopup() {
    homePage.openHomePage(baseUrl);
    homePage.clickOnPopupAndAlertsLink();

    List<String> tabsList = homePage.tabsList();
    homePage.switchToTab(tabsList.get(FIRST));

    popupAndAlertsPage.implicitWait(TIMEOUT);
    popupAndAlertsPage.clickOnPopupButton();

    popupAndAlertsPage.waitVisibilityOfPopup();
    String popupTitle = popupAndAlertsPage.getPopupTitle();

    popupAndAlertsPage.clickOnClosePopupButton();

    Assertions.assertEquals(popupTitle,
            "It’s that Easy!! Well I think it is.....");
  }
}
