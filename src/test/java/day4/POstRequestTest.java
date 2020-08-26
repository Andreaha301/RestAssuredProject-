package day4;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class POstRequestTest {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://34.229.100.122" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Post request with String as body ")
    @Test
    public void testPostWithStringBody(){

        // Using Faker to generate random name
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        long randomPhoneNumber = faker.number().numberBetween(1000000000,9999999991l);
        System.out.println(randomPhoneNumber);

        String bodyString= "{\n" +
                "  \"name\": \""+randomName+"\",  \n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": "+randomPhoneNumber+"\n" +
                "}" ;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString).
        when()
              .post("/spartans").
        then()
              .log().all()
              .statusCode(201)
              .body("data.name", is(randomName));
    }

    @DisplayName("Posting with external json file")
    @Test
    public void testPostWithExternalFile(){

        //Create a file object that point to spartan.json you just added
        // so we can use this as body in post request
        File file1 = new File("spartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("From File"));

    }

    @DisplayName("Posting with Map object as body")
    @Test
    public void testPostWithMapAsBody(){

        //Add dependency jackson-databind

        //Create a Map<String, Object> as hashMap
        //Add name, gender, phone
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", "Andrea");
        bodyMap.put("gender", "Female");
        bodyMap.put("phone", 7868151443l);

        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap). // jackson-data-bind turn your java map object to json here
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("Andrea"));





    }



}
