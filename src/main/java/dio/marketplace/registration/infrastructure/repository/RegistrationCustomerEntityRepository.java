package dio.marketplace.registration.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import dio.marketplace.registration.infrastructure.entity.RegistrationCustomerEntity;
import dio.marketplace.registration.infrastructure.entity.projection.RegistrationCustomerExcerpt;

@RepositoryRestResource(excerptProjection = RegistrationCustomerExcerpt.class)
public interface RegistrationCustomerEntityRepository extends PagingAndSortingRepository<RegistrationCustomerEntity, UUID>, CrudRepository<RegistrationCustomerEntity, UUID> {
    
    List<RegistrationCustomerEntity> findByFirstNameStartingWithIgnoreCase(@Param("firstName") String firstName);

    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);

}
