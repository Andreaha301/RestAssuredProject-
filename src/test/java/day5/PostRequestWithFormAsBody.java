package day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PostRequestWithFormAsBody {

    //Posting in library app
    // body is not json, it's x-www-urlencoded-form-data

    // Post body, type x-www-urlencoded-form-data
    //email : librarian69@library
    //password : KNPXrm3S

    @BeforeAll
    public static void init(){

        //http://library1.cybertekschool.com/rest/v1
        //baseURI is http://library1.cybertekschool.com
        //basePath is /rest/v1

        // IN URL's if you do not see port, bc it's omitted bc it's so common
        // This two port are so common that are hiding http--> 80 , http--> 443
        //Anything other than those two ports, need to be explicitly set in the URL

        RestAssured.baseURI = "http://library1.cybertekschool.com" ;
        RestAssured.basePath = "/rest/v1" ;

    }

    @DisplayName("POST /login Request Test")
    @Test
    public void testLoginEndPoint(){

        given()
                .log().all()
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode(200)
                //we can not actually validate the token since it's dynamic
                //What we can validate though -- token field exists and it's not null -- we can also use is not empty
                .body("token", is(notNullValue() ));

    }

    @DisplayName("testing out the utility")
    @Test
    public void runningUtilityMethod(){

        System.out.println( loginAndGetToken("librarian69@library", "KNPXrm3S"));

    }



    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
    public static String loginAndGetToken(String username, String password){

        String token = "";

        Response response = given()
//                                .log().all()
                // explicitly saying the body content type is x-www-urlencoded-form-data
                .contentType(ContentType.URLENC)
                .formParam("email",username)
                .formParam("password", password ).
                        when()
                .post("/login") ;

        //token = response.path("token") ;  // this is using path method
        token = response.jsonPath().getString("token") ;
        return token ;
    }





}
