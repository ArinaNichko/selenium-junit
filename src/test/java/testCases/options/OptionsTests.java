package testCases.options;

import base.BaseTest;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.HomePage;
import pages.OptionsPage;

import java.util.List;

import static utils.Constants.*;
import static utils.EnvironmentContext.baseUrl;

@ExtendWith(ReportPortalExtension.class)
public class OptionsTests extends BaseTest {
  private static OptionsPage optionsPage;
  private static HomePage homePage;

  @BeforeEach
  public void setOptionsPage() {
    optionsPage = pageFactoryManager.getPage(OptionsPage.class);
    homePage = pageFactoryManager.getPage(HomePage.class);
  }

  @ParameterizedTest
  @CsvFileSource(resources = DROPDOWN_TEST_DATA, numLinesToSkip = 1)
  public void checkJavaDropdownMenu(String option, String expectedResult) {
    homePage.openHomePage(baseUrl);
    homePage.clickOnOptionsLink();

    List<String> newTab = homePage.tabsList();
    homePage.switchToTab(newTab.get(FIRST));

    optionsPage.implicitWait(TIMEOUT);
    optionsPage.selectOption(option);

    Assertions.assertEquals(optionsPage.getSelectedOptionText(), expectedResult);
  }

  @Test
  public void checkEclipseDropdownMenu() {
    homePage.openHomePage(baseUrl);

    homePage.clickOnOptionsLink();

    List<String> newTab = homePage.tabsList();
    homePage.switchToTab(newTab.get(FIRST));

    optionsPage.implicitWait(TIMEOUT);
    optionsPage.clickOnEclipseDropdownMenu();

    optionsPage.waitVisibilityOfTestngOption();
    optionsPage.clickOnTestngOption();

    Assertions.assertEquals(optionsPage.getTestngOptionText(), "TestNG");
  }

  @Test
  public void checkGreenRadioButton() {
    homePage.openHomePage(baseUrl);
    homePage.clickOnOptionsLink();

    List<String> newTab = homePage.tabsList();
    homePage.switchToTab(newTab.get(FIRST));

    optionsPage.implicitWait(TIMEOUT);
    optionsPage.clickOnGreenRadioButton();

    Assertions.assertEquals(optionsPage.getGreenRadioButtonValue(), "green");
  }

  @Test
  public void checkCheckboxes() {
    homePage.openHomePage(baseUrl);
    homePage.clickOnOptionsLink();

    List<String> newTab = homePage.tabsList();
    homePage.switchToTab(newTab.get(FIRST));

    optionsPage.implicitWait(TIMEOUT);
    optionsPage.clickOnFirstOptionCheckbox();
    optionsPage.clickOnSecondOptionCheckbox();
    optionsPage.clickOnThirdOptionCheckbox();

    Assertions.assertTrue(optionsPage.isSelectedFirstOptionCheckbox() &&
            optionsPage.isSelectedSecondOptionCheckbox() && optionsPage.isNotSelectedThirdOptionCheckbox());
  }
}
