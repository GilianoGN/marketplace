package dio.marketplace.catalog.infrastructure;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogSwaggerConfiguration {

    @Bean
    public GroupedOpenApi catalogApi() {
        return GroupedOpenApi.builder()
            .group("Catalog")
            .pathsToMatch("/catalog/**")
            .build();
    }
}