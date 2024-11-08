package HomeWork03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.example.HomeWork03.accuweather.Error;

public class ForecastsTenDaysTest extends AccuweatherAbstractTest {

    @Test
    void testGetTenDayForecastResponse() throws InterruptedException {

        Error weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 100)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}")
                .then().statusCode(401)
                .time(lessThan(2000L))
                .extract().response().body().as(Error.class);

        Assertions.assertEquals("Unauthorized", weather.getCode());
        Assertions.assertEquals("Api Authorization failed", weather.getMessage());
        Assertions.assertEquals("/forecasts/v1/daily/10day/" + "100?apikey=B2vovdWGdJgGaGVM4UG9pY2VExlUG6Me", weather.getReference());
        ;
    }
}


