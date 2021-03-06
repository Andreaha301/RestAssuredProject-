package day03;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Practice1 {

    @BeforeAll
    // in JUnit 5 @BeforeAll @AfterAll is static method
    // if we do not make it static it does not work because that's how it's defined in the doc
    public static void init(){
        // example of setting the port separately from baseURI
        RestAssured.baseURI = "http://100.24.242.13" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;
    }

    @DisplayName("simple test")
    @Test
    public void test1(){

        given()
                .log().all()
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .statusCode(200);



    }




}
