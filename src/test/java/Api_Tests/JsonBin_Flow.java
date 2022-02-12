package Api_Tests;

import static io.restassured.RestAssured.*;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ApiUtils;
import utilities.ConfigurationReaders;
import static org.hamcrest.Matchers.*;

public class JsonBin_Flow extends TestBase{

    ApiUtils apiUtils = new ApiUtils();
    Response response;
    JsonPath jsonPath;
    String id_pojo;
    String id_map;


    @Test (priority = 1)
    public void createJson1(){

            extentLogger = reports.createTest("Create with Pojo");

             response = given().accept(ContentType.JSON)
                                    .and().contentType(ContentType.JSON)
                                    .header("X-Master-Key", ConfigurationReaders.get("key"))
                                    .and().body(apiUtils.createJson("Helloooo"))
                            .when()
                                    .post()
                            .then()
                                    .statusCode(200)
                                    .and().contentType("application/json")
                                    .and().assertThat().body("record.record.sample",equalTo("Helloooo"))
                        .extract().response();

        jsonPath=response.jsonPath();
        id_pojo=jsonPath.getString("metadata.id");
        System.out.println("id_pojo = " + id_pojo);
        response.prettyPrint();

    }

    @Test(priority = 2)
    public void createJson2(){

        extentLogger = reports.createTest("Create with Map");

                response = given().accept(ContentType.JSON)
                                    .and().contentType(ContentType.JSON)
                                    .header("X-Master-Key", ConfigurationReaders.get("key"))
                                    .and().body(apiUtils.createMap("Worldddd"))
                            .when()
                                    .post()
                            .then()
                                    .statusCode(200)
                                    .and().contentType("application/json")
                                    .and().assertThat().body("record.record.sample",equalTo("Worldddd"))
                                    .extract().response();

        jsonPath=response.jsonPath();
        id_map = jsonPath.getString("metadata.id");
        System.out.println("id_pojo = " + id_pojo);
        response.prettyPrint();

    }


    @Test(priority = 3)
    public void updateJson(){

        extentLogger = reports.createTest("Update with Pojo");

        System.out.println("id_pojo = " + id_pojo);
        try{
            response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().pathParam("id",  id_pojo)
                    .header("X-Master-Key", ConfigurationReaders.get("key"))
                    .and().body(apiUtils.createJson("Murat"))
                    .when()
                    .put("/{id}")
                    .then()
                    .statusCode(200)
                    .and().contentType("application/json")
                    .and().assertThat().body("record.record.sample",equalTo("Murat"))
                    .extract().response();
        }catch (Exception e){
            e.printStackTrace();
        }
        response.prettyPrint();
    }

    @Test(priority = 4)
    public void getJson(){

        extentLogger = reports.createTest("Get");

        response = given().accept(ContentType.JSON)
                .and().pathParam("id", id_pojo)
                .and().header("X-Master-Key", ConfigurationReaders.get("key"))
                .get("/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .and().body("metadata.id", equalTo(id_pojo))
                .extract().response();

    }
    @Test(priority = 5)
    public void deleteJson(){

        extentLogger = reports.createTest("Delete");

                given().pathParam("id", id_pojo)
                        .and().header("X-Master-Key", ConfigurationReaders.get("key"))
                        .delete("/{id}")
                        .then().statusCode(200);
    }


}
