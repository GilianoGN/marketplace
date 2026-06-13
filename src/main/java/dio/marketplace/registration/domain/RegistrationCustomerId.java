package dio.marketplace.registration.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record RegistrationCustomerId(UUID id) {
    public RegistrationCustomerId {
        Assert.notNull(id, "Customer ID cannot be null");
    }

    public RegistrationCustomerId() {
        this(UUID.randomUUID());
    }
    
}
