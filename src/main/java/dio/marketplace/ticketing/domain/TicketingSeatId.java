package dio.marketplace.ticketing.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record TicketingSeatId(UUID id) {
    public TicketingSeatId {
        Assert.notNull(id, "id must not be null");
    }
}