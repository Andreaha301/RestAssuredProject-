package day9;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZipDataDriven {

    //Data Drive your GET / api.zippopotam.us/us/:state/:city
    //Create a csv file called state_city.csv
    //add 3 column  state , city , numberOfZipcodes
    //VA ,  Fairfax , 10 (send the request and prepare this number)
    //assert the state , city
    //and number of zipcodes you got from the response

    //api.zippopotam.us/us/:state/:city

    @ParameterizedTest
    @CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
    public void testStateCity(String expectedState, String expectedCity, int numberOfZipcodes){

        // This prints the info that we have in the state-city.csv file that I have in resources
        System.out.println("state = " + expectedState);
        System.out.println("city = " + expectedCity);
        System.out.println("numberOfZipcodes = " + numberOfZipcodes);

        //import io.restassured.response.Response;
        Response response = given()
                                    .pathParam("state", expectedState)
                                    .pathParam("city", expectedCity)
                                    .baseUri("http://api.zippopotam.us/us").
                             when()
                                    .get("/{state}/{city}")
                                    .prettyPeek() ;

        // assert the state and city match in the response
        JsonPath jp = response.jsonPath() ;
        // why do we use single quote? because there can not be a space in json path
        // we are using the '' to treat the 2 word as one
        System.out.println("state = " + jp.getString("'state abbreviation'")  ) ;
        System.out.println("city = "  + jp.getString("'place name'"));

        assertThat( jp.getString("'state abbreviation'"), is(expectedState) );
        assertThat( jp.getString("'place name'"), is(expectedCity) );

        //now we want to count how many items in jsonArray from the response
        // and validate that against our csv file expected numbers
        // since post code key has space in between, we have to add ' ' to treat it as one
        List<String> zipList = jp.getList("places.'post code'") ;
        System.out.println(zipList);

        //with this assertion we check the size of the List
        assertThat(zipList, hasSize(numberOfZipcodes));

        // How to count Json Array --> we add size() next to the path --> jp.getInt("places.size()")
        // If your jsonPath is pointing to an JsonArray yo can count them
        // by calling groovy method called size()
        System.out.println("The size of places array is: " + jp.getInt("places.size()"));

    }





}
