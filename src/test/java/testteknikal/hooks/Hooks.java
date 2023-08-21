package testteknikal.hooks;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.testdms.drivers.DriverSingleton;
import com.testdms.utils.Constants;
import com.testdms.utils.TestScenarios;
import com.testdms.utils.Utils;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class Hooks {
    public static WebDriver driver;
    public static ExtentTest extentTest;
    public static ExtentReports reports = new ExtentReports("target/extent-report.html");

    @Before
    public static void setUp() {
        DriverSingleton.getInstance(Constants.CHROME);
        driver = DriverSingleton.getDriver();
        TestScenarios[] test = TestScenarios.values();
        extentTest = reports.startTest(test[Utils.testCount].getTestCaseName());
        Utils.testCount++;
    }

    @After
    public void endTestClass() {
        reports.endTest(extentTest);
        reports.flush();
    }

    @AfterStep
    public void getResultStatus(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            String screenshootPath = Utils.getScreenshot(driver, scenario.getName().replace(" ", "_"));
            extentTest.log(LogStatus.FAIL, scenario.getName() + "\n" +
                    extentTest.addScreenCapture(screenshootPath));
        }
    }

    @AfterAll
    public static void tearDown() {
        delay(2);
        DriverSingleton.closeObjectInstance();
    }

    public static void delay(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
