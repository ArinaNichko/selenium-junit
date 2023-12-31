package cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/"},
        glue = {"cucumber.definitionSteps"},
        plugin = {"pretty", "com.epam.reportportal.cucumber.ScenarioReporter"}
       )
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
