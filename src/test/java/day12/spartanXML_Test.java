package day12;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class spartanXML_Test {


    /*
    Practice XML Response using GET /Spartans
    Create a class called SpartanXML_Test
    Add @BeforeAll method to set up your baseURI and basePath
    Create a Test and send GET /Spartans by specifying accept header as xml.
    Verify you get xml as response content type.
    Verify the first spartan name, id , gender .
 */


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }


    @DisplayName("Test XML Response from Get /Spartans")
    @Test
    public void testSpartansXML(){

        given().
                log().all().
                accept(ContentType.XML).
        when().
                get("/spartans").prettyPeek().
        then().
                //log().all().
                statusCode(is(200)).
                contentType(ContentType.XML).
                body("List.item[0].id", is("1")).
                body("List.item[0].name", is("Meade")).
                body("List.item[0].gender", is("Male"));


    }


    @DisplayName("XMLPath object to extract the data as a LISt")
    @Test
    public void testSpartanExtractData() {

        Response response = given()
                                    .log().all()
                                    .accept(ContentType.XML)
                            .when()
                                    .get("/spartans");

        //Getting XMLPath object just like we dis for JsonPath object
        XmlPath xmlPath = response.xmlPath();

        // Get the first spartan id and store it into the int
        int firstID = xmlPath.getInt("List.item[0].id");
        System.out.println("firstID = " + firstID);

        String firstName = xmlPath.getString("List.item[0].name") ;
        System.out.println("firstName = " + firstName);

        long firstPhone = xmlPath.getLong("List.item[0].phone");
        System.out.println("firstPhone = " + firstPhone);

        // Get the List of names
        List<String> names = xmlPath.getList("List.item.name");
        names.forEach(System.out::println);

    }


    @DisplayName("Same test - Different approach for the List")
    @Test
    public void test3() {

        Response response = given()
                                    .header("accept", "application/xml")
                           .when()
                                    .get("http://100.24.242.13:8000/api/spartans");

        // How to get a List of all the names
        XmlPath xmlPath = response.xmlPath();

        List<Integer> listOfIds = xmlPath.getList("List.item.id", Integer.class);
        System.out.println("listOfIds = " + listOfIds);

        //Assert the List size is ... - To be able to use assertThat() we need the below import
        // import static org.hamcrest.MatcherAssert.assertThat;
        //Assert above List has items
        assertThat(listOfIds, hasSize(131));
        // when we got the List<Integer> without specifying what type in getList method
        // somehow it can not decide the in the Hamcrest assertThat method it's a List<Integer>
        // so the fix was to provide class type for the getList method to make it obvious
        // like this  List<Integer> idList = xp.getList("List.item.id", Integer.class) ;
        assertThat(listOfIds, hasItems(100,15,131));


        // Get a List of Long from the phone numbers
        // first check the size is 479
        // check it has few phone numbers you specified
        // check every item is greaterThan 1000000000
        List<Long> phoneNums = xmlPath.getList("List.item.phone", Long.class);
        System.out.println("phone Nums = " + phoneNums);

        assertThat(phoneNums, hasSize(131) );
        //3018205382, 6497882550, 7868151443
        assertThat(phoneNums, hasItems(3018205382L, 6497882550L, 7868151443L) );

        // How to check every item in the collection match certain criteria
        // assertThat( 5 , greaterThan(3) );
        assertThat(phoneNums, everyItem( greaterThan(1000000000L)  )  );


        // Get a List of String from the names

        List<String> list=  xmlPath.getList("List.item.name");
        System.out.println("listOfNames = " + list);
        System.out.println("list = " + list.size()); // To get the size of the List
        list.forEach(System.out::println); // This is a forEach Loop instead of printing istOfMaps = [Meade, Andrea Updated, Fidole, etc]
        //It will print like this. See below
        //Meade
        //Andrea Updated
        //Fidole

        // find out how many unique names you have
        // Set interface define collection of unique elements
        // creating a HashSet object by copying everything from "list", duplicates are auto-removed
        Set<String> uniqueNames = new HashSet<>( list ) ;
        System.out.println("uniqueNames = " + uniqueNames);
        System.out.println("uniqueNames = " + uniqueNames.size()); // To get the size of uniqueNames


    }


}
