package day5;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan2;
import pojo.Spartan;

import static io.restassured.RestAssured.*;

public class JsonToPOJO_Practice {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://23.23.75.140" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;
    }

    @DisplayName("Json to Pojo GET /spartans/{id} ")
    @Test
    public void testSpartanJsonToSpartanObject(){

        int newID = SecureSpartanTest.createRandomSpartan();

        Response response = given()
                .auth().basic("admin","admin")
                .log().all()
                .pathParam("id", newID).
                        when()
                .get("/spartans/{id}")
                .prettyPeek() ;
        // as method from response
        // accept a class type to define what is the type object you are trying to store the response as
        //  Spartan2 class we created has all the fields that match the json fields from the response
        // So it will map all the json fields to the java fields and return Spartan2 POJO Object
        // in a simple word turn below json into Java object
        /*
         * {
         *     "id": 261,
         *     "name": "Elma",
         *     "gender": "Male",
         *     "phone": 9999999998
         * }
         *
         * into  new Spartan2(261,"Elma","Male",9999999998L) Java Object
         * so we can work with the data using java object directly
         */
        Spartan2 sp = response.as( Spartan2.class ) ;
        // above line is almost as if you are doing below line
//        Spartan2 sp = new Spartan2(261,"Elma","Male",9999999998L)
        System.out.println("sp = " + sp);

    }

    @DisplayName("Search Request and save 1st Result as Spartan Pojo")
    @Test
    public void gettingNestedJsonAsPojo(){

        Response response = given()
                                   .log().all()
                                   .auth().basic("admin", "admin")
                                   .queryParam("gender", "Male").
                            when()
                                   .get("/spartans/search") ;
                                   //.prettyPeek(); --> With prettyPeek it will print all the ID's with Male gender

        System.out.println("Response.statusCode() = " + response.statusCode() );

        //Printing out the first ID and name from response
        JsonPath jp = response.jsonPath() ;
        System.out.println("First ID: " + jp.getInt("content[0].id") );
        System.out.println( "First guy Name: " + jp.getString("content[0].name"));

        //Lets save the entire first json object in the array into Spartan2 POJO
        //getObject method accept 2 parameters
        //First parameter to provide the path to your json
        //second parameter to provide the type you want to store as: Spartan.class
        Spartan2 firstMaleSpartan = jp.getObject("content[0]", Spartan2.class);

        System.out.println("firstMaleSpartan = " + firstMaleSpartan);
        System.out.println("The Spartan name from POJO is = " + firstMaleSpartan.getName());
        System.out.println("The Spartan gender from POJO is = " + firstMaleSpartan.getGender());
        System.out.println("The Spartan phone from POJO is = " + firstMaleSpartan.getPhone());

    }






}
