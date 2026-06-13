package dio.marketplace.catalog.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dio.marketplace.catalog.domain.CatalogEvent;
import dio.marketplace.catalog.domain.CatalogEventMetadata;

public record CatalogEventOutput(
    String id,
    String title,
    Instant date,
    EventMetadataOutput metadata
) implements Serializable {

    public record EventMetadataOutput (
        String eventDescription,
        Map<String, Object> technicalRequirements,
        Map<String, List<SeatOutput>> seatsBySector
    ) implements Serializable{
        public record SeatOutput (
            String id,
            String sectorId,
            BigDecimal price
        ) implements Serializable {
        }

        public static EventMetadataOutput from(CatalogEventMetadata metadata) {
            Map<String, List<CatalogEventOutput.EventMetadataOutput.SeatOutput>> seats = 
                metadata.seats().entrySet().stream()
                    .collect(Collectors.toMap(
                        entry -> entry.getKey().getId().name(),
                        entry -> entry.getValue().stream()
                            .map(seat -> new CatalogEventOutput.EventMetadataOutput.SeatOutput(
                                seat.getId().seatNumber(),
                                seat.getSectorId().name(),
                                entry.getKey().getPrice()
                            ))
                            .toList()
                        ));

            return new EventMetadataOutput(
                metadata.eventDescription(),
                metadata.technicalRequirements(),
                seats
            );
        }
    }

    public static CatalogEventOutput from(CatalogEvent event) {
        return new CatalogEventOutput(
            event.getId().id().toString(),
            event.getTitle(),
            event.getDate(),
            event.getMetadata()
                .map(EventMetadataOutput::from)
                .orElse(null)
        );
    }
}