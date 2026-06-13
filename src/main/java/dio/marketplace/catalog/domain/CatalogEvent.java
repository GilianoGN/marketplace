package dio.marketplace.catalog.domain;

import java.time.Instant;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogEvent {
    private CatalogEventId id;
    private String title;
    private Instant date;
    private Optional<CatalogEventMetadata> metadata;

    public CatalogEvent(CatalogEventId id, String title, Instant date, Optional<CatalogEventMetadata> metadata) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.metadata = metadata;
    }
}
