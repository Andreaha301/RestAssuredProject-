package day2;

//Always need this three imports

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;

public class SpartanTest {


    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartan(){

        //String spartanURL = "http://34.229.100.122:8000/api/spartans";

        //How to set base URl, port, base path so I can reuse them
        RestAssured.baseURI = "http://34.229.100.122:8000";
        RestAssured.basePath = "/api" ;

        // This way it will create the request URL as is
        // baseURI + basePath + what us after .get("/spartans").

        // I want to explicitly specify I want JSON response from this result
        given()
                .header("Accept", "application/json").
        when()
                .get("/spartans").
        then()
                .statusCode( is(200) ) ;

    }


    @DisplayName("Get All Spartan 2")
    @Test
    public void testAllSpartan2(){

        //How to send the same request specifying the accept header is  application/json
        // User baseURI + basePath and check status 200, Content-Type header is JSON

        given()
                .baseUri("http://34.229.100.122:8000") //Alternative way of doing it
                .basePath("/api")
                //.accept("application/json"). --> "accept using value
                .accept(ContentType.JSON). // --> "accept" using enum
        when()
                .get("spartans").
        then()
                .statusCode( is(200) )
                //.contentType("application/json;charset=UTF-8") --> One way to get Content-Type
                //.contentType( is("application/json;charset=UTF-8")) --> Second way to get Content-Type
                // Easiest way for Content-Type is using Content-Type enum
                .contentType(ContentType.JSON) ; //--> Third way to get Content-Type

    }







}
