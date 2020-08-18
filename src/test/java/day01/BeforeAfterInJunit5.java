package day01;

import org.junit.jupiter.api.*;

public class BeforeAfterInJunit5 {

    // This method will run only once before the entire test
    //Same idea as @BeforeClass we learned previously
    // These are JUNIT5 Specific annotations
    @BeforeAll
    public static void setUp(){
        System.out.println("This run before All");
    }

    //Same idea as @BeforeMethod we learned previously
    // This is Junit5 specific annotation for same idea
    @BeforeEach
    public void beforeEachTest(){
        System.out.println("Running before the test");
    }

    @Test
    public void test1(){
        System.out.println("Test1 is running");
    }

    //@Disabled //Same idea as @ignore --> That we learned before
    @Test
    public void test2(){
        System.out.println("Test2 is running");
    }

    //Same idea as @AfterMethod that we learned previously
    @AfterEach
    public void afterEachTest(){
        System.out.println("Running after each test");
    }

    //This method will run only once after all the test
    // Same idea as @AfterClass annotation you learned previously
    @AfterAll
    public static void tearDown(){
        System.out.println("This run all the way at the end");
    }






}
