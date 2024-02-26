package tests.produtos;

import io.qameta.allure.Description;
import org.junit.Test;
import tests.base.BasePage;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static util.GerarDadoProdutosFaker.gerarDadosProdutos;
import static util.GerarDadoProdutosInteiroFaker.gerarDadosProdutosInteiro;

public class AdicionarProdutosTest extends BasePage {
    @Test
    @Description("Deve validar se endpoint de produtos está online")
    public void validarEndPointProductsOnline(){
            gerarDadosProdutos();
            given()
                    .when()
                    .get("/products")
                    .then()
                    .statusCode(200);

    }
    @Test
    @Description("Deve adicionar o produto com sucesso.")
    public void adicionarProdutoPost(){
          gerarDadosProdutos();
            given()
                    .body(gerarDadosProdutos())
                    .when()
                    .post("/products/add")
                    .then()
                    .statusCode(200);

    }
    @Test
    @Description("Validar tipo de dados enviados no Json")
    public void adicionarProdutoTitleInteiro(){
            gerarDadosProdutosInteiro();
            given()
                    .body(gerarDadosProdutos())
                    .when()
                    .post("/products/add")
                    .then()
                    .statusCode(200);
    }
    @Test
    @Description("Não deve adicionar produto com verbo http get")
    public void adicionarProdutoGet(){
            gerarDadosProdutos();
            given()
                    .body(gerarDadosProdutos())
                    .when()
                    .get("/products/add")
                    .then()
                    .statusCode(404)
                    .body("message", is("Product with id 'add' not found"));
    }
}
