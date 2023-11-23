package cucumber.setUp;

import manager.PageFactoryManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import pages.ActionsPage;
import pages.HomePage;
import pages.LoginPortalPage;
import pages.OptionsPage;
import utils.PropertiesHelper;
import webDriverFactory.LocalWebDriverCreator;
import webDriverFactory.RemoteWebDriverCreator;
import webDriverFactory.WebDriverCreator;

import static java.util.Optional.ofNullable;
import static utils.PropertiesHelper.getInstance;

public class CucumberContext {
  protected static PropertiesHelper propertiesHelper = getInstance();
  public final int FIRST = 1;
  public WebDriver driver;
  public HomePage homePage;
  public LoginPortalPage loginPortalPage;
  public ActionsPage actionsPage;
  public OptionsPage optionsPage;
  public int timeout;
  public String baseUrl;
  private String hubUrl;
  private String browser;
  private boolean localRun;

  private static void configureLog4j() {
    PropertyConfigurator.configure(propertiesHelper.getProperty("log4jPropertiesPath"));
  }

  public void setUp() {
    configureConstant();
    configureBrowser();
    configurePages();
    configureLog4j();
  }

  public void configureBrowser() {
    setUpDriver(browser, localRun);
    driver.manage().window().maximize();
  }

  public void configurePages() {
    homePage = new PageFactoryManager(driver).getPage(HomePage.class);
    loginPortalPage = new PageFactoryManager(driver).getPage(LoginPortalPage.class);
    actionsPage = new PageFactoryManager(driver).getPage(ActionsPage.class);
    optionsPage = new PageFactoryManager(driver).getPage(OptionsPage.class);
  }

  public void setUpDriver(String browser, boolean localRun) {
    String envBrowser = System.getenv("BROWSER");
    browser = ofNullable(envBrowser).orElse(browser);
    WebDriverCreator creator;
    if (localRun) {
      creator = new LocalWebDriverCreator(browser);
    } else {
      creator = new RemoteWebDriverCreator(browser, hubUrl);
    }
    driver = creator.createWebDriver();
  }

  public void configureConstant() {
    hubUrl = propertiesHelper.getProperty("hubUrl");
    baseUrl = propertiesHelper.getProperty("baseUrl");

    timeout = Integer.parseInt(propertiesHelper.getProperty("timeout"));
    browser = propertiesHelper.getProperty("browser");
    localRun = Boolean.parseBoolean(propertiesHelper.getProperty("localRun"));
  }
}
