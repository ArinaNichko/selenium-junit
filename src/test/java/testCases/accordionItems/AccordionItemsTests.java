package testCases.accordionItems;

import base.BaseTest;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AccordionItemsPage;
import pages.HomePage;

import java.util.List;

import static utils.Constants.FIRST;
import static utils.Constants.TIMEOUT;
import static utils.EnvironmentContext.baseUrl;

@ExtendWith(ReportPortalExtension.class)
public class AccordionItemsTests extends BaseTest {
  private static AccordionItemsPage accordionItemsPage;
  private static HomePage homePage;

  @BeforeEach
  public void setAccordionItemsPage() {
    accordionItemsPage = pageFactoryManager.getPage(AccordionItemsPage.class);
    homePage = pageFactoryManager.getPage(HomePage.class);
  }

  @Test
  public void checkKeepClickingItem() {
    homePage.openHomePage(baseUrl);
    homePage.clickOnAccordionItemsLink();

    List<String> tabsList = homePage.tabsList();
    homePage.switchToTab(tabsList.get(FIRST));

    accordionItemsPage.waitVisibilityOfLoadText("LOADING COMPLETE.");
    accordionItemsPage.clickOnKeepClickingItem();

    Assertions.assertEquals(accordionItemsPage.getKeepClickingItemText(),
            "This text has appeared after 5 seconds!");
  }

  @Test
  public void checkManualTestingItem() {
    homePage.openHomePage(baseUrl);
    homePage.clickOnAccordionItemsLink();

    List<String> tabsList = homePage.tabsList();
    homePage.switchToTab(tabsList.get(FIRST));

    accordionItemsPage.implicitWait(TIMEOUT);
    accordionItemsPage.clickOnManualTestingItem();

    Assertions.assertTrue(accordionItemsPage.getManualTestingItemText()
            .contains("Manual testing has for some time"));
  }
}
