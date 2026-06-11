package dio.marketplace.catalog.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dio.marketplace.catalog.infrastructure.entity.EventMetadataEntity;

@RepositoryRestResource
public interface EventMetadataEntityRepository extends MongoRepository<EventMetadataEntity, String> {
    Optional<EventMetadataEntity> findByEventId(UUID eventId);
}