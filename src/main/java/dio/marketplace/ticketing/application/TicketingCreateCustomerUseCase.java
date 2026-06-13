package dio.marketplace.ticketing.application;

import org.springframework.stereotype.Service;

import dio.marketplace.common.infrastruture.event.dto.CommonCustomerCreated;
import dio.marketplace.ticketing.domain.TicketingCustomer;
import dio.marketplace.ticketing.domain.TicketingCustomerRepository;

@Service
public class TicketingCreateCustomerUseCase {
    private final TicketingCustomerRepository customerRepository;

    public TicketingCreateCustomerUseCase(TicketingCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(CommonCustomerCreated event) {
        var customer = new TicketingCustomer(event.id(), event.name());
        customerRepository.save(customer);
    }
}