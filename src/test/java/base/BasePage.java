package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static utils.Constants.TIMEOUT;

@Slf4j
public class BasePage {
  protected final WebDriverWait WAIT;
  protected JavascriptExecutor javascriptExecutor;
  protected WebDriver driver;

  protected BasePage(WebDriver driver) {
    this.driver = driver;
    WAIT = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    javascriptExecutor = (JavascriptExecutor) driver;
    PageFactory.initElements(driver, this);
  }

  public List<String> tabsList() {
    return new ArrayList<>(driver.getWindowHandles());
  }

  public void switchToTab(String tab) {
    log.debug("Switched to tab: {}", tab);
    driver.switchTo().window(tab);
  }

  public Alert switchToAlert() {
    log.info("Switched to alert");
    return driver.switchTo().alert();
  }

  public String getAlertText(Alert alert) {
    log.debug("Getting alert text: {}", alert.getText());
    return alert.getText();
  }

  public void acceptAlert(Alert alert) {
    log.info("Accepted alert");
    alert.accept();
  }

  public void implicitWait(long TIMEOUT) {
    log.info("Setting implicit wait to {} seconds", TIMEOUT);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
  }
}
