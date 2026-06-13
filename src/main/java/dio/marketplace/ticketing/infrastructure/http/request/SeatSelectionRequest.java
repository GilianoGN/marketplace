package dio.marketplace.ticketing.infrastructure.http.request;

import java.util.UUID;

import dio.marketplace.ticketing.domain.SeatId;

public record SeatSelectionRequest(UUID id) {
    public SeatId toInput() {
        return new SeatId(id);
    }
}