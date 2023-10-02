package bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/java/bdd/features"},
		dryRun=!true,
		monochrome=true,
		glue={"bdd.stepDefinitions","bdd.hooks"},
		plugin= {
				"pretty",
				"html:reports/reports.html",
				"json:reports/report.json",
				"junit:reports/report.xml"
				},
		tags="@smoke"
		)
public class Runner extends AbstractTestNGCucumberTests{

}
