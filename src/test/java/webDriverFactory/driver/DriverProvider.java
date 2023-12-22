package webDriverFactory.driver;


import org.openqa.selenium.WebDriver;
import webDriverFactory.driver.managers.LocalWebDriverCreator;
import webDriverFactory.driver.managers.RemoteWebDriverCreator;
import webDriverFactory.driver.managers.WebDriverCreator;

import static java.util.Optional.ofNullable;
import static utils.EnvironmentContext.hubUrl;


public class DriverProvider {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> LOCAL_RUN_THREAD = new ThreadLocal<>();
    private static final ThreadLocal<String> BROWSER_THREAD = new ThreadLocal<>();

    private DriverProvider() {
        WebDriverCreator creator = createDriver(BROWSER_THREAD.get(), LOCAL_RUN_THREAD.get());
        driver.set(creator.createWebDriver());
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            driver.set(new DriverProvider().getDriver());
        }
        return driver.get();
    }

    public static void removeInstance() {
        driver.remove();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public WebDriverCreator createDriver(String browser, boolean localRun) {
        String envBrowser = System.getenv("BROWSER");
        browser = ofNullable(envBrowser).orElse(browser);
        if (localRun) {
            return new LocalWebDriverCreator(browser);
        } else {
            return new RemoteWebDriverCreator(browser, hubUrl);
        }
    }

    public static void initialize(String browser, boolean localRun) {
        LOCAL_RUN_THREAD.set(localRun);
        BROWSER_THREAD.set(browser);
    }
}
