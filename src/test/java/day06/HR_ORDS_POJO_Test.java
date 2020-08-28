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
import pojo.Locations;
import pojo.Region;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HR_ORDS_POJO_Test {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";
    }

    @DisplayName("Testing the /locations/{location_id} endpoint")
    @Test
    public void testLocation(){

    Response response = given()
                               .accept(ContentType.JSON)
                               .pathParam("location_id", 1700)
                               .log().all().
                         when()
                               .get("/locations/{location_id}")
                               .prettyPeek() ;

        Locations l1 = response.as(Locations.class)  ;
        System.out.println("l1 = " + l1);
    }


    @DisplayName("Testing the /location endpoint")
    @Test
    public void testLocations(){

        Response response = get("/locations").prettyPeek() ;

       // Save all street address to List<String>
        List<String> addressList = response.jsonPath().getList("items.street_address");
        System.out.println("addressList = " + addressList);

        // Save all locations into List<Locations>
        List<Locations> locationsList = response.jsonPath().getList("items", Locations.class) ;

    //    for (Locations eachLocation : locationsList){
    //        System.out.println("eachLocation = " + eachLocation);
    //    }

        // Using Lamba expression
        locationsList.forEach( eachLocation -> System.out.println("eachLocation = " + eachLocation));

        // How do we assert we have 23 items in the list
        // Using hamcrest library assertion to check the list with certain size
        // import static org.hamcrest.MatcherAssert.assertThat;
        // and import static org.hamcrest.MatcherAssert.hasSize;
        assertThat(locationsList, hasSize(23)) ;


        // we want to get list of pojo but we only want to get those
       // data with country_id as US
        List<Locations> usLocations = response.jsonPath().getList("items.findAll { it.country_id=='US' }", Locations.class) ;
       //System.out.println("usLocations = " + usLocations);
        usLocations.forEach( eachLocation -> System.out.println(eachLocation));



    }

}
