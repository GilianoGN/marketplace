package dio.marketplace.ticketing.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingSeatLockEntity;

@RepositoryRestResource(exported = false)
public interface TicketingRedisSeatLockRepository extends CrudRepository<TicketingSeatLockEntity, String>{
    Optional<TicketingSeatLockEntity> findByCustomerId(String customerId);
}