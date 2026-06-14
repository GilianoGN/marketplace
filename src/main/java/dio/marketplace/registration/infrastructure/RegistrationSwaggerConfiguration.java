package dio.marketplace.registration.infrastructure;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrationSwaggerConfiguration {

    @Bean
    public GroupedOpenApi RegistrationApi() {
        return GroupedOpenApi.builder()
            .group("Registration")
            .pathsToMatch("/registrationCustomerEntities/**")
            .build();
    }
}