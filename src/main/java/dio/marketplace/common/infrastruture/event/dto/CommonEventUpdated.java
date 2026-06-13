package dio.marketplace.common.infrastruture.event.dto;

import java.math.BigDecimal;
import java.util.List;

import dio.marketplace.catalog.infrastructure.entity.CatalogEventMetadataEntity;


public record CommonEventUpdated(String id, List<Sector> sectors) {

    public static CommonEventUpdated from(CatalogEventMetadataEntity event) {
        List<Sector> sectors = event.getSectors().stream()
                .map(s -> Sector.from(s, event.getSeats()))
                .toList();

        return new CommonEventUpdated(event.getEventId().toString(), sectors);
    }

    public record Sector(String id, BigDecimal price, List<Seat> seats) {
        public static Sector from(CatalogEventMetadataEntity.Sector sector, List<CatalogEventMetadataEntity.Seat> allSeats) {
            List<Seat> sectorSeats = allSeats.stream()
                    .filter(seat -> seat.getSectorName().equals(sector.getName()))
                    .map(Seat::from)
                    .toList();
            
            return new Sector(sector.getName(), sector.getPrice(), sectorSeats);
        }

        public record Seat(String number) {
            public static Seat from(CatalogEventMetadataEntity.Seat seat) {
                return new Seat(seat.getCode());
            }
        }
    }
}