package dio.marketplace.ticketing.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record TicketingCustomerId(UUID id) {
    
    public TicketingCustomerId {
        Assert.notNull(id, "id must not be null");
    }

    public TicketingCustomerId(String id) {
        this(UUID.fromString(id));
    }
}
