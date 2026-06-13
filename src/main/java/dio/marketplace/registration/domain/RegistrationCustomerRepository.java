package dio.marketplace.registration.domain;

import java.util.List;

public interface RegistrationCustomerRepository {
    RegistrationCustomer save(RegistrationCustomer customer);
    List<RegistrationCustomer> findAll();
    
}
