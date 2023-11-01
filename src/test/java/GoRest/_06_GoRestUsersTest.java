package GoRest;

import Model.User;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _06_GoRestUsersTest {


//    https://gorest.co.in/public/v2/users   POST
//
//    giden body
//    {
//        "name":"{{$randomFullName}}",
//            "gender":"male",
//            "email":"{{$randomEmail}}",
//            "status":"active"
//    }
//
//    Authorization
//    Bearer 787c83039875452ce6a32a7b73e7a10c1d4443273522652da0f13d5e76a27713
//
//
//    dönüşte 201
//    id extract

    Faker randomUreteci=new Faker();
    int userID=0;
    RequestSpecification reqSpec;

    @BeforeClass
    public void setup(){

        baseURI="https://gorest.co.in/public/v2/users";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization","Bearer 787c83039875452ce6a32a7b73e7a10c1d4443273522652da0f13d5e76a27713")
                .setContentType(ContentType.JSON)
                .build();
    }


    @Test(enabled = false)
    public void createUserJSon(){

        String rndFullName= randomUreteci.name().fullName();
        String rndEmail= randomUreteci.internet().emailAddress();

        userID=
                given() // giden body, token, contentType
                        .header("Authorization","Bearer 787c83039875452ce6a32a7b73e7a10c1d4443273522652da0f13d5e76a27713")
                        .body("{\"name\":\""+rndFullName+"\", \"gender\":\"male\", \"email\":\""+rndEmail+"\", \"status\":\"active\"}") // giden body
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
        ;
        System.out.println("userID = " + userID);
    }

    @Test
    public void createUserMAP(){

        String rndFullName= randomUreteci.name().fullName();
        String rndEmail= randomUreteci.internet().emailAddress();

        Map<String,String> newUser=new HashMap<>();
        newUser.put("name", rndFullName);
        newUser.put("gender","male");
        newUser.put("email", rndEmail);
        newUser.put("status", "active");

        userID=
                given() // giden body, token, contentType
                        .spec(reqSpec)
                        .body(newUser) // giden body
                        .contentType(ContentType.JSON)

                        .when()
                        .post("")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
        ;
        System.out.println("userID = " + userID);
    }

    @Test(enabled = false)
    public void createUserClass(){

        String rndFullName= randomUreteci.name().fullName();
        String rndEmail= randomUreteci.internet().emailAddress();

        User newUser=new User();
        newUser.name=rndFullName;
        newUser.email=rndEmail;
        newUser.gender="male";
        newUser.status="active";

        userID=
                given() // giden body, token, contentType
                        .header("Authorization","Bearer 787c83039875452ce6a32a7b73e7a10c1d4443273522652da0f13d5e76a27713")
                        .body(newUser) // giden body
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
        ;
        System.out.println("userID = " + userID);
    }


    @Test(dependsOnMethods = "createUserMAP")
    public void getUserById(){

        given()
                .spec(reqSpec)

                .when()
                .get(""+userID)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userID))
        ;
    }











}
