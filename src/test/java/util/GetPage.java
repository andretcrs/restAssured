package util;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetPage {

    public Response get(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint);
    }

    public Response getById(String endpoint, int id) {
        String fullEndpoint = endpoint + "/" + id;
        return get(fullEndpoint);
    }
}


