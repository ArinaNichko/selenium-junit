package webDriverFactory;

import exceptions.TestExecutionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverCreator implements WebDriverCreator{
  private final String BROWSER;
  private final String HUB_URL;

  public RemoteWebDriverCreator(String browser, String hubUrl) {
    this.BROWSER = browser;
    this.HUB_URL = hubUrl;
  }

  @Override
  public WebDriver createWebDriver() {
    try {
      return new RemoteWebDriver(new URL(HUB_URL), getDesiredCapabilities(BROWSER));
    } catch (MalformedURLException e) {
      throw new TestExecutionException(e.getMessage());
    }
  }

  private DesiredCapabilities getDesiredCapabilities(String browser) {
    switch (browser.toLowerCase()) {
      case "chrome" -> {
        ChromeOptions chromeOptions = new ChromeOptions();
        return new DesiredCapabilities().merge(chromeOptions);
      }
      case "firefox" -> {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new DesiredCapabilities().merge(firefoxOptions);
      }
      case "edge" -> {
        EdgeOptions edgeOptions = new EdgeOptions();
        return new DesiredCapabilities().merge(edgeOptions);
      }
      default -> new DesiredCapabilities();
    }
    return new DesiredCapabilities();
  }
}
