package br.santosfyuri.algaworks.algafood.core.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.OpenAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Autowired
    private OpenAPIService openAPIService;

    @Bean
    public OpenAPI apiInfo() {
        var openAPI = new OpenAPI()
                .info(new Info().title("AlgaFood API")
                        .description("API aberta para clientes e restaurantes")
                        .version("1")
                        .contact(new Contact()
                                .name("Yuri de Freitas Santos")
                                .email("freitas.yuri@gmail.com")
                                .url("https://github.com/santosfyuri")));
        openAPI.setServers(List.of(new Server().description("local").url("http://localhost:8080")));
        openAPI.tags(
                Arrays.asList(new Tag().name("Cities").description("Manage the cities"),
                        new Tag().name("Groups").description("Manage the user groups"),
                        new Tag().name("Payment methods").description("Manage the payment methods"),
                        new Tag().name("Orders").description("Manage the orders"),
                        new Tag().name("Restaurants").description("Manage the restaurants"),
                        new Tag().name("States").description("Manage the states"),
                        new Tag().name("Kitchens").description("Manage the kitchens"),
                        new Tag().name("Restaurant payment methods").description("Manage the restaurant payment methods"),
                        new Tag().name("Responsible restaurant users").description("Manage the responsible restaurant users"),
                        new Tag().name("Group permissions").description("Manage the group permissions"),
                        new Tag().name("Products").description("Manage the products")));
        return openAPI;
    }
}
