package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class PutRequestExample {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://100.24.242.13" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;
    }


    @DisplayName("Updating existing Data")
    @Test
    public void updateSpartan(){

        String updatedBody = "{\n" +
                "        \"id\": 205,\n" +
                "        \"name\": \"Wonder Woman\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 3018205382\n" +
                "    }" ;
        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .log().all().
        when()
                .put("/spartans/{id}", 14).
        then()
                .log().all()
                .statusCode(204);
    }


    @DisplayName("Testing DELETE")
    @Test
    public void testDelete(){

        when()
                .delete("/spartans/{id}", 14).
         then()
                .statusCode(204) ;
               // .body( is(empty()));

    }



}
