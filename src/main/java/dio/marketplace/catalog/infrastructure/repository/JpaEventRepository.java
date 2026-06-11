package dio.marketplace.catalog.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import dio.marketplace.catalog.domain.Event;
import dio.marketplace.catalog.domain.EventId;
import dio.marketplace.catalog.domain.EventRepository;
import dio.marketplace.catalog.infrastructure.entity.EventEntity;

@Repository
public class JpaEventRepository implements EventRepository{
    private final EventEntityRepository eventEntityRepository;

    public JpaEventRepository(EventEntityRepository eventEntityRepository) {
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override
    public List<Event> findAll() {
        var iterable = eventEntityRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
            .map(JpaEventRepository::mapper).toList();
    }
    
    private static Event mapper(EventEntity event) {
        return new Event(new EventId(event.getId()), event.getTitle(), event.getDate(), Optional.empty());
    }
}