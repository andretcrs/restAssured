package util;

import net.datafaker.Faker;
public class GerarDadoProdutosFaker {
    private static final Faker faker = new Faker();

    public static String gerarDadosProdutos() {
        return "{\n" +
                "    \"title\": \"" + faker.commerce().productName() + "\",\n" +
                "    \"description\": \"" + faker.lorem().sentence() + "\",\n" +
                "    \"price\": " + faker.number().randomDouble(2, 1, 100) + ",\n" +
                "    \"discountPercentage\": " + faker.number().randomDouble(1, 0, 50) + ",\n" +
                "    \"rating\": " + faker.number().randomDouble(2, 1, 5) + ",\n" +
                "    \"stock\": " + faker.number().numberBetween(1, 100) + ",\n" +
                "    \"brand\": \"" + faker.company().name() + "\",\n" +
                "    \"category\": \"" + faker.commerce().material() + "\",\n" +
                "    \"thumbnail\": \"" + faker.internet().image() + "\"\n" +
                "}";
    }

}
