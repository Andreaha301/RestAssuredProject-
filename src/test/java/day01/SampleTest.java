package day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

    @Test
    public void calculatorTest(){

        System.out.println("Hello World");

        //Assert 4 + 5 is 9
        assertEquals(9, 4+5);

        // How do we add error message if the assertion fail
        //assertEquals(10, 5+4, "Hey wrong result!!");

    }

    //You can add the display name for your test instead of the method name
    // providing the custom name for the test using @DisplayName(" ")
    @DisplayName("I am testing the name")
    @Test
    public void nameTest(){

        // Write a simple assertion
        // concatenate your first name and last name
        // and make assertion it's equal to your full name

        String firstName = "Andrea";
        String lastName = "Herrera";

        assertEquals("Andrea Herrera", firstName+" "+lastName);
    }




}




