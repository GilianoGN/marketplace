package dio.marketplace.catalog.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogSector {
    private CatalogSectorId id;
    private BigDecimal price;

    public CatalogSector(CatalogSectorId id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }
}