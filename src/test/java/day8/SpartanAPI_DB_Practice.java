package day8;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class SpartanAPI_DB_Practice {

    /**
     * The dev just implemented the search endpoint
     * and want it to be tested to make sure it's actually
     * returning the correct result from the database
     *
     *    GET /spartans/search?gender=Female&nameContains=a
     *  we want to test the count of result is correct
     *  for numberOfElements field.
     *
     *  The Database query to get the count is :
     *  // all the females that have a in their name , case insensitive
     *   -- This is how we get the data with case insensitive manner
     *      SELECT * FROM SPARTANS
     *       WHERE LOWER(gender) = 'female'
     *       and LOWER(name) LIKE '%a%' ;
     *
     */

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://34.229.255.26" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;

        DB_Utility.createConnection("spartan1");
    }


    @DisplayName("Testing out my DB Connection")
    @Test
    public void testDB(){

        //DB_Utility.runQuery("SELECT * FROM SPARTANS");
        //DB_Utility.displayAllData();

        // 1.- Run this query so we can use it for expected result
        String query = "SELECT * FROM spartans  " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%'  " ;
        DB_Utility.runQuery(query) ;
        DB_Utility.displayAllData();

        // 2.- Get the row count so we can see how many data returned
               // and use it for expected result in the next test
         int expectedResult = DB_Utility.getRowCount() ;
        System.out.println("expectedResult = " + expectedResult);
    }



    @DisplayName("Testing /spartans/search Endpoint and Validate against DB")
    @Test
    public void testSearch(){

        // make a request to GET /spartans/search
        // using query parameter gender Female  nameContains a

        Response response = given()
                                    .log().all()
                                    .queryParam("gender","Female")
                                    .queryParam("nameContains", "a").
                            when()
                                    .get("/spartans/search")
                                    .prettyPeek();

        int resultCount = response.path("numberOfElements") ;
        System.out.println("resultCount = " + resultCount);

        String query = "SELECT * FROM spartans  " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%'  " ;

        DB_Utility.runQuery(query);
        int expectedResult = DB_Utility.getRowCount() ;

        // This is using JUnit assertion, we ca use hamcrest if we want
        assertEquals(expectedResult, resultCount);
    }


    @DisplayName("Testing /spartans/search Endpoint and Validate against DB for all IDs")
    @Test
    public void testSearchVerifyAllID(){

        // make a request to GET /spartans/search
        // using query parameter gender Female  nameContains a

        Response response = given()
                                    .log().all()
                                    .queryParam("gender","Female")
                                    .queryParam("nameContains", "a").
                             when()
                                    .get("/spartans/search")
                                    .prettyPeek();

        // We want to store the id list as List<String> rather than List of Integer so we can compare easily
        // with the List<String> we got from DB Response , and no parsing needed
        // so we used the getList method that accept 2 parameters
        // the jsonpath to get the list and the Data type of the List you want ! -->> String.class
        List<String> idListFromResponse = response.jsonPath().getList("content.id" , String.class) ;

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);

        List<String> idListFromDB =  DB_Utility.getColumnDataAsList(1) ;

        assertEquals( idListFromResponse.size() , idListFromDB.size() ) ;

        // how to assert 2 list have same content
        assertEquals(idListFromDB, idListFromResponse);

    }


    @AfterAll
    public static void destroy(){

        RestAssured.reset();
        DB_Utility.destroy();
    }

}


