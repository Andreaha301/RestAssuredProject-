package day8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class LibraryApp_DataDriven {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1" ;

    }


    //Create a csv file under resources folder called credentials.csv
    //    -- it has 2 column , username , password

    //    We will write a parameterized test for POST /login endpoint
    //    if the username and password is valid
    //    you should simply get 200 and the token field should not be null

    //Giving a name to our test in this format --> iteration 1 | username: firstColData, password: secondColData
    @ParameterizedTest(name = "iteration {index} | username: {0} , password: {1}")
    @CsvFileSource(resources = "/credentials.csv", numLinesToSkip = 1)
    public void testLoginCredentials(String username, String password){

        //System.out.println("username = " + username);
        //System.out.println("password = " + password);

        // So now lets make a post request to /login
        // content type is x-www-form-urlencoded
        // form data  email , password
        // check if the status code 200  if the password is correct
        // check the token field from the response is not null
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password" , password).
        when()
                .post("/login").
        then()
                .statusCode(200)
                .body("token", notNullValue() )
        ;


    }

    @AfterAll
    public static void cleanUp(){

        RestAssured.reset();
    }




    }
