package dio.marketplace.registration.infrastructure.entity.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import dio.marketplace.registration.infrastructure.entity.CustomerEntity;


@Projection(name = "excerpt", types = CustomerEntity.class)
public interface CustomerExcerpt {

    String getFirstName();

    String getLastName();

    @Value("#{target.address?.toString()}")
    String getAddress();
    
}