package dio.marketplace.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dio.marketplace.catalog.infrastructure.entity.CatalogEventEntity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

public class CatalogEventListener {
    private static final Logger logger = LoggerFactory.getLogger(CatalogEventListener.class);

    @PostPersist
    public void onEventCreated(CatalogEventEntity event) {
        logger.info("Event created via @PostPersist {}", event);
    }

    @PostUpdate
    public void onEventUpdated(CatalogEventEntity event) {
        logger.info("Event updated via @PostUpdate {}", event);
    }

    @PostRemove
    public void onEventDeleted(CatalogEventEntity event) {
        logger.info("Event deleted via @PostRemove {}", event);
    }
}