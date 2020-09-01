package day9;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pojo.Spartan;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostSpartan_Data_Driven {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://34.229.100.122";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }


    @ParameterizedTest
    @CsvFileSource(resources = "/allSpartans.csv" , numLinesToSkip = 1)
    public void testAddSpartan(String csvName, String csvGender, long csvPhone){

        //We print at the beginning to make sure it works

//        System.out.println("csvName = " + csvName);
//        System.out.println("csvGender = " + csvGender);
//        System.out.println("csvPhone = " + csvPhone);

        // I need the post body
        Spartan spBody = new Spartan(csvName, csvGender, csvPhone) ;

        given()
                .contentType(ContentType.JSON)
                .body(spBody).
                when()
                .post("/spartans").
                then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!") )
                .body("data.name" , is( csvName) )
                .body("data.gender", is(csvGender) )
                .body("data.phone" , is(csvPhone))
                .body("data.id" , is(not(0)) ) ; // just checking the is not 0

    }






    }


