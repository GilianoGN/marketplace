package dio.marketplace.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import dio.marketplace.catalog.infrastructure.entity.EventMetadataEntity;

@Component
public class EventMetadataListener extends AbstractMongoEventListener<EventMetadataEntity> {
    private static final Logger logger = LoggerFactory.getLogger(EventMetadataListener.class);

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadataEntity> event){
        logger.info("Event metadata saved via onAfterSave {}", event.getDocument());
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadataEntity> event) {
        logger.info("Event metadata deleted via onAfterDelete {}", event.getDocument());
    }
}