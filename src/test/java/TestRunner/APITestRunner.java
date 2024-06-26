package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/resources/feature",
                 glue = {"StepDefinitions"},
                 plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
                 tags = "@SmokeAPI" )

public class APITestRunner extends AbstractTestNGCucumberTests {

}
