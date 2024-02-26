package tests.produtos;


import tests.base.BasePage;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import util.GetPage;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class BuscarProdutoPorIdTest extends BasePage {

    private final GetPage getPage = new GetPage();

    private String getRandomProductId() {
        return String.valueOf((int) (Math.random() * 100) + 1);
    }

    @Test
    @Description("Deve encontrar o produto por um Id existente")
    public void deveEncontrarProdutoIdExistente() {
            String productId = getRandomProductId();

            Response response = getPage.get("/products/" + productId);

            response.then().statusCode(200)
                    .body("id", equalTo(Integer.parseInt(productId)))
                    .body("title", notNullValue())
                    .body("description", notNullValue())
                    .body("price", notNullValue())
                    .body("discountPercentage", notNullValue())
                    .body("rating", notNullValue())
                    .body("stock", notNullValue())
                    .body("brand", notNullValue())
                    .body("category", notNullValue())
                    .body("thumbnail", notNullValue())
                    .body("images", notNullValue());
    }

    @Test
    @Description(" Nã Deve encontrar o produto por um Id existente")
    public void deveRetornarErroQuandoIdNaoExistir() {

            String productId = "0";
            Response response = getPage.get("/products/" + productId);
            response.then().statusCode(404)
                    .body("message", equalTo("Product with id '0' not found"));
    }

    @Test
    @Description("Deve validar tipo de dados")
    public void devoValidarTiposDeDados() {
            String productId = getRandomProductId();

            Response response = getPage.get("/products/" + productId);

            response.then().statusCode(200)
                    .body("id", instanceOf(Integer.class))
                    .body("title", instanceOf(String.class))
                    .body("description", instanceOf(String.class))
                    .body("price", instanceOf(Number.class))
                    .body("discountPercentage", instanceOf(Number.class))
                    .body("rating", instanceOf(Number.class))
                    .body("stock", instanceOf(Number.class))
                    .body("brand", instanceOf(String.class))
                    .body("category", instanceOf(String.class))
                    .body("thumbnail", instanceOf(String.class))
                    .body("images", instanceOf(List.class));

    }
}
