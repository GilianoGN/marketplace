package dio.marketplace.catalog.domain;

import java.util.List;

public interface CatalogEventRepository {
    List<CatalogEvent> findAll();
}
