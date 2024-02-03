package br.com.cannysters.integrationtests.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cannysters.configs.TestConfigs;
import br.com.cannysters.integrationtests.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() { //testar se a página swagger esta funcionando
		var content = 
				given() // pertence ao RestAssured
					.basePath("/swagger-ui/index.html")
					.port(TestConfigs.SERVER_PORT) // porta definida na classe TestConfigs (8888)
					.when()
						.get() // simulando a requisição get com o Rest Assured
					.then()
						.statusCode(200)//verifica se o status code recebido bate com o selecionado
					.extract() // extrai valores da resposta http 
						.body() // retorna o response body
							.asString(); // converte o body em string
		assertTrue(content.contains("Swagger UI")); // verifica o retorno se tem o nome da página
	}

}
