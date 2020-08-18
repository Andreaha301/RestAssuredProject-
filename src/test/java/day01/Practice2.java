package day01;

import org.junit.jupiter.api.Test;


// We need two imports for this assertions to work
// Hamcrest already come with RestAssured dependency
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class Practice2 {

    //Hamcrest library is a assertion library
    //to make the test more human readable
    // using lots of human readable matchers like something is(somethingElse)
    //Most importantly rest Assured use hamcrest matchers, when we chin multiple rest assured methods

    @Test
    public void test1(){

        //Assert 5+4 is 9
        int num1 = 5 ;
        int num2 = 4 ;

        // hamcrest library use the assertThat method for all assertions
        // hamcrest use built in matchers to do assertion
        //Couple common ones are:
           // is( some value )
          //  equalTo( some value) or optionally is ( equalTo( some value) )

        assertThat(num1+num2, is(9) );
        assertThat(num1+num2, equalTo(9));
        assertThat(num1+num2, is(equalTo(9)));

        // not ( value )
        // is( not (some value) )
        // not ( equalTo(11) )
        assertThat(num1+num2, not(11));
        assertThat(num1+num2, is( not(11) ) );
        assertThat(num1+num2, not( equalTo(11) ) );


        //Save your first name and last name into two variables
        //Test the concatenation result using hamcrest matcher
        String firstName = "Andrea " ;
        String lastName = "Herrera" ;

        assertThat(firstName+lastName, is("Andrea Herrera"));
        assertThat(firstName+lastName, equalTo("Andrea Herrera"));
        assertThat(firstName+lastName, is(equalTo("Andrea Herrera")));


        // How to check in caseInsensitive manner
        assertThat(firstName, equalToIgnoringCase("Andrea "));

        //How to ignore all whitespaces --> In this case the space in  String firstName = "Andrea " ;
        assertThat(firstName, equalToCompressingWhiteSpace("Andrea"));

        //We also can use this --> String matchers
        //equalToIgnoringCase()
        // equalToCompressingWhiteSpace()
        // containString, endsWith, startsWith, --> test string matching


    }



}
