package day7;

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

public class GOT_TestAPI {

    // Make a request to GOT all characters endpoint :
    //GET https://api.got.show/api/book/characters

    //then store all the character name whose house value is House Stark
    //Hint :
    //The response is top level json array so you will not need to provide json path
    //The method will look like this getList(" findAll { condition here }.theFieldNameHere")

    @DisplayName("Getting All Members of House Stark")
    @Test
    public void testingGOT(){

        Response response = given()
                                   .baseUri("https://api.got.show")
                                   .basePath("/api/book").
                            when()
                                   .get("/characters") ;

        // Because House and Name are in top level --> we don't need Json path object. We're not providing a path
        // like  jp.getList("articles.author")
        // JsonPath jp = response.jsonPath() ;

        List<String> houseStarkList =
                response.jsonPath().getList(" findAll {it.house=='House Stark'}.name ") ;
        System.out.println("houseStarkList = " + houseStarkList);

        // you list should have size 76 ;
        assertThat( houseStarkList , hasSize(76) ) ;

        //Check the list has item Eddard Stark
        assertThat(houseStarkList, hasItem("Eddard Stark"));

        // Check the list has items Robb Stark, Lyanna Stark and Arya Stark
        assertThat(houseStarkList, hasItems("Robb Stark", "Lyanna Stark", "Arya Stark"));







    }




}
