package eComm.Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
		features = "src/test/resources/eComm/Featurefiles",
		glue = {"eComm.Stepdefinitions", "eComm.Hooks"},
		dryRun = false,									
		monochrome = true,
		tags = "@TS006_TC001",
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
		)

public class TestRunner extends AbstractTestNGCucumberTests {
	
	
	
}
