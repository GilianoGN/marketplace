package dio.marketplace.ticketing.infrastructure;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketingSwaggerConfiguration {

    @Bean
    public GroupedOpenApi ticketingApi() {
        return GroupedOpenApi.builder()
            .group("Ticketing")
            .pathsToMatch("/ticketing/**")
            .build();
    }
}