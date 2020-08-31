package day8;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// The default test execution is in alphabetical order
//To specify in what order we want run our test, we need to add annotation--> @Order(1) - before every test and we need to add
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class) before the class

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testExecutionOrderInJunit5 {

    @Order(4)
    @Test
    public void testB(){

    }

    @Order(2)
    @Test
    public void testA(){

    }

    @Order(3)
    @Test
    public void testZ(){

    }

    @Order(1)
    @Test
    public void testK(){

    }


}
