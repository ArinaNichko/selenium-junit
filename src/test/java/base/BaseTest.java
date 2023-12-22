package base;

import manager.PageFactoryManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import webDriverFactory.driver.DriverProvider;

import static utils.EnvironmentContext.browser;
import static utils.EnvironmentContext.localRun;
import static utils.EnvironmentContext.configureConstant;
import static utils.EnvironmentContext.configureLog4j;

public class BaseTest {
    public static WebDriver driver;
    protected static PageFactoryManager pageFactoryManager;

    @BeforeEach
    public void setUp() {
        configureConstant();
        DriverProvider.initialize(browser, localRun);
        driver = DriverProvider.getInstance();
        pageFactoryManager = new PageFactoryManager(driver);
        configureLog4j();
    }

    @AfterEach
    public void tearDown() {
        DriverProvider.getInstance().quit();
        DriverProvider.removeInstance();
    }
}
