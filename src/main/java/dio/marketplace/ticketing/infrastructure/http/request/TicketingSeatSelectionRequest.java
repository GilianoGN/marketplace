package dio.marketplace.ticketing.infrastructure.http.request;

import java.util.UUID;

import dio.marketplace.ticketing.domain.TicketingSeatId;

public record TicketingSeatSelectionRequest(UUID id) {
    public TicketingSeatId toInput() {
        return new TicketingSeatId(id);
    }
}