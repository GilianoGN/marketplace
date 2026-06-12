package dio.marketplace.ticketing.domain;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Seat {
    private UUID id;
    private EventId correlationId;

    public Seat(String correlationId) {
        this.id = UUID.randomUUID();
        this.correlationId = new EventId(correlationId);
    }
}