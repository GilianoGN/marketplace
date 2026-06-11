package dio.marketplace.catalog.infrastructure.repository;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import dio.marketplace.catalog.domain.EventId;
import dio.marketplace.catalog.domain.EventMetadata;
import dio.marketplace.catalog.domain.EventMetadataRepository;
import dio.marketplace.catalog.domain.Seat;
import dio.marketplace.catalog.domain.SeatId;
import dio.marketplace.catalog.domain.Sector;
import dio.marketplace.catalog.domain.SectorId;
import dio.marketplace.catalog.infrastructure.entity.EventMetadataEntity;

@Repository
public class MongoEventMetadataRepository implements EventMetadataRepository{
    private final EventMetadataEntityRepository eventMetadataEntityRepository;

    public MongoEventMetadataRepository(EventMetadataEntityRepository eventMetadataEntityRepository) {
        this.eventMetadataEntityRepository = eventMetadataEntityRepository;
    }

    @Override
    public Optional<EventMetadata> findByEventId(EventId eventId) {
        return eventMetadataEntityRepository.findByEventId(eventId.id()).map(MongoEventMetadataRepository::mapper);
    }

    private static EventMetadata mapper(EventMetadataEntity eventMetadataEntity) {
        var sectors = eventMetadataEntity.getSectors().stream()
            .map(MongoEventMetadataRepository::mapper)
            .collect(Collectors.toMap(
                sector -> sector.getId().name(),
                Function.identity()
            ));

        var seats = eventMetadataEntity.getSeats().stream()
            .map(MongoEventMetadataRepository::mapper)
            .collect(Collectors.groupingBy(
                seat -> sectors.get(seat.getSectorId().name())
            ));

        return new EventMetadata(
            eventMetadataEntity.getEventDescription(),
            eventMetadataEntity.getTechnicalRequirements(),
            seats);
    }


    private static Seat mapper(EventMetadataEntity.Seat seat) {
        return new Seat(new SeatId(seat.getCode()), new SectorId(seat.getSectorName()));
    }

    private static Sector mapper(EventMetadataEntity.Sector sector) {
        return new Sector(new SectorId(sector.getName()), sector.getPrice());
    }
}