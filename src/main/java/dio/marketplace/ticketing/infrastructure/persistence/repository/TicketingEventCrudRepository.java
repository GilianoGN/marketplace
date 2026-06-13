package dio.marketplace.ticketing.infrastructure.persistence.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingEventEntity;

@RepositoryRestResource(exported = false, path = "_event")
public interface TicketingEventCrudRepository extends CrudRepository<TicketingEventEntity, UUID> {
    boolean existsByCorrelationIdAndSectors_Seats_CorrelationId(UUID eventId, UUID seatId);
}
