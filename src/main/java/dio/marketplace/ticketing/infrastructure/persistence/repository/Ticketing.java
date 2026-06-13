package dio.marketplace.ticketing.infrastructure.persistence.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Repository;

import dio.marketplace.ticketing.domain.TicketingCustomerId;
import dio.marketplace.ticketing.domain.TicketingEvent;
import dio.marketplace.ticketing.domain.TicketingEventId;
import dio.marketplace.ticketing.domain.TicketingEventRepository;
import dio.marketplace.ticketing.domain.TicketingSeat;
import dio.marketplace.ticketing.domain.TicketingSeatId;
import dio.marketplace.ticketing.domain.TicketingSector;
import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingEventEntity;
import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingSeatEntity;
import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingSeatLockEntity;
import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingSectorEntity;

@Repository
public class Ticketing implements TicketingEventRepository{
    private final TicketingEventCrudRepository eventCrudRepository;
    private final TicketingRedisSeatLockRepository redisSeatLockRepository;


    public Ticketing(TicketingEventCrudRepository eventCrudRepository, TicketingRedisSeatLockRepository redisSeatLockRepository) {
        this.eventCrudRepository = eventCrudRepository;
        this.redisSeatLockRepository = redisSeatLockRepository;
    }

    @Override
    public void save(TicketingEvent event) {
        var sectors = event.getSeats().entrySet().stream()
            .map(entry -> {
                TicketingSector domainSector = entry.getKey();
                List<TicketingSeat> domainSeats = entry.getValue();

                var seats = domainSeats.stream()
                    .map(s -> new TicketingSeatEntity(
                        s.getId(),
                        s.getCorrelationId().id()
                    ))
                    .toList();
                
                return new TicketingSectorEntity(
                    domainSector.getId(),
                    domainSector.getCorrelationId().id(),
                    domainSector.getPrice(),
                    seats
                );
            })
            .toList();
        
        var entity = new TicketingEventEntity(
            event.getId(),
            event.getCorrelationId().id(),
            sectors);

        eventCrudRepository.save(entity);
    }

    @Override
    public boolean existsSeat(TicketingEventId eventId, TicketingSeatId seatId) {
        return eventCrudRepository.existsByCorrelationIdAndSectors_Seats_CorrelationId(eventId.id(), seatId.id());
    }

    @Override
    public boolean tryLockSeat(TicketingEventId eventId, TicketingSeatId seatId, TicketingCustomerId customerId) {
        String lockId = eventId.id().toString() + ":" + seatId.id().toString();
        
        if (redisSeatLockRepository.existsById(lockId)) {
            return false;
        }

        var lock = new TicketingSeatLockEntity(lockId, customerId.id().toString(), Instant.now());
        redisSeatLockRepository.save(lock);

        return true;
    }
}