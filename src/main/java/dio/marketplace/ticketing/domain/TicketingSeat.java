package dio.marketplace.ticketing.domain;

import java.util.UUID;
import lombok.Getter;

@Getter
public class TicketingSeat {
    private UUID id;
    private TicketingEventId correlationId;

    public TicketingSeat(String correlationId) {
        this.id = UUID.randomUUID();
        this.correlationId = new TicketingEventId(correlationId);
    }
}