package day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://100.24.242.13:8000";
        RestAssured.basePath = "/api" ;
    }


    @DisplayName("Testing /spartans/{id}")
    @Test
    public void testSingleSpartan(){

        given()
                .log().all()
                .pathParam("id",8 ). // Path variable is unique and identify a single data
        when()
                .get("spartans/{id}").

        then()
                .log().body()
                .statusCode( is(200 )) ;
    }



    // Another way to do the above test

    @DisplayName("Testing2 /spartans/{id}")
    @Test
    public void testSingleSpartan2(){

        given()
                .log().all().
        when()
                .get("spartans/{id}" , 16).
        then()
                .statusCode( is(200))
                .log().body() ;
    }


    @DisplayName("Testing /spartans/{id} Body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all().
        when()
                .get("spartans/{id}" , 92).
        then()
                .log().all()
                .statusCode( is(200))
                //.body("JSON PATH", is("THE VALUE"))
                .body("id", is(92))
                .body("name", is("Caitlin"))
                .body("gender", is("Female"))
                .body("phone",is(6911121800L));

    }



}
