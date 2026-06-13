package dio.marketplace.ticketing.domain;

import org.springframework.util.Assert;

public record TicketingSectorId(String id) {
    public TicketingSectorId {
        Assert.notNull(id, "id must not be null");
    }
}
