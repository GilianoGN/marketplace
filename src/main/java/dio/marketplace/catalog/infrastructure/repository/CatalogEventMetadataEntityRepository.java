package dio.marketplace.catalog.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dio.marketplace.catalog.infrastructure.entity.CatalogEventMetadataEntity;

@RepositoryRestResource
public interface CatalogEventMetadataEntityRepository extends MongoRepository<CatalogEventMetadataEntity, String> {
    Optional<CatalogEventMetadataEntity> findByEventId(UUID eventId);
}