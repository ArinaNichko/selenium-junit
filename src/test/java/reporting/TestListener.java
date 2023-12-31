package reporting;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static base.BaseTest.driver;
@Slf4j
public class TestListener implements ITestListener {
   public void onTestStart(ITestResult result) {
  }

  public void onTestSuccess(ITestResult result) {
  }

  public void onTestFailure(ITestResult result) { saveScreenshot();
  }

  public void onTestSkipped(ITestResult result) {
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  public void onTestFailedWithTimeout(ITestResult result) {
    log.debug("Screenshot was saved: {}", getCurrentTimeAsString() + ".png");
    this.onTestFailure(result);
  }

  public void onStart(ITestContext context) {
  }

  public void onFinish(ITestContext context) {
  }

  private void saveScreenshot(){
    File screenCapture = ((TakesScreenshot)driver)
            .getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(screenCapture, new File(
              ".//target/screenshots/"
              + getCurrentTimeAsString() + ".png"
      ));
    } catch (IOException e){
      log.error("Failed to save screenshot: " + e.getLocalizedMessage());
    }
  }

  private String getCurrentTimeAsString(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
    return ZonedDateTime.now().format(formatter);
  }

}
