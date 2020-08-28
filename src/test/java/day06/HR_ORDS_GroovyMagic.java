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

public class HR_ORDS_GroovyMagic {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";
    }


    @DisplayName("Testing the /Employees endpoint")
    @Test
    public void testEmployees(){

        Response response = get("/employees");//.prettyPeek() ;

        JsonPath jp = response.jsonPath();

        //Print all id by getting a List
        System.out.println("All Id's: "+ jp.getList("items.employee_id"));

        //Print first id and last id
        System.out.println("First Id is: "+ jp.getInt("items[0].employee_id"));
        System.out.println("Last Id is: "+ jp.getInt("items[-1].employee_id"));

        // Get all the id's from first one till fifth
        System.out.println("From the first till the fifth: " + jp.getList("items[0..4].employee_id"));

        // Get all last name from index 10-15
        System.out.println("Last name from 10 to 15: "+ jp.getList("items[10..15].last_name"));
        // Get all last name from index 10-15 in UpperCase
        System.out.println(jp.getList("items[10..15].last_name").toString().toUpperCase());


        //  get the employee first_name with employee id of 105
        //  find   and find all  where you can specify the criteria to restrict the result
        //  find method will return single value that fall into the criteria compared to findAll will return a list
        //  find { it.employee_id == 105 }
        // what does the word <it> means -->> eachItem in your json array
        // just like in your for each loop you have to specify a name ,
        // the name <it> represent each item in the json array
        String result = jp.getString("items.find { it.employee_id == 105 }.last_name    ") ;
        System.out.println("result = " + result);

        //Finding the salary of employee with email value: LDEHAAN
        int salary = jp.getInt("items.find{ it.email == 'LDEHAAN'}.salary");
        System.out.println("salary = " + salary);

        //FindAll will get all the result that match the criteria and return it as a List
        // Save all the first_names of the employees with salary more than 15000
        List<String> richPeople = jp.getList("items.findAll {it.salary > 15000}.first_name");
        System.out.println("richPeople = " + richPeople);

        // Find out all the phone_number in department_id 90
        List<Integer> phones90 = jp.get("items.findAll{it.department_id==90}.phone_number");
        System.out.println("Phone #s of department 90 employees =" + phones90);










    }




}
