package day4;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.io.File;
import java.util.*;

public class PutAndPatch {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://34.229.100.122" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }



}
