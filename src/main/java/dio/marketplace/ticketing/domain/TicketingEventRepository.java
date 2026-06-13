package dio.marketplace.ticketing.domain;

public interface TicketingEventRepository {
    void save(TicketingEvent event);

    boolean existsSeat(TicketingEventId eventId, TicketingSeatId seatId);

    boolean tryLockSeat(TicketingEventId eventId, TicketingSeatId seatId, TicketingCustomerId customerId);
}
