package steps;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "steps" }, monochrome = true, plugin = { "pretty",
		"html:target/HtmlReports", "json:target/JSONReports/report.json", "junit:target/JunitReports/report.xml" })
public class TestRunner {

}
