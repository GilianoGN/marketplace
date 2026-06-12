package dio.marketplace.ticketing.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import dio.marketplace.ticketing.domain.Event;
import dio.marketplace.ticketing.domain.EventRepository;
import dio.marketplace.ticketing.domain.Seat;
import dio.marketplace.ticketing.domain.Sector;
import dio.marketplace.ticketing.infrastructure.persistence.entity.EventEntity;
import dio.marketplace.ticketing.infrastructure.persistence.entity.SeatEntity;
import dio.marketplace.ticketing.infrastructure.persistence.entity.SectorEntity;

@Repository
public class PostgresEventRepository implements EventRepository{
    private final EventCrudRepository eventCrudRepository;

    public PostgresEventRepository(EventCrudRepository eventCrudRepository) {
        this.eventCrudRepository = eventCrudRepository;
    }

    @Override
    public void save(Event event) {
        var sectors = event.getSeats().entrySet().stream()
            .map(entry -> {
                Sector domainSector = entry.getKey();
                List<Seat> domainSeats = entry.getValue();

                var seats = domainSeats.stream()
                    .map(s -> new SeatEntity(
                        s.getId(),
                        s.getCorrelationId().id()
                    ))
                    .toList();
                
                return new SectorEntity(
                    domainSector.getId(),
                    domainSector.getCorrelationId().id(),
                    domainSector.getPrice(),
                    seats
                );
            })
            .toList();
        
        var entity = new EventEntity(
            event.getId(),
            event.getCorrelationId().id(),
            sectors);

        eventCrudRepository.save(entity);
    }
}