package cucumber.setUp;

import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import pages.ActionsPage;
import pages.HomePage;
import pages.LoginPortalPage;
import pages.OptionsPage;
import webDriverFactory.driver.DriverProvider;

import static utils.EnvironmentContext.configureLog4j;
import static utils.EnvironmentContext.configureConstant;
import static utils.EnvironmentContext.localRun;
import static utils.EnvironmentContext.browser;

public class CucumberContext {
  public WebDriver driver;
  public HomePage homePage;
  public LoginPortalPage loginPortalPage;
  public ActionsPage actionsPage;
  public OptionsPage optionsPage;

  public void setUp() {
    configureConstant();
    configureBrowser();
    configurePages();
    configureLog4j();
  }

  public void configureBrowser() {
    DriverProvider.initialize(browser, localRun);
    driver = DriverProvider.getInstance();
  }

  public void configurePages() {
    homePage = new PageFactoryManager(driver).getPage(HomePage.class);
    loginPortalPage = new PageFactoryManager(driver).getPage(LoginPortalPage.class);
    actionsPage = new PageFactoryManager(driver).getPage(ActionsPage.class);
    optionsPage = new PageFactoryManager(driver).getPage(OptionsPage.class);
  }
}
