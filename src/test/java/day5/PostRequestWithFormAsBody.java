package day5;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

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



}
