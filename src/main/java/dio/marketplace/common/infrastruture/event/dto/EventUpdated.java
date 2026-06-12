package dio.marketplace.common.infrastruture.event.dto;

import java.math.BigDecimal;
import java.util.List;

import dio.marketplace.catalog.infrastructure.entity.EventMetadataEntity;


public record EventUpdated(String id, List<Sector> sectors) {

    public static EventUpdated from(EventMetadataEntity event) {
        List<Sector> sectors = event.getSectors().stream()
                .map(s -> Sector.from(s, event.getSeats()))
                .toList();

        return new EventUpdated(event.getEventId().toString(), sectors);
    }

    public record Sector(String id, BigDecimal price, List<Seat> seats) {
        public static Sector from(EventMetadataEntity.Sector sector, List<EventMetadataEntity.Seat> allSeats) {
            List<Seat> sectorSeats = allSeats.stream()
                    .filter(seat -> seat.getSectorName().equals(sector.getName()))
                    .map(Seat::from)
                    .toList();
            
            return new Sector(sector.getName(), sector.getPrice(), sectorSeats);
        }

        public record Seat(String number) {
            public static Seat from(EventMetadataEntity.Seat seat) {
                return new Seat(seat.getCode());
            }
        }
    }
}