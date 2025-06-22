package almeida.alexandro.customer.web;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Insurance Onboarding API")
                        .version("1.0.0")
                        .description("API para cadastro de clientes.")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("dev-team@google.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Oficial")
                        .url("https://example.com.br/docs"));
    }
}
