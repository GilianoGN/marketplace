package dio.marketplace.catalog.infrastructure.http;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.marketplace.catalog.application.CatalogBrowseShowcaseUseCase;
import dio.marketplace.catalog.application.dto.CatalogEventOutput;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/catalog/showcase")
public class CatalogShowcaseController {
    private final CatalogBrowseShowcaseUseCase browseShowcaseUseCase;

    public CatalogShowcaseController(CatalogBrowseShowcaseUseCase browseShowcaseUseCase) {
        this.browseShowcaseUseCase = browseShowcaseUseCase;
    }

    @GetMapping
    @CircuitBreaker(name = "catalogService", fallbackMethod = "fallbackBrowse")
    List<CatalogEventOutput> browseShowcase() {
        return browseShowcaseUseCase.execute();
    }

    public List<CatalogEventOutput> fallbackBrowse(Throwable t) {
        return Collections.emptyList();
    }
}