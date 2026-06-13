package dio.marketplace.ticketing.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dio.marketplace.ticketing.infrastructure.persistence.entity.SeatLockEntity;

@RepositoryRestResource(exported = false)
public interface RedisSeatLockRepository extends CrudRepository<SeatLockEntity, String>{
    Optional<SeatLockEntity> findByCustomerId(String customerId);
}