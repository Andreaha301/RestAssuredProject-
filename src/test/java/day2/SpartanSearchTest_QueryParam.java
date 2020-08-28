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

public class SpartanSearchTest_QueryParam {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://34.229.100.122:8000";
        RestAssured.basePath = "/api" ;
    }


    // http://34.229.100.122:8000/api/spartans/search?gender=Male&nameContains=Andrea
    // Everything after the ? is Endpoint --> ?gender=Male&nameContains=Andrea
    @DisplayName("Testing /spartan/search Endpoint")
    @Test
    public void testSearch(){

        given()
                .log().all()
                .queryParam("gender" ,"Male")
                .queryParam("nameContains", "Andrea").
        when()
                //.get("spartans/search?gender=Male&nameContains=Andrea").
                .get("spartans/search").
        then()
                .log().all()
                .statusCode(200)
                .body("numberOfElements", is(2));




    }


}
