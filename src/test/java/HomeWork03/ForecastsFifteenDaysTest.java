package HomeWork03;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import org.example.HomeWork03.accuweather.Error;

public class ForecastsFifteenDaysTest extends AccuweatherAbstractTest {

    @Test
    void testGetResponse() {
        Response response = given()
                .queryParam("apikey", getApiKey()).pathParam("locationKey", 100)
                .when()
                .request(Method.GET, getBaseUrl() + "/forecasts/v1/daily/15day/{locationKey}");

        Assertions.assertAll(() -> Assertions.assertEquals(401, response.statusCode()),
                () -> Assertions.assertEquals("Unauthorized", response.body().as(Error.class).getCode()),
                () -> Assertions.assertEquals("Api Authorization failed",
                        response.body().as(Error.class).getMessage()),
                () -> Assertions.assertEquals("/forecasts/v1/daily/15day/" +
                                "100?apikey=B2vovdWGdJgGaGVM4UG9pY2VExlUG6Me",
                        response.body().as(Error.class).getReference()));
    }
}