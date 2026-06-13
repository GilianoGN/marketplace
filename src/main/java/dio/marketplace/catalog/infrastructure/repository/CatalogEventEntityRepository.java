package dio.marketplace.catalog.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dio.marketplace.catalog.infrastructure.entity.CatalogEventEntity;

@RepositoryRestResource
public interface CatalogEventEntityRepository extends CrudRepository<CatalogEventEntity, UUID> {

}
