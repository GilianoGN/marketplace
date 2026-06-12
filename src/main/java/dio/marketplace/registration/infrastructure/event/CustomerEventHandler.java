package dio.marketplace.registration.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import dio.marketplace.common.infrastruture.event.dto.CustomerCreated;
import dio.marketplace.registration.infrastructure.entity.CustomerEntity;

@Component
@RepositoryEventHandler
public class CustomerEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomerEventHandler.class);

    private final ApplicationEventPublisher publisher;

    public CustomerEventHandler(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    
    @HandleAfterCreate
    public void handleAfterCreate(CustomerEntity customer) {
        logger.warn("CustomerEventHandler#HandleAfterCreate");
        publisher.publishEvent(new CustomerCreated(customer.getId().toString(), customer.getFirstName()));
    }

    @HandleAfterSave
    public void handleAfterSave(CustomerEntity customer) {
        logger.warn("CustomerEventHandler#HandleAfterSave");
    }

    @HandleAfterDelete
    public void handleAfterDelete(CustomerEntity customer) {
        logger.warn("CustomerEventHandler#HandleAfterDelete");
    }
}