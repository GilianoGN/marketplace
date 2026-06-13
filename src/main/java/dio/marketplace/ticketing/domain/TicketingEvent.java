package dio.marketplace.ticketing.domain;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;

@Getter
public class TicketingEvent {
    private UUID id;
    private TicketingEventId correlationId;
    private Map<TicketingSector, List<TicketingSeat>> seats;

    public TicketingEvent(String correlationId, Map<TicketingSector, List<TicketingSeat>> seats) {
        this.id = UUID.randomUUID();
        this.correlationId = new TicketingEventId(correlationId);
        this.seats = seats;
    }
}
