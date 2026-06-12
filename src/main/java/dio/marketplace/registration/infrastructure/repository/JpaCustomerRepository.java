package dio.marketplace.registration.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import dio.marketplace.common.infrastruture.event.dto.CustomerCreated;
import dio.marketplace.registration.domain.Customer;
import dio.marketplace.registration.domain.CustomerId;
import dio.marketplace.registration.domain.CustomerRepository;
import dio.marketplace.registration.infrastructure.entity.CustomerEntity;

@Repository
public class JpaCustomerRepository implements CustomerRepository {
    private final CustomerEntityRepository customerEntityRepository;
    private final ApplicationEventPublisher publisher;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository,
                                ApplicationEventPublisher publisher) {
        this.customerEntityRepository = customerEntityRepository;
        this.publisher = publisher;
    }

    @Override
    public Customer save(Customer customer) {
        var entity = mapper(customer);
        customerEntityRepository.save(entity);

        publisher.publishEvent(new CustomerCreated(customer.getId().id().toString(), customer.getName()));

        return customer;
    }

    @Override
    public List<Customer> findAll() {
        var iterable = customerEntityRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(JpaCustomerRepository::mapper).toList();

    }

    private static CustomerEntity mapper(Customer customer) {
        var entity = new CustomerEntity();

        entity.setId(customer.getId().id());
        entity.setFirstName(customer.getName());
        entity.setEmail(customer.getEmail());

        return entity;
    }

    private static Customer mapper(CustomerEntity entity) {
        String fullName = Optional.ofNullable(entity.getLastName())
                .map(lastName -> entity.getFirstName() + " " + lastName)
                .orElseGet(entity::getFirstName);

        return new Customer(new CustomerId(entity.getId()), fullName, entity.getEmail());
    }
}