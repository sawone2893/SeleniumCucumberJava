package bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/java/bdd/features/"},
		dryRun=!true,
		monochrome=true,
		glue={"bdd.stepDefinitions","bdd.hooks"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"summary"
				},
		tags="@sanity"
		)
public class Runner extends AbstractTestNGCucumberTests{

}
