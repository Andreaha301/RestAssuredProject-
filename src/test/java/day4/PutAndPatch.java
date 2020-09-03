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

public class PutAndPatch {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://34.229.255.26" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Put request body as a Map")
    @Test
    public void testPutRequestWithMap(){

        //PUT request to update Spartan with id 209
        // name : put with map, gender : MAle, phone : 1231231231
        //Status code 204

        // How do we know it's updates since it does not have body in request
        //we can make another GET request right after this and assert the body

        String randomName = new Faker().name().firstName();

        Map<String, Object> updatedBody = new LinkedHashMap<>();
        updatedBody.put("name", randomName);
        updatedBody.put("gender","Male");
        updatedBody.put("phone",8853352000l)  ;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(updatedBody). // jackson-data-bind turn your java map object to json here
        when()
                .put("/spartans/{id}", 209).
        then()
                .log().all()
                .statusCode(204)
                ;


    }



}
