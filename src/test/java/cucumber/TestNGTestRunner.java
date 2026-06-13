package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (features="src/test/java/cucumber",glue="cucumber",monochrome=true,tags="@ErrorValidation",
plugin= {"pretty","html:target/cucumber.html","rerun:target/failedrun.txt"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
