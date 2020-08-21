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
import static org.hamcrest.Matchers.is;

public class PostRequestExample {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://34.229.100.122" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Testing POST /api/spartans")
    @Test
    public void testAddSpartan(){

       String myBodyData = "{\n" +
               "  \"name\": \"Carola Marchani\",  \n" +
               "  \"gender\": \"Male\",\n" +
               "  \"phone\": 3018205382\n" +
               "}" ;
        System.out.println(myBodyData);

        given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode( is(201))
                .contentType(ContentType.JSON);


    }




}
