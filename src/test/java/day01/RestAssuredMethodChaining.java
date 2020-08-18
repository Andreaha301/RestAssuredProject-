package day01;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredMethodChaining {

    @DisplayName("First Atemp for chaining")
    @Test
    public void chaining_Methods_Test1(){

        // given().
        // some more information you want to provide other than request url
        // like header , query param , path variable , body

        //when().
        // you send the request GET POST PUT PATCH DELETE

        //then().
        // VALIDATE SOMETHING HERE

        when().
                get("http://34.229.100.122:8000/api/hello"). // --> Sending a request to this URL
        then().                                 //Assertions start HERE
                statusCode(200).               // --> Asserting status code is 200
        header("Content-Length", "17") ; // Asserting header "Content-Length" is "17"

    }



    @DisplayName("Using Hamcrest matcher for assertions")
    @Test
    public void testingWithMatcher(){

        when().
                get("http://34.229.100.122:8000/api/hello"). // --> Sending a request to this URL
                prettyPeek().
        then().                                 //Assertions start HERE
                statusCode(200).               // --> Asserting status code is 200
                header("Content-Length", equalTo("17")). // Asserting header "Content-Length" is "17"
                header("Content-Type", is("text/plain;charset=UTF-8")).
                body(is("Hello from Sparta"));
    }



    @DisplayName("Sending Request to Get All Spartan")
    @Test
    public void testAllSpartan(){

        // given part -- RequestSpecification
        // you can add information like header, query param, path var, body
        // if this request need authentication , it also goes to give section
        // when part --- Send Request(GET POST PUT DELETE)
        //                  --Get response
        // then part -- ValidatableResponse
        // this is where assertions start
        // you can chain multiple assertions
        // if any assertions fail , whole test fail.

        given(). //--> When using given() all our request specific information like header, query param, path var, body
                header("Accept", "application/xml").
        when().
                get("http://34.229.100.122:8000/api/spartans").
                prettyPeek().
        then().
                statusCode(200) ;
    }



}
