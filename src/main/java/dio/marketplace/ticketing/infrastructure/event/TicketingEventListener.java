package dio.marketplace.ticketing.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import dio.marketplace.common.infrastruture.event.dto.CommonCustomerCreated;
import dio.marketplace.common.infrastruture.event.dto.CommonEventUpdated;
import dio.marketplace.ticketing.application.TicketingCreateCustomerUseCase;
import dio.marketplace.ticketing.application.TicketingCreateEventUseCase;

@Component
public class TicketingEventListener {
    private static final Logger logger = LoggerFactory.getLogger(TicketingEventListener.class);

    private final TicketingCreateCustomerUseCase createCustomerUseCase;
    private final TicketingCreateEventUseCase createEventUseCase;


    public TicketingEventListener(TicketingCreateCustomerUseCase createCustomerUseCase, TicketingCreateEventUseCase createEventUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.createEventUseCase = createEventUseCase;
    }

    @EventListener
    @Async
    public void handle(CommonCustomerCreated event) {
        logger.info("CustomerCreated received {}", event);
        createCustomerUseCase.execute(event);
    }

    @EventListener
    @Async
    public void handle(CommonEventUpdated event) {
        logger.info("EventUpdated received {}", event);
        createEventUseCase.execute(event);
    }
}
