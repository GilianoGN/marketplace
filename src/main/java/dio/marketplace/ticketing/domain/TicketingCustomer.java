package dio.marketplace.ticketing.domain;

import java.util.UUID;

import lombok.Getter;

@Getter
public class TicketingCustomer {
    private UUID id;
    private TicketingCustomerId correlationId;
    private String name;

    public TicketingCustomer(String correlationId, String name) {
        this.id = UUID.randomUUID();
        this.correlationId = new TicketingCustomerId(correlationId);
        this.name = name;
    }
}