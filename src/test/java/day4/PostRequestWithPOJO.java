package day4;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PostRequestWithPOJO {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://100.24.242.13" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }


    @DisplayName("Post request using POJO")
    @Test
    public void testPostBodyWithPojo(){

        Spartan sp1 = new Spartan("Irina Li", "Female", 1231231231);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1). // jackson-data-bind turn your java map object to json here
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("Irina Li"));

    }


}
