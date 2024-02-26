package tests.produtos;
import tests.base.BasePage;
import io.qameta.allure.Description;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BuscarProdutosComAutenticacaoTest extends BasePage {
    @Test
    @Description("Deve verificar se o endpoint de produtos está online")
    public void verificaApiProdutosOnline(){
            given()
                    .when()
                    .get("/products")
                    .then()
                    .statusCode(200)
                    .body(containsString("products"));

    }

    @Test
    @Description("Deve realizar a autenticação e consultar produtos")
    public void autenticarConsultarProdutos(){
            String token =
                    given()
                            .body(
                                    "{\n" +
                                            "    \"username\": \"kminchelle\",\n" +
                                            "    \"password\": \"0lelplR\"\n" +
                                            "}")
                            .when()
                            .post("/auth/login")
                            .then()
                            .statusCode(200)
                            .extract().path("token");
            given()
                    .headers("Authorization", "Bearer " +token )
                    .when()
                    .get("/auth/products")
                    .then()
                    .statusCode(200)
                    .body(containsString("iPhone 9"));
    }

    @Test
    @Description("Não deve permitir a autenticação sem token")
    public void deveRetornarStatusCode403SemToken(){
            given()
                    .when()
                    .get("/auth/products")
                    .then()
                    .statusCode(403)
                    .body("message", is("Authentication Problem"));
    }

    @Test
    @Description("Deve retornar mensagem informando quando o token estiver expirado")
    public void deveRetornarStatusCode401TokenExpirado(){
            given()
                    .headers("Authorization", "Bearer " )
                    .when()
                    .get("/auth/products")
                    .then().log().all()
                    .statusCode(401)
                    .body("message", is("Invalid/Expired Token!"));
    }

}
