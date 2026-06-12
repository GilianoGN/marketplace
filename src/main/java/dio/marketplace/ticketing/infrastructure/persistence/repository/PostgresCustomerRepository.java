package dio.marketplace.ticketing.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import dio.marketplace.ticketing.domain.Customer;
import dio.marketplace.ticketing.domain.CustomerRepository;
import dio.marketplace.ticketing.infrastructure.persistence.entity.CustomerEntity;

@Repository
public class PostgresCustomerRepository implements CustomerRepository{
    private final CustomerCrudRepository customerCrudRepository;

    public PostgresCustomerRepository(CustomerCrudRepository customerCrudRepository) {
        this.customerCrudRepository = customerCrudRepository;
    }

    @Override
    public void save(Customer customer) {
        var entity = new CustomerEntity(
            customer.getId(),
            customer.getCorrelationId().id(),
            customer.getName()
        );

        customerCrudRepository.save(entity);
    }
}