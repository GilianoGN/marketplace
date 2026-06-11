package dio.marketplace.registration.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record CustomerId(UUID id) {
    public CustomerId {
        Assert.notNull(id, "Customer ID cannot be null");
    }

    public CustomerId() {
        this(UUID.randomUUID());
    }
    
}
