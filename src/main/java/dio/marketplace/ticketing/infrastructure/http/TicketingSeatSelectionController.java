package dio.marketplace.ticketing.infrastructure.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dio.marketplace.ticketing.application.TicketingSelectSeatUseCase;
import dio.marketplace.ticketing.domain.TicketingCustomerId;
import dio.marketplace.ticketing.domain.TicketingEventId;
import dio.marketplace.ticketing.infrastructure.http.request.TicketingSeatSelectionRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/ticketing/events/{eventId}/seats")
public class TicketingSeatSelectionController {
    private final TicketingSelectSeatUseCase selectSeatUseCase;

    public TicketingSeatSelectionController(TicketingSelectSeatUseCase selectSeatUseCase) {
        this.selectSeatUseCase = selectSeatUseCase;
    }

    @PostMapping("/select")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "ticketingService", fallbackMethod = "fallbackSelectSeat")
    public void selectSeat( @PathVariable String eventId, 
                            @RequestBody TicketingSeatSelectionRequest request, 
                            @RequestHeader("X-CUSTOMER-ID") String customerId) {
        selectSeatUseCase.execute(new TicketingEventId(eventId), request.toInput(), new TicketingCustomerId(customerId));
    }

    public void fallbackSelectSeat(String eventId, TicketingSeatSelectionRequest request, String customerId, Throwable t) {

        throw new RuntimeException("Serviço de bilheteria temporariamente indisponível. Tente novamente mais tarde.");
    }
}