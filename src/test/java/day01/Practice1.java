package day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1 {

    // We are using spartan app that does not require a password
    //http://34.229.255.26/api/hello

    @Test
    public void test1(){

        //First make sure your request

        //RestAssured.get("URL HERE");

        //Since we did the static import
        //We can directly call the get method
        //After we sent the request, we can save the result into a type called Response
        // To do that we need  this import io.restassured.response.Response;
        Response response = get("http://34.229.255.26:8000/api/hello") ;

        //response object store all the information about the response we got
        //like status, statusline, body, headers and so on
        System.out.println("Status code of this response: " + response.statusCode() );


        //This is another way of getting status code starts with HTTP/1.1
        System.out.println("Getting status line of this response " + response.statusLine() );

        //In restAssured there are usually 2 methods that does same action
        // one directly with the name like response.statusCode()
        // another starting with getXXX like response.getStatusCode()
        System.out.println("Status code of this response: " + response.getStatusCode());

        //Getting the header out of our response from Postman
        //We can use response.header("Date") or response.getHeader("Date")
        System.out.println("Getting the value of the date header: " + response.header("Date"));

        //Getting Content-Type header value
        // We can use response.contentType() or response.header("Content-Type")
        System.out.println("Content-Type header value is: " + response.contentType());

        //Getting Content-Length header value
        System.out.println("Content-Length header value is:" + response.header("Content-Length"));

    }


    @Disabled("Testing /Hello endpoint")
    @Test
    public void testHello(){

        Response response = get("http://34.229.255.26:8000/api/hello") ;

        //Hover over and select More Actions --> Import statics
        assertEquals(200, response.statusCode() ) ;

        //Testing Content -Type header value is:  text/plain;charset=UTF-8
        assertEquals("text/plain;charset=UTF-8", response.contentType());
        // Or
        assertEquals("text/plain;charset=UTF-8", response.header("Content-Type"));

        //Testing The Content-Length header value is: 17
        // response.header("Content-Length") give us a string result so we need to do a String comparision
        assertEquals("17", response.header("Content-Length"));
    }


    @DisplayName("Testing Hello endpoint body")
    @Test
    public void testingHelloResponseBody(){

        //Get the body and assert the body equal to "Hello from Sparta"
        Response response = get("http://34.229.255.26:8000/api/hello") ;

        //Getting the body as String using asString method of Response object
        System.out.println( response.asString() );

        //Getting the body by calling body method --> We need to add .asString() to get "Hello from Sparta"
        // DO NOT USE toString() --> This method will not give us the content
        System.out.println( response.body().asString() );

        //Assert the body is Hello from Sparta, length is 17
        String helloBody = response.asString() ;
        assertEquals("Hello from Sparta", helloBody);
    }


    @DisplayName("Printing the response body using method")
    @Test
    public void printingBody(){
        Response response = get("http://34.229.255.26:8000/api/hello") ;

        //easy way to print the response body and return at the same time
        //Prints "Hello from Sparta"
        response.prettyPrint();

        System.out.println("------------------------------------------------");

        //prints the entire response, it will include all header, status code, body
        // Most importantly , it return same Response object rather than String like prettyPrint
        // it will enable you to call more method of response object after peeking
        // HTTP/1.1 200
        //Content-Type: text/plain;charset=UTF-8
        //Content-Length: 17
        //Date: Tue, 18 Aug 2020 18:39:05 GMT
        //
        //Hello from Sparta
        response.prettyPeek();

        System.out.println("------------------------------------------------");


        // I want to see entire response + save the status code into a variable in same statement
        int statusCode = response.prettyPeek().statusCode();
        System.out.println("Status code is: " + statusCode);


    }





}
