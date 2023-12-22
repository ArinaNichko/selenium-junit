package webDriverFactory.driver.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class LocalWebDriverCreator extends WebDriverCreator {
    private final String browser;

    public LocalWebDriverCreator(String browser) {
        this.browser = browser;
    }

    @Override
    public WebDriver createWebDriver() {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Invalid browser specified");
        }
        driver.manage().window().maximize();
        return driver;
    }
}
