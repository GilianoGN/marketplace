package dio.marketplace.catalog.application;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dio.marketplace.catalog.application.dto.CatalogEventOutput;
import dio.marketplace.catalog.domain.CatalogEventRepository;

@Service
public class CatalogBrowseShowcaseUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CatalogBrowseShowcaseUseCase.class);
    
    private final CatalogEventRepository eventRepository;
    private final CatalogEventEnricher eventEnricher;


    public CatalogBrowseShowcaseUseCase(CatalogEventRepository eventRepository, CatalogEventEnricher eventEnricher) {
        this.eventRepository = eventRepository;
        this.eventEnricher = eventEnricher;
    }
    
    @Cacheable(value = "showcase", unless="#result.isEmpty()")
    public List<CatalogEventOutput> execute() {
        var futures = eventRepository.findAll().stream().map(eventEnricher::enrich).toList();

        var events = futures.stream()
            .map(CompletableFuture::join)
            .map(CatalogEventOutput::from)
            .toList();
        
        logger.info("{} events enriched", events.size());
            
        return events;
    }
}