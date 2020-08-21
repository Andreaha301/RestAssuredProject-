package day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;

public class OpenMovieDB_Test {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://www.omdbapi.com";

    }

    @DisplayName("Test Movie API")
    @Test
    public void testMovies(){

        given()
                .log().all()
                .queryParam("apikey" ,"ef00024f")
                .queryParam("t", "el padrino")
                .queryParam("plot","full"). // This is coming from the parameters of the web API
        when()
                .get(). // If my URL is already complete, DO NOTHING
        then()
                .log().all()
                .statusCode(200)
                // If we are looking for an exact match use equalTo
                .body("Title", containsString("El padrino"))
                .body("Ratings[0].Value", is("4.9/10"))
                // If Ratings Array has more than one --> we can use "Ratings[-1].Value" to get the last one
                //.body("Ratings[-1].Value", is("VALUE"))
                ;




    }
}
