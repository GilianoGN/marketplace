package dio.marketplace.catalog.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogSeat {
    private CatalogSeatId id;
    private CatalogSectorId sectorId;

    public CatalogSeat(CatalogSeatId id, CatalogSectorId sectorId) {
        this.id = id;
        this.sectorId = sectorId;
    }
}
