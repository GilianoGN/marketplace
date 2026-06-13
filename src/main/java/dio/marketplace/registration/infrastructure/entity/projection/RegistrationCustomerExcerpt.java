package dio.marketplace.registration.infrastructure.entity.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import dio.marketplace.registration.infrastructure.entity.RegistrationCustomerEntity;


@Projection(name = "excerpt", types = RegistrationCustomerEntity.class)
public interface RegistrationCustomerExcerpt {

    String getFirstName();

    String getLastName();

    @Value("#{target.address?.toString()}")
    String getAddress();
    
}