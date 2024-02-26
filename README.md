# Sicredi Desafio QA

**Descrição do Projeto**

Este projeto é uma automação de testes de APIs relacionadas a uma aplicação que é responsável por gerenciar produtos eletrônicos com o framework RestAssured em Java.

## Índice

- [Instalação](#instalação)
- [Configuração](#configuração)
- [Execução](#execução)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Dependências](#dependências)
- [Licença](#licença)

## Instalação

Para instalar as dependências do projeto, você precisa ter o Maven instalado em seu sistema. Navegue até o diretório raiz do projeto e execute o seguinte comando:

```bash
mvn clean install
```

## Configuração

Antes de executar os testes, certifique-se de configurar as variáveis de ambiente necessárias. Você pode encontrar as configurações em arquivos como `testng.xml` ou diretamente nos testes.

```bash
# Exemplo de Configuração de Variáveis de Ambiente
export BASE_URL=https://api.exemplo.com
export API_KEY=sua_chave_secreta
```

## Execução

Os testes podem ser executados utilizando o Maven ou um ambiente de desenvolvimento integrado (IDE). Utilize o seguinte comando para executar os testes via Maven:

```bash
mvn clean test
```

## Estrutura do Projeto

```plaintext
sicredi-desafio-qa
├─ .git
├─ pom.xml
├─ README.md
├─ src
│  └─ test
│     ├─ java
│     │  └─ api
│     │     ├─ tests
│     │     │  ├─ base
│     │     │  │  └─ BasePage.java
│     │     │  │  └─ Properties
│     │     │  ├─ login
│     │     │  │  └─ LoginTest.java
│     │     │  ├─ produtos
│     │     │  │  └─ AdicionarProdutosTest.java
│     │     │  │  └─ BuscarProdutosPorIdTest.java
│     │     │  │  └─ BuscarProdutosComAutenticacaoTest.java
│     │     │  │  └─ BuscarProdutosSemAutenticacaoTest.java
│     │     │  ├─ usuarios
│     │     │  │  └─ ConsultarUsuarioTest.java
│     │     ├─ util
│     │        └─ GerarDadoProdutosFaker.java
│     │        └─ GerarDadoProdutosInteiroFaker
│     │        └─ GerarDadoUsuarioFaker.java
│     │        └─ GetPage.java
│     ├─ resources
│        └─ collection
│        |  └─ Sicredi Collection.postman_collection.json
│        └─ fixtures
│        |  └─ addProduct.json
│        └─ allure.properties
│        └─ LoginJsonSchema.json
│        └─ ProdutosJsonSchema.json
├─ target
│        └─ allure.properties
│        └─ generated-test-sources
│        └─ maven-status
│        └─ surefire-reports
│        └─ test-classes
├─ .gitignore
├─ .gitlab-ci.yml
├─ pom.xml
├─ README.md
```

## Dependências

- [RestAssured](https://rest-assured.io/)
- [TestNG](https://testng.org/)
- [Maven](https://maven.apache.org/)

# Como Executar o Relatório Allure Localmente

Para visualizar o relatório Allure gerado pelos testes, siga as instruções abaixo:

## Pré-requisitos

- Certifique-se de ter o Allure Command Line instalado em sua máquina. Para instruções de instalação, consulte a [documentação oficial do Allure](https://allurereport.org/docs/junit5/).

## Passos

1. Navegue até o diretório do projeto onde os resultados do Allure estão localizados. Geralmente, os resultados são armazenados em `target/allure-results`.

2. Abra um terminal na pasta do projeto.

3. Execute o seguinte comando para gerar o relatório:

    ```bash(linux)
    mvn allure:serve
    ```

4. O comando acima iniciará um servidor local e abrirá o relatório no seu navegador padrão. O URL do relatório será exibido no terminal.

5. Logo será apresentado o no seu navegador os resultados do testes(report)

    ```
    Servindo relatório de 7008...
    Pressione Ctrl+C para encerrar o servidor
    ```

Lembre-se de substituir `target/allure-results` pelo caminho real para o diretório onde seus resultados do Allure estão armazenados.

## Executando os testes no gitlab
Acesse o menu Build --> Pipeline Schedules e clique no Botão Run Pipeline Schedules na Schedule com a descrição "Executar todos os testes"
OBS: Foi criado um agendamento para que elas sejam executadas de segunda a sexta-feira as 18:00.

## Sugestões de melhorias nos Endpoints da API

### Endpoint: /users?skip=-10&limit=-20

### Descrição
O endpoint `/users?skip=-10&limit=-20` está retornando resultados inesperados quando valores negativos são fornecidos para os parâmetros `skip` e `limit`.

### Sugestões de Melhoria

1. **Validação Adequada no Servidor:**
   Certifique-se de que o servidor da API está realizando a validação adequada para os parâmetros `skip` e `limit`, garantindo que valores negativos não sejam aceitos.

2. **Tratamento de Erro Apropriado:**
   Atualize a API para retornar um código de status adequado (por exemplo, 400 Bad Request) quando valores inválidos são fornecidos. Isso ajudará a indicar corretamente que a solicitação do cliente possui parâmetros inválidos.

3. **Documentação Clara:**
   Atualize a documentação para incluir informações claras sobre os requisitos de `skip` e `limit`. Forneça exemplos claros para os usuários entenderem como usar esses parâmetros corretamente.

5. **Mensagens de Erro Claras:**
   Se possível, incluir mensagens de erro detalhadas na resposta da API quando valores inválidos são fornecidos. Isso ajuda os desenvolvedores a diagnosticar e corrigir os problemas mais facilmente.

6. **Limite Padrão Seguro:**
   Considere estabelecer um valor padrão seguro para `limit` caso um valor inválido seja fornecido. Isso pode evitar problemas inesperados, como a exposição desnecessária de um grande número de registros.

7. **Tratamento de Erro Personalizado:**
   Implemente um tratamento de erro personalizado para lidar com URLs não encontradas. Se uma URL não corresponde a nenhum recurso, a aplicação deve retornar um código de status 404 e uma mensagem de erro adequada.

8. **Registro de Roteamento:**
   Considere incluir logs detalhados do processo de roteamento para ajudar a diagnosticar problemas. Verifique os logs para garantir que as URLs estão sendo processadas corretamente.

9. **Endpoint produtos:**
    No endpoint de produtos esta aceitando o envio do Json com apenas um campo, exemplo: um Post de {"title": "Perfume Oil"} e o cadastro fica imconpleto, seria interessante se tivesse verificações de campos obrigatórios.
---

## Endpoint: /test/error

### Descrição
O endpoint `/test/error` está sempre retornando um status 200 independentemente da URL fornecida informada.

## Endpoint: /products
### Descrição
No endpoint `/products` ao adicionar um novo produto está retornando status code 200 e na documentação está informando 201

## Endpoint: /auth/login
### Descrição
No endpoint /auth/login está retornando status code 200 e na documentação está informando 201

## Endpoint: /products/add
### Descrição
No endpoint /products/add está aceitando enviar um tipo de dado inteiro no campo Title, na documentação informa o tipo
de dado como String, deveria retornar uma mensagem informando sobre o tipo de dado incorreto. Nos campos price,stock, rating 
está aceitando string e o campo é inteiro. Deveria existir uma validação no tipo de dado enviado.

## Endpoint: /products/add
### Descrição
No endpoint /products/add está aceitando também o envio de um objeto vazio e mesmo assim retorna status code 200

---
## Licença

Este projeto é licenciado sob a [Licença MIT](LICENSE).

---

Este é um projeto de automação de testes API desenvolvido usando RestAssured em Java.