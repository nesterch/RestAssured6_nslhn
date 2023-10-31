package GoRest;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _06_GoRestUsersTest {

    Faker randomUretici = new Faker();
    int userID = 0;

    @Test
    public void createUser() {

        String rndFullName = randomUretici.name().fullName();
        String rndEmail = randomUretici.internet().emailAddress();


        userID =

                given()
                        //giden body ,token, contentType

                        .header("Authorization", "Bearer 7855808b298b71ca4af40039bc33e831f19046ece1a1f05c6ca13fba632b82e6")
                        .body("{\"name\":\"" + rndFullName + "\", \"gender\":\"male\", \"email\":\"" + rndEmail + "\", \"status\":\"active\"}") // giden body
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")


                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;

        System.out.println("userID=" + userID);
    }
}
