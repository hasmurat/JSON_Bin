package Api_Tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReaders;

public class TestBase {

    @BeforeClass
    public void setUp(){
        baseURI = ConfigurationReaders.get("base_uri");
        basePath="/b";
    }

    @AfterClass
    public void tearDown(){
        reset();
    }

}
