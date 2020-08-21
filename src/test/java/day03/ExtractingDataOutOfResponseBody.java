package day03;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ExtractingDataOutOfResponseBody {

    @DisplayName("Getting Movie Info")
    @Test
    public void test1(){

        //Provide badeURi in the test
        // Add query parameters --> apiKey - t= el padrino
        // Save the response into response object

        Response response = given()
                .log().all()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey" ,"ef00024f")
                .queryParam("t", "el padrino").
        when()
                .get();

        response.prettyPrint();
        System.out.println("Status is: " + response.statusCode() );

        //If we want to get single data out, for example just title, just year
        // We have to use path method of Response object and provide the JsonPath
        String title = response.path( "Title");
        System.out.println("Title is: "+ title);

        // Using same example - print out --> Year, Director, Actors and Ratings
        System.out.println("Year of the movie is: " + response.path("Year"));
        System.out.println("The Director of the movie is: " + response.path("Director"));
        System.out.println("The Actors of the movie are: " + response.path("Actors"));
        System.out.println("The Ratings of the movie are: " + response.path("Ratings[0].Source") +
                                                             " , " + response.path("Ratings[0].Value"));



    }

}
