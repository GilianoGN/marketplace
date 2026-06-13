package dio.marketplace.ticketing.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record TicketingEventId(UUID id) {
    public TicketingEventId {
        Assert.notNull(id, "id must not be null");
    }

    public TicketingEventId(String id) {
        this(UUID.fromString(id));
    }
}
