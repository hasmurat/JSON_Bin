package Api_Tests;

import static io.restassured.RestAssured.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReaders;

public class TestBase {

    protected static ExtentReports reports;
    protected static ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeClass
    public void setUp(){
        baseURI = ConfigurationReaders.get("base_uri");
        basePath="/b";


        String projectPath = System.getProperty("user.dir");
        String path = projectPath+"/test-output/report.html";

        reports = new ExtentReports();
        htmlReporter = new ExtentHtmlReporter(path);
        reports.attachReporter(htmlReporter);

        htmlReporter.config().setReportName("Smoke Test Results");


    }

    @AfterClass
    public void tearDown(){
        reports.flush();
        reset();
    }

}
