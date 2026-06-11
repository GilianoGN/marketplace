package dio.marketplace.registration.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import dio.marketplace.registration.infrastructure.entity.CustomerEntity;

@Component
@RepositoryEventHandler
public class CustomerEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomerEventHandler.class);
    
    @HandleAfterCreate
    public void handleAfterCreate(CustomerEntity customer) {
        logger.warn("CustomerEventHandler#HandleAfterCreate");
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