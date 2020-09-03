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

        RestAssured.baseURI = "http://100.24.242.13" ;
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
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Carola Marchani"))

        ;
    }

     // Please create another test
    // make a post request , store the response Object
    // save the id into int variable
    // save the name into String
    // print them out.
    @DisplayName("Practice extracting data")
    @Test
    public void postRequestExtractingData(){

        String myBodyData = "{\n" +
                "  \"name\": \"Carola Marchani\",  \n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 3018205382\n" +
                "}" ;

        Response response = given()
                                   .contentType(ContentType.JSON)
                                   .body(myBodyData)
                                   .log().all().
                            when()
                                   .post("/spartans")
                                   .prettyPeek() ;

        System.out.println("The id is " + response.path("data.id"));
        System.out.println("The name is " + response.path("data.name"));

        JsonPath jp = response.jsonPath() ;
        System.out.println("ID using jsonPath " + jp.getInt("data.id"));
        System.out.println("Name using jsonPath " + jp.getString("data.name"));



    }




}
