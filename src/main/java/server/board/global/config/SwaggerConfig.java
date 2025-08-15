package server.board.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Server prodServer = new Server();
        prodServer.setUrl("https://dongmin.inuappcenter.kr");
        prodServer.setDescription("Production Server");

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", securityScheme))
                .info(apiInfo())
                .servers(List.of(prodServer));
    }

    private Info apiInfo() {
        return new Info()
                .title("Appcenter Board API Docs") // API의 제목
                .description("Basic Summer Term Side Project API Docs") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
