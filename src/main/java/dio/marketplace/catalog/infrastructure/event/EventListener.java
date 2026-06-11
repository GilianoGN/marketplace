package dio.marketplace.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dio.marketplace.catalog.infrastructure.entity.EventEntity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

public class EventListener {
    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @PostPersist
    public void onEventCreated(EventEntity event) {
        logger.info("Event created via @PostPersist {}", event);
    }

    @PostUpdate
    public void onEventUpdated(EventEntity event) {
        logger.info("Event updated via @PostUpdate {}", event);
    }

    @PostRemove
    public void onEventDeleted(EventEntity event) {
        logger.info("Event deleted via @PostRemove {}", event);
    }
}