package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public class XMLResponseTest {

    @BeforeAll
    public static void init() {

        // We're going to send a GET request to this endpoint
        // in this Rest API request, it use query parameter tp specify the response content type

        //https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
        RestAssured.baseURI = "https://vpic.nhtsa.dot.gov" ;
        RestAssured.basePath = "/api/vehicles";
    }

    @DisplayName("Testing GET / ")
    @Test
    public void testXML(){


        given()
                .log().all()
                .queryParam("format", "xml")
        .when()
                .get("/GetMakeForManufacturer/988")
        .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                // The body must match, asn the value is ALWAYS String in XML
                .body("Response.Count", is("2")) ;

    }



}
