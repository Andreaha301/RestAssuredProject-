package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;


public class ZipCode_Test {

    @BeforeAll
    public static void setUp(){

        // THE URL MUST STAR WITH HTTP OR HTTPS
        RestAssured.baseURI = "http://api.zippopotam.us";
        RestAssured.basePath = "/us/" ;
    }

    @DisplayName("Zip to City Test")
    @Test
    public void testZipToCity(){

        given()
                .pathParam("zip", 33020)
                .log().all().
        when()
                .get("/{zip}").
        then()
                .log().all()
                .statusCode( is(200))
                .contentType(ContentType.JSON)
                //when we have space like in "post code" we have to use -->"'post code'" or "\"post code\""
                .body("'post code'", is("33020"))
                .body("country", is("United States"))
                .body("places[0].state", is("Florida"))
                //If we have two words in the places arrray we can do this below
                //Another option
                //.body("places[0].'place name' ", is("Hollywood"));
                .body("places[0][\"place name\"]", is("Hollywood"));

    }

    @DisplayName("City to Zip")
    @Test
    public void testCityToZip(){

        given()
               // .pathParam("state", "FL")
               // .pathParam("city", "Hollywood")
                .log().all().
        when()
               // .get("/{state}/{city}"). --> We can do it this way or the way below
               .get("/{state}/{city}" , "FL","Hollywood").
        then()
              .log().all()
              .statusCode( is(200))
              .body("'country abbreviation'", is("US"))
              .body("places[0].latitude", is("26.007"))
              // JsonPath hack for getting last item from Json Array
              // - 1 index indicate the last item, only works here not in Postman
              .body("places[-1].latitude", is("26.1457"))
              ;



    }




}
