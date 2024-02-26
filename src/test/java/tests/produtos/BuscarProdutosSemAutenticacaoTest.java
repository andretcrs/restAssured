package tests.produtos;
import tests.base.BasePage;
import io.qameta.allure.Description;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;


public class BuscarProdutosSemAutenticacaoTest extends BasePage {

    @Test
    @Description("Deve listar produtos sem realizar a autenticação")
    public void deveListarProdutosSemAutenticar(){
            given()
                    .when()
                    .get("/products")
                    .then()
                    .statusCode(200)
                    .body(containsString("iPhone X"));
    }
    @Test
    @Description("Deve validar a quantidade de registros")
    public void deveValidarQuantidadeRegistros() {
            given()
                    .when()
                    .get("/products")
                    .then()
                    .statusCode(200)
                    .body("total", equalTo(100))
                    .body("skip", equalTo(0))
                    .body("limit", equalTo(30))
                    .body("products", hasSize(30));
    }
    @Test
    public void validarJsonSchemaProdutos() {
        given();
        get("/products").then().assertThat()
                .body(matchesJsonSchemaInClasspath("ProdutosJsonSchema.json"))
                .statusCode(200);
    }
}


