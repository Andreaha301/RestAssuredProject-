package day9;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanRoleBaseAccessControl_NegativeTest {

    //We are doing a role based access control test
    //     -- for the Spartan app with username password
    //     for the credentials  user/user

    //       user should not be able to delete data
    //       user should not be able to post data
    //       user should not be able to update data

    //       all these 3 tests share same username and password
    //       and we can also add accept json result back
    //       and we want to log all the request

    //        all these test can share same response status as 403
    //        and all tests response content type is json
    //        and all test has Date header not null assertion
    //        and we want to see the log of all request

        @BeforeAll
        public static void init(){

            //IP that requires username: admin and password: admin "http://54.160.106.84:8000"
            // Now we are using username: user and password: user
            RestAssured.baseURI = "http://54.160.106.84";
            RestAssured.port = 8000;
            RestAssured.basePath = "/api";
        }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        // Building reusable request specification
        //Using RequestSpecBuilder class


        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all().
        when()
                .delete("/spartans/{id}" , 10).
        then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();
   }


    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){


        Spartan spartanObj = new Spartan("some name", "Male", 8888888888L) ;

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartanObj).
        when()
                .put("/spartans/{id}", 10).
        then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();

    }


    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

        Spartan spartanObj = new Spartan("some name", "Male", 8888888888L) ;

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartanObj).
        when()
                .post("/spartans").
        then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();

    }




}
