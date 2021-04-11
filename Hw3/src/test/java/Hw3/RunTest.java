package Hw3;

import cucumber.api.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
//import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
//import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "Hw3"
)
public class RunTest extends AbstractTestNGCucumberTests{
}
