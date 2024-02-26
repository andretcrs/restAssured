package util;

import net.datafaker.Faker;

public class GerarDadoUsuarioFaker {
    private static final Faker faker = new Faker();

    public static String gerarDadosUsuario() {
        return "{\n" +
                "    \"username\": \"" + faker.name().firstName() + "\",\n" +
                "    \"password\": \"" + "MinhaSenha" + faker.number().randomDigit() + "\"\n" +
                "}";

    }
}
