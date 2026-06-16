package dio.marketplace.registration.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import dio.marketplace.common.infrastruture.event.dto.CommonCustomerCreated;
import dio.marketplace.registration.domain.RegistrationCustomer;
import dio.marketplace.registration.domain.RegistrationCustomerId;
import dio.marketplace.registration.domain.RegistrationCustomerRepository;
import dio.marketplace.registration.infrastructure.entity.RegistrationCustomerEntity;

@Repository
public class RegistrationJpaCustomerRepository implements RegistrationCustomerRepository {
    private final RegistrationCustomerEntityRepository customerEntityRepository;
    private final ApplicationEventPublisher publisher;

    public RegistrationJpaCustomerRepository(RegistrationCustomerEntityRepository customerEntityRepository,
                                ApplicationEventPublisher publisher) {
        this.customerEntityRepository = customerEntityRepository;
        this.publisher = publisher;
    }

    @SuppressWarnings("null")
    @Override
    public RegistrationCustomer save(RegistrationCustomer customer) {
        var entity = mapper(customer);
        customerEntityRepository.save(entity);

        publisher.publishEvent(new CommonCustomerCreated(customer.getId().id().toString(), customer.getName()));

        return customer;
    }

    @Override
    public List<RegistrationCustomer> findAll() {
        var iterable = customerEntityRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(RegistrationJpaCustomerRepository::mapper).toList();

    }

    private static RegistrationCustomerEntity mapper(RegistrationCustomer customer) {
        var entity = new RegistrationCustomerEntity();

        entity.setId(customer.getId().id());
        entity.setFirstName(customer.getName());
        entity.setEmail(customer.getEmail());

        return entity;
    }

    private static RegistrationCustomer mapper(RegistrationCustomerEntity entity) {
        String fullName = Optional.ofNullable(entity.getLastName())
                .map(lastName -> entity.getFirstName() + " " + lastName)
                .orElseGet(entity::getFirstName);

        return new RegistrationCustomer(new RegistrationCustomerId(entity.getId()), fullName, entity.getEmail());
    }
}