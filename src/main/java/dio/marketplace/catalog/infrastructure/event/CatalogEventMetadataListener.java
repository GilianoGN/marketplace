package dio.marketplace.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import dio.marketplace.catalog.infrastructure.entity.CatalogEventMetadataEntity;
import dio.marketplace.common.infrastruture.event.dto.CommonEventUpdated;

@Component
public class CatalogEventMetadataListener extends AbstractMongoEventListener<CatalogEventMetadataEntity> {
    private static final Logger logger = LoggerFactory.getLogger(CatalogEventMetadataListener.class);

    private final ApplicationEventPublisher publisher;

    public CatalogEventMetadataListener(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    @Override
    public void onAfterSave(AfterSaveEvent<CatalogEventMetadataEntity> event){
        logger.info("Event metadata saved via onAfterSave {}", event.getDocument());
        this.publisher.publishEvent(CommonEventUpdated.from(event.getSource()));
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<CatalogEventMetadataEntity> event) {
        logger.info("Event metadata deleted via onAfterDelete {}", event.getDocument());
    }
}