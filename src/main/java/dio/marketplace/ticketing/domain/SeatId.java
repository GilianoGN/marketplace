package dio.marketplace.ticketing.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record SeatId(UUID id) {
    public SeatId {
        Assert.notNull(id, "id must not be null");
    }
}