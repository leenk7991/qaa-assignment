package steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"steps"},
    plugin = {
      "pretty",
      "html:target/HtmlReports",
      "json:target/JSONReports/report.json",
      "junit:target/JunitReports/report.xml"
    })
public class TestRunner {}
