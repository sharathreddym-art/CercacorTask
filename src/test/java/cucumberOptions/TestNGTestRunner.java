package cucumberOptions;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import utilities.DriverManager;

@CucumberOptions(features = "src/test/java/features", glue = "stepDefinations", tags = "@Login", dryRun = false,
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"})


public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    TestNGCucumberRunner testNGCucumberRunner;

    public TestNGTestRunner() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeTest
    public void initializeBrowser() {

        DriverManager driverManager = new DriverManager();
        driverManager.initializeDriver();
    }


    @AfterTest
    public void quitApp() {
        DriverManager.getAndroidDriver().closeApp();
    }
}
