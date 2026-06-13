package dio.marketplace.catalog.application;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dio.marketplace.catalog.domain.CatalogEvent;
import dio.marketplace.catalog.domain.CatalogEventMetadataRepository;

@Service
public class CatalogEventEnricher {
    private static final Logger logger = LoggerFactory.getLogger(CatalogEventEnricher.class);

    private final CatalogEventMetadataRepository eventMetadataRepository;

    public CatalogEventEnricher(CatalogEventMetadataRepository eventMetadataRepository) {
        this.eventMetadataRepository = eventMetadataRepository;
    }

    @Async
    public CompletableFuture<CatalogEvent> enrich(CatalogEvent event) {
        logger.info("Enriching event: {}", event);

        var metadata = eventMetadataRepository.findByEventId(event.getId());
        event.setMetadata(metadata);

        return CompletableFuture.completedFuture(event);
    }
}