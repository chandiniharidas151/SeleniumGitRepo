package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue= {"StepDefinition"},
        features = "src/test/java/FeatureFile/Login.feature",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true,  // Display output in a more readable format
        dryRun = false  // Check if your step definitions match with your feature file
)
public class TestRunner {
}
