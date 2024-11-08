package HomeWork03;

import org.example.HomeWork03.accuweather.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;


public class ForecastOneDayTest extends AccuweatherAbstractTest {

    @Test
    void testGetResponse() {
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/1day/{locationKey}")
                .then().statusCode(200).time(lessThan(2000L))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals(1, weather.getDailyForecasts().size());
        System.out.println(weather);
    }
}