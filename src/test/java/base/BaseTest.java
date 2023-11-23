package base;

import manager.PageFactoryManager;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.PropertiesHelper;
import webDriverFactory.LocalWebDriverCreator;
import webDriverFactory.RemoteWebDriverCreator;
import webDriverFactory.WebDriverCreator;

import static java.util.Optional.ofNullable;
import static utils.PropertiesHelper.getInstance;

public class BaseTest {
    public static WebDriver driver;
    public static PropertiesHelper propertiesHelper;
    protected static HomePage homePage;
    protected static PageFactoryManager pageFactoryManager;
    protected static String browser;
    protected static boolean localRun;
    protected static long timeout;
    protected static String baseUrl;
    private static String hubUrl;
    protected final int FIRST = 1;

    private static void configureLog4j() {
        PropertyConfigurator.configure(propertiesHelper.getProperty("log4jPropertiesPath"));
    }

    @BeforeEach
    public void setUp() {
        propertiesHelper = getInstance();
        configureConstant();
        setUpDriver(browser, localRun);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getPage(HomePage.class);

        configureLog4j();
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

        timeout = Long.parseLong(propertiesHelper.getProperty("timeout"));
        browser = propertiesHelper.getProperty("browser");
        localRun = Boolean.parseBoolean(propertiesHelper.getProperty("localRun"));
        timeout = Long.parseLong(propertiesHelper.getProperty("timeout"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
