package dio.marketplace.registration.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import dio.marketplace.common.infrastruture.event.dto.CommonCustomerCreated;
import dio.marketplace.registration.infrastructure.entity.RegistrationCustomerEntity;

@Component
@RepositoryEventHandler
public class RegistrationCustomerEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationCustomerEventHandler.class);

    private final ApplicationEventPublisher publisher;

    public RegistrationCustomerEventHandler(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    
    @HandleAfterCreate
    public void handleAfterCreate(RegistrationCustomerEntity customer) {
        logger.warn("CustomerEventHandler#HandleAfterCreate");
        publisher.publishEvent(new CommonCustomerCreated(customer.getId().toString(), customer.getFirstName()));
    }

    @HandleAfterSave
    public void handleAfterSave(RegistrationCustomerEntity customer) {
        logger.warn("CustomerEventHandler#HandleAfterSave");
    }

    @HandleAfterDelete
    public void handleAfterDelete(RegistrationCustomerEntity customer) {
        logger.warn("CustomerEventHandler#HandleAfterDelete");
    }
}