package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractingDataUsingJsonPathMethods {

    @DisplayName("Getting Movie Info")
    @Test
    public void test1(){

        Response response = given()
                .log().all()
                .queryParam("apikey" ,"ef00024f")
                .queryParam("t", "el padrino").
        when()
                .get("http://www.omdbapi.com");

        // The JsonPath is a class that have a lot of methods
        // To grt the body data in different format or data types
        // We get this object by calling the method of Response object called  .jsonPath()
        JsonPath jp = response.jsonPath();

        //Get the Title as String
        String title = jp.getString("Title") ;
        System.out.println("Title is: " + title);

        //Get the year as int
        int year = jp.getInt("Year") ;
        System.out.println("Year of the movie is: " + year);

        //Getting the Director
        String director = jp.getString("Director");
        System.out.println("The Director is: " + director);

        //Getting the first rating source as we did in previous class
        String rating1Src = jp.getString("Ratings[0].Source");
        System.out.println("The Source of the first rating is: " + rating1Src);

        //Storing the entire response as Map<String, Object>
        // Since our result is a Json Object with key  value pair
        // We can directly call getMap method and provide the path
        //Store the whole thing into a Map Object
        Map<String, Object> responseMap = jp.getMap("");
        System.out.println("responseMap = " + responseMap);

        // Printing out the Awards key from above Map
        System.out.println("Awards are " + responseMap.get("Awards"));

        //Getting The first Ratings as a Map
        Map<String, Object> firstRatingMap = jp.getMap("Ratings[0]");
        // This Map will print --> firstRatingMap = {Source=Internet Movie Database, Value=4.9/10}
        System.out.println("firstRatingMap = " + firstRatingMap);

        // above code is doing below when getMap method is being called
//        Map<String,Object> manualMap = new HashMap<>();
//        manualMap.put("Source","Internet Movie Database");
//        manualMap.put("Value","6.3/10");

        // I want to store all the source of ratings into the list of String
        // your result should be ["Internet Movie Database","Rotten Tomatoes","Metacritic"]
        // JsonPath getList method will store items in jsonArray into the list

        // get me the list of Source field of the Ratings jsonArray from the response
        List<String> sourceList  =   jp.getList("Ratings.Source") ;
        System.out.println("sourceList = " + sourceList);







    }

}
