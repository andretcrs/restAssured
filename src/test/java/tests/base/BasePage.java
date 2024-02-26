package tests.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import static tests.base.Properties.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class BasePage {
    @BeforeClass
    public static void setUp() {
        try {
            given()
                    .when()
                    .get("https://dummyjson.com/test")
                    .then()
                    .statusCode(200)
                    .body("status", is("ok"))
                    .body("method", equalTo("GET"));

        }  catch (AssertionError e) {
            System.out.println("Aplicação indisponível " + e.getMessage());
            throw e;

        }
            RestAssured.baseURI = Properties.APP_BASE_URL;
            RestAssured.basePath = APP_BASE_PATH;

            RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
            reqBuilder.setContentType(APP_CONTENT_TYPE);
            RestAssured.requestSpecification = reqBuilder.build();

            ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
            resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIME_OUT));
            RestAssured.responseSpecification = resBuilder.build();

            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
    }

