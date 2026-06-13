package dio.marketplace.catalog.domain;

import java.util.List;
import java.util.Map;

public record CatalogEventMetadata(String eventDescription,
                            Map<String, Object> technicalRequirements,
                            Map<CatalogSector, List<CatalogSeat>> seats){
}