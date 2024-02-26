package tests.usuarios;

import tests.base.BasePage;
import io.qameta.allure.Description;
import org.junit.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ConsultarUsuarioTest extends BasePage {
    public static String username = "atuny0";
    public static String password = "9uQFF1Lh";

    @Test
    @Description("Deve validar se o endpoint de usuarios está online")
    public void validarEndPointUsuariosOnline() {
            given()
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200);
    }
    @Test
    @Description("Deve retornar usuário")
    public void consultarUsuario(){
            given()
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .body(containsString(username))
                    .body((containsString(password)));
    }

    @Test
    @Description("Deve validar quantidade de registros")
    public void deveValidarQuantidadeRegistros() {
            given()
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .body("users", hasSize(30));

    }
    @Test
    @Description("Deve validar parametros no footer")
    public void deveValidarParametrosFooter() {

            given()
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200)
                    .body("total", equalTo(100))
                    .body("skip", equalTo(0))
                    .body("limit", equalTo(30))
                    .body("users", hasSize(30));

    }

    @Test
    @Description("Deve validar o total de skip e limit")
    public void deveValidarTotalSkipLimit() {

            given()
                    .get("/users?skip=10&limit=20")
                    .then()
                    .body("total", equalTo(100))
                    .body("skip", equalTo(10))
                    .body("limit", equalTo(20));
            expect().statusCode(200).when().get("/users");
    }

    @Test
    @Description("Deve validar o limite de paginação")
    public void deveValidarLimitePaginacao() {

            given()
                    .get("/users?limit=150")
                    .then()
                    .body("users", hasSize(100));

            expect().statusCode(200).when().get("/users");
    }

}

