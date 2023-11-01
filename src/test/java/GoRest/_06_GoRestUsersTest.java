package GoRest;

import Model.User;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

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
//    Bearer 7855808b298b71ca4af40039bc33e831f19046ece1a1f05c6ca13fba632b82e6
//
//
//    dönüşte 201
//    id extract

    Faker randomUreteci=new Faker();
    int userID=0;

    @Test
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

    @Test
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
                        .header("Authorization","Bearer 7855808b298b71ca4af40039bc33e831f19046ece1a1f05c6ca13fba632b82e6")
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












}
