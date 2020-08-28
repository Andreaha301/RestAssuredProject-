package day06;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

       /*
        Go to newsAPI.org and get an API key
        each request put the key you got in Authorization header
        header name Authorization , value Bearer long_token_here

        send request to the endpoint /top-headlines?country=us
        get all the author name if the source id is not null
        print the list of author name

        */


public class NewsAPI {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://newsapi.org";
        RestAssured.basePath = "/v2";

    }


    @Test
    public void authorsWithId(){

        Response response = given()
                                    .header("Authorization", "Bearer a88d567ed1d848e6ae2c9865a647f3ae")
                                    .queryParam("country", "us")
                                    .log().all().
                           when()
                                   .get("/top-headlines");

        JsonPath jp = response.jsonPath();

        List<String> authors = jp.get("articles.findAll{it.source.id != null}.author");
        System.out.println("authors = " + authors);
    }


}
