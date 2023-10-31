import Model.Location;
import Model.Place;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.Socket;

import static io.restassured.RestAssured.*;

public class _03_ApiTestPOJO {
    // POJO : JSON nesnesi : locationNesnesi

    @Test
    public void extractJsonAll_POJO() {
        // Ogrenci ogr1= new Ogrenci();
        Location locationNesnesi =
                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().body().as(Location.class) // Location Kalıbına göre
                ;       // dönen body bilgisi Location Class kalıbıyla çevir.


        System.out.println("locationNesnesi.getCountry()=" +
                locationNesnesi.getCountry());

        System.out.println("locationNesnesi.getPlaces()=" +
                locationNesnesi.getPlaces());

        for (Place p : locationNesnesi.getPlaces())
            System.out.println("p=" + p);

        // JSonaDonustur(locationNesnesi);
        //Json.Serialise(locationNesnesi); bende tersine deserialize yaptim.
        //yani nesne yi elde ettim


    }


}


