package dio.marketplace.ticketing.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingSectorEntity;

@Repository
public class Ticketing implements TicketingEventRepository{
    private final TicketingEventCrudRepository eventCrudRepository;
    private final StringRedisTemplate redisTemplate;

    public Ticketing(TicketingEventCrudRepository eventCrudRepository,
            @Qualifier("ticketingStringRedisTemplate") StringRedisTemplate redisTemplate) {
        this.eventCrudRepository = eventCrudRepository;
        this.redisTemplate = redisTemplate;
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
                    UUID.fromString(domainSector.getCorrelationId().id()),
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

        String lockKey = "seat_lock:" + eventId.id().toString() + ":" + seatId.id().toString();

        String lockValue = customerId.id().toString();

        Boolean locked = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, Duration.ofSeconds(30));

        return Boolean.TRUE.equals(locked);
    }
}