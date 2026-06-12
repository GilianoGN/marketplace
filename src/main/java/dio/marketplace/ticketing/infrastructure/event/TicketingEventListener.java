package dio.marketplace.ticketing.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import dio.marketplace.common.infrastruture.event.dto.CustomerCreated;
import dio.marketplace.common.infrastruture.event.dto.EventUpdated;
import dio.marketplace.ticketing.application.CreateCustomerUseCase;

@Component
public class TicketingEventListener {
    private static final Logger logger = LoggerFactory.getLogger(TicketingEventListener.class);

    private final CreateCustomerUseCase createCustomerUseCase;

    public TicketingEventListener(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @EventListener
    @Async
    public void handle(CustomerCreated event) {
        logger.info("CustomerCreated received {}", event);
        createCustomerUseCase.execute(event);
    }

    @EventListener
    @Async
    public void handle(EventUpdated event) {
        logger.info("EventUpdated received {}", event);
    }
}
