package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="@target/failedrun.txt", glue="cucumber", plugin={"pretty","html:target/rerun.html"})
	public class FailedRunner extends AbstractTestNGCucumberTests {
	}


