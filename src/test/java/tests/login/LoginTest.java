package tests.login;

import tests.base.BasePage;
import io.qameta.allure.Description;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static util.GerarDadoUsuarioFaker.gerarDadosUsuario;

@FixMethodOrder

public class LoginTest extends BasePage {

    @Test
    @Description("Deve realizar login quando informado os dados corretos do usuário")
    public void deveRetornarStatus200DadosCorretos() {

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
                    .body("firstName", is("Jeanne"));
    }

    @Test
    @Description("Deve retornar erro ao tentar realizar o login com usuário incorreto")
    public void deveValidarUsuarioInvalido() {

            gerarDadosUsuario();
            given()
                    .body(gerarDadosUsuario())
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(400)
                    .body("message", is("Invalid credentials"));
    }

    @Test
    @Description("Deve retornar erro ao tentar realizar o login com a senha incorreto")
    public void deveValidarSenhaInvalida() {
            given()
                    .body(
                            "{\n" +
                                    "    \"username\": \"kminchelle\",\n" +
                                    "    \"password\": \"1234\"\n" +
                                    "}")
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(400)
                    .body("message", is("Invalid credentials"));
    }
    @Test
    @Description("Deve retornar erro ao tentar enviar username como inteiro")
    public void validarUsernameInteiro() {
            given()
                    .body(
                            "{\n" +
                                    "    \"username\": 123456,\n" +
                                    "    \"password\": \"0lelplR\"\n" +
                                    "}")
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(500)
                    .body("message", is("username.toLowerCase is not a function"));
    }

    @Test
    @Description("Deve retornar erro ao tentar enviar password como inteiro")
    public void validarPasswordInteiro() {
            given()
                    .body(
                            "{\n" +
                                    "    \"username\": 123456,\n" +
                                    "    \"password\": \"0lelplR\"\n" +
                                    "}")
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(500)
                    .body("message", is("username.toLowerCase is not a function"));
    }

    @Test
    @Description("Deve retornar erro ao enviar dados de usuario e senha em ordem invertida no objeto")
    public void validarOrdemInvertidaObjeto() {
            given()
                    .body(
                            "{\n" +
                                    "    \"password\": \"0lelplR\",\n" +
                                    "    \"usarname\": \"kminchelle\"\n" +
                                    "}")
                    .when()
                    .post("/auth/login")
                    .then()
                    .statusCode(400)
                    .body("message", is("Invalid credentials"));
    }
    @Test
    public void validarJsonSchemaLogin() {
        given();
        post("test/auth/login").then().assertThat()
                .body(matchesJsonSchemaInClasspath("LoginJsonSchema.json"))
                .statusCode(200);
    }

}