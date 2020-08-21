package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Always need this three imports
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanTest2 {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://34.229.100.122:8000";
        RestAssured.basePath = "/api" ;
    }


    @DisplayName("Get 1 Spartan Test")
    @Test
    public void testSingleSpartan(){

        // I want to log the request I sent so I see what is the URL, methods and so on
        given()
                .log().all(). // WITH .log().all --> we get all the information from our request including URL
                // .log().uri(). // With URI() we get only the URL "http://34.229.100.122:8000/api/spartans/20"
        when()
                .get("/spartans/20").
               // .prettyPeek().  // --> Using .prettyPeek() here I'll get the Spartan requested in this case /20
        then()
                .log().all()
                //.log().body() // WIth body() we get Spartan requested info
                //.log().ifValidationFails() --> This one will print if test fail
                .statusCode( is(200)) ;

    }



    //Task
    //Add another test for Hello endpoint by reusing the baseURI and basePath above
    //Specify you want to get a text result back
    //Check status code is 200
    //ContentType is Text
    //Body is "Hello from Sparta"
    @DisplayName("Testing /Hello again")
    @Test
    public void testHello(){

        given()
                .accept(ContentType.TEXT).  // specify you wnat to get a text result back
        when()
                .get("/hello")// Sending request baseURI + basePath + /hello
                .prettyPeek().
        then()
                .statusCode( is(200) )  // Checking status code 200
                .contentType( ContentType.TEXT) // Checking content type is text
                .body( equalTo( "Hello from Sparta")) ;// checking body
    }



    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartan(){

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
                //.baseUri("http://34.229.100.122:8000") //Alternative way of doing it
                //.basePath("/api")
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
