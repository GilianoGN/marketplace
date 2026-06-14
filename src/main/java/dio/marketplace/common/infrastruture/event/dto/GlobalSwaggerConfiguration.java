package dio.marketplace.common.infrastruture.event.dto;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class GlobalSwaggerConfiguration {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Marketplace API")
                .description("Documentação centralizada do Marketplace")
                .version("1.0"));
    }
}
