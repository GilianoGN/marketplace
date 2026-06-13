package dio.marketplace.ticketing.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingCustomerEntity;

@RepositoryRestResource(exported = false, path = "_customer")
public interface TicketingCustomerCrudRepository extends CrudRepository<TicketingCustomerEntity, UUID> {
    
}