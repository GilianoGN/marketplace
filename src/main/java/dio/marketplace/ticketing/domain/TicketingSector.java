package dio.marketplace.ticketing.domain;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;

@Getter
public class TicketingSector {
    private UUID id;
    private TicketingSectorId correlationId;
    private BigDecimal price;

    public TicketingSector(String correlationId, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.correlationId = new TicketingSectorId(correlationId);
        this.price = price;
    }
}