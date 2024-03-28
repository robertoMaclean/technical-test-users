package cl.technicaltest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Users API")
                                .version("1.0.0")
                                .description("Service that allow user CRUD operations and configure the password validation of it")
                        .contact(
                                new Contact()
                                        .name("Roberto Mac lean M.")
                                        .url("https://robmaclean-portfolio.netlify.app/")
                                        .email("r.maclean.m@gmail.com"
                                        )));
    }
}