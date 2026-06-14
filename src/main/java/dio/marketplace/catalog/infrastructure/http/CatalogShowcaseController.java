package dio.marketplace.catalog.infrastructure.http;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.marketplace.catalog.application.CatalogBrowseShowcaseUseCase;
import dio.marketplace.catalog.application.dto.CatalogEventOutput;

@RestController
@RequestMapping("/catalog/showcase")
public class CatalogShowcaseController {
    private final CatalogBrowseShowcaseUseCase browseShowcaseUseCase;

    public CatalogShowcaseController(CatalogBrowseShowcaseUseCase browseShowcaseUseCase) {
        this.browseShowcaseUseCase = browseShowcaseUseCase;
    }

    @GetMapping
    List<CatalogEventOutput> browseShowcase() {
        return browseShowcaseUseCase.execute();
    }
}
