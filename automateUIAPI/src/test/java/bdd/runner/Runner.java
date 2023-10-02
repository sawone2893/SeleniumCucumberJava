package bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/java/bdd/features"},
		dryRun=!true,
		monochrome=true,
		glue={"bdd.stepDefinitions","bdd.hooks"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags="@smoke"
		)
public class Runner extends AbstractTestNGCucumberTests{

}
