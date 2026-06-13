package dio.marketplace.ticketing.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import dio.marketplace.ticketing.domain.TicketingCustomer;
import dio.marketplace.ticketing.domain.TicketingCustomerRepository;
import dio.marketplace.ticketing.infrastructure.persistence.entity.TicketingCustomerEntity;

@Repository
public class TicketingPostgresCustomerRepository implements TicketingCustomerRepository{
    private final TicketingCustomerCrudRepository customerCrudRepository;

    public TicketingPostgresCustomerRepository(TicketingCustomerCrudRepository customerCrudRepository) {
        this.customerCrudRepository = customerCrudRepository;
    }

    @Override
    public void save(TicketingCustomer customer) {
        var entity = new TicketingCustomerEntity(
            customer.getId(),
            customer.getCorrelationId().id(),
            customer.getName()
        );

        customerCrudRepository.save(entity);
    }
}