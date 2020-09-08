package day12;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class OpenMovieDB_XML {

    @Test
    public void testMovieXML(){

        //http://www.omdbapi.com/?apikey=ef00024f&t=10 Things I hate about you&r=xml

        given()
                .log().all()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey", "ef00024f")
                .queryParam("r", "xml")
                .queryParam("t", "10 Things I hate about you").
        when()
                .get().
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                // The Result has only 2 elements, parent: root -- child: movie
                // all the movie data is stored under Movie attributes
                .body("root.movie.@title", is("10 Things I Hate About You"))
                .body("root.movie.@year", is("1999"))
                .body("root.movie.@rated", is("PG-13"))
                .body("root.movie.@released", is("31 Mar 1999")) ;

    }


    @DisplayName("Getting many movies and extracting attributes to the list")
    @Test
    public void testGettingAttributeAsList(){

        // in open movie DB
        // the query parameter called s = "some movie name"
        // will return many movies match that names
        // and we are using that query parameter
        // http://www.omdbapi.com/?apikey=YourKeyHere&r=xml&s=Superman

        Response response = given()
                .log().all()
                .baseUri("http://www.omdbapi.com/")
                .queryParam("apikey", "be8cd0d1")
                .queryParam("r","xml")
                .queryParam("s","superman").
                        when()
                .get() ;

        XmlPath xp = response.xmlPath();
        // get the list of movie title
        // getting list of attribute belong to each elements in xml nodes/elements
        List<String> supermanMovieList = xp.getList("root.result.@title") ;
        System.out.println("supermanMovieList = " + supermanMovieList);
        supermanMovieList.forEach(System.out::println);



    }







}
