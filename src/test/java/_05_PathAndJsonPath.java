import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _05_PathAndJsonPath {

    @Test
    public void extractingPath()
    {
        String postCode=  // int e dönüşüm istediğimizde hata aldık.
                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        //.log().body()
                        .extract().path("'post code'")
                ;

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void extractingJosnPath()
    {
        int postCode=  // int e dönüşüm de JsonPath yönteminde hata almadık
                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        //.log().body()
                        .extract().jsonPath().getInt("'post code'")
                // tip dönüşümü otomatik, uygun tip verilmeli
                ;

        System.out.println("postCode = " + postCode);
    }


}
