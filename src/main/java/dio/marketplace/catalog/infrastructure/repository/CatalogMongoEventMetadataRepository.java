package dio.marketplace.catalog.infrastructure.repository;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import dio.marketplace.catalog.domain.CatalogEventId;
import dio.marketplace.catalog.domain.CatalogEventMetadata;
import dio.marketplace.catalog.domain.CatalogEventMetadataRepository;
import dio.marketplace.catalog.domain.CatalogSeat;
import dio.marketplace.catalog.domain.CatalogSeatId;
import dio.marketplace.catalog.domain.CatalogSector;
import dio.marketplace.catalog.domain.CatalogSectorId;
import dio.marketplace.catalog.infrastructure.entity.CatalogEventMetadataEntity;

@Repository
public class CatalogMongoEventMetadataRepository implements CatalogEventMetadataRepository{
    private final CatalogEventMetadataEntityRepository eventMetadataEntityRepository;

    public CatalogMongoEventMetadataRepository(CatalogEventMetadataEntityRepository eventMetadataEntityRepository) {
        this.eventMetadataEntityRepository = eventMetadataEntityRepository;
    }

    @Override
    public Optional<CatalogEventMetadata> findByEventId(CatalogEventId eventId) {
        return eventMetadataEntityRepository.findByEventId(eventId.id()).map(CatalogMongoEventMetadataRepository::mapper);
    }

    private static CatalogEventMetadata mapper(CatalogEventMetadataEntity eventMetadataEntity) {
        var sectors = eventMetadataEntity.getSectors().stream()
            .map(CatalogMongoEventMetadataRepository::mapper)
            .collect(Collectors.toMap(
                sector -> sector.getId().name(),
                Function.identity()
            ));

        var seats = eventMetadataEntity.getSeats().stream()
            .map(CatalogMongoEventMetadataRepository::mapper)
            .collect(Collectors.groupingBy(
                seat -> sectors.get(seat.getSectorId().name())
            ));

        return new CatalogEventMetadata(
            eventMetadataEntity.getEventDescription(),
            eventMetadataEntity.getTechnicalRequirements(),
            seats);
    }


    private static CatalogSeat mapper(CatalogEventMetadataEntity.Seat seat) {
        return new CatalogSeat(new CatalogSeatId(seat.getCode()), new CatalogSectorId(seat.getSectorName()));
    }

    private static CatalogSector mapper(CatalogEventMetadataEntity.Sector sector) {
        return new CatalogSector(new CatalogSectorId(sector.getName()), sector.getPrice());
    }
}