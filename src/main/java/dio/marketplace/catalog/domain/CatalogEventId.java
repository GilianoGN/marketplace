package dio.marketplace.catalog.domain;

import java.util.UUID;

public record CatalogEventId(UUID id) {
    public CatalogEventId() {
        this(UUID.randomUUID());
    }
}