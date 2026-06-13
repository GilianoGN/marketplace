package dio.marketplace.catalog.domain;

import java.util.Optional;

public interface CatalogEventMetadataRepository {
    Optional<CatalogEventMetadata> findByEventId(CatalogEventId eventId);
}
