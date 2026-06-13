package dio.marketplace.catalog.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import dio.marketplace.catalog.domain.CatalogEvent;
import dio.marketplace.catalog.domain.CatalogEventId;
import dio.marketplace.catalog.domain.CatalogEventRepository;
import dio.marketplace.catalog.infrastructure.entity.CatalogEventEntity;

@Repository
public class CatalogJpaEventRepository implements CatalogEventRepository{
    private final CatalogEventEntityRepository eventEntityRepository;

    public CatalogJpaEventRepository(CatalogEventEntityRepository eventEntityRepository) {
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override
    public List<CatalogEvent> findAll() {
        var iterable = eventEntityRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
            .map(CatalogJpaEventRepository::mapper).toList();
    }
    
    private static CatalogEvent mapper(CatalogEventEntity event) {
        return new CatalogEvent(new CatalogEventId(event.getId()), event.getTitle(), event.getDate(), Optional.empty());
    }
}