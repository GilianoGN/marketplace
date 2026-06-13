package dio.marketplace.ticketing.application;

import org.springframework.stereotype.Service;

import dio.marketplace.ticketing.domain.TicketingCustomerId;
import dio.marketplace.ticketing.domain.TicketingEventId;
import dio.marketplace.ticketing.domain.TicketingEventRepository;
import dio.marketplace.ticketing.domain.TicketingSeatAlreadyReservedException;
import dio.marketplace.ticketing.domain.TicketingSeatId;
import dio.marketplace.ticketing.domain.TicketingSeatNotFoundException;

@Service
public class TicketingSelectSeatUseCase {
    private final TicketingEventRepository eventRepository;

    public TicketingSelectSeatUseCase(TicketingEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void execute(TicketingEventId eventId, TicketingSeatId seatId, TicketingCustomerId customerId) {
        if (!eventRepository.existsSeat(eventId, seatId)) {
            throw new TicketingSeatNotFoundException(eventId, seatId);
        }

        boolean lock = eventRepository.tryLockSeat(eventId, seatId, customerId);

        if (!lock) {
            throw new TicketingSeatAlreadyReservedException(eventId, seatId);
        }

        // Order, Payment, ...
    }
}
