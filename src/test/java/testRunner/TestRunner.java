package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

//Whole run
@CucumberOptions(
		features = "./Features",
		glue = "stepDefinitions",
		monochrome = true,
		plugin = {"pretty", "html:test-output.html"}
)

//Running tags like ErrorTest
//@CucumberOptions(
//		features = "./Features",
//		glue = "stepDefinitions",
//		monochrome = true,
//		plugin = {"pretty", "html:test-output.html"},
//		tags = "@ErrorTest"
//)

public class TestRunner {
}
