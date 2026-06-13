package dio.marketplace.ticketing.domain;

public class TicketingSeatAlreadyReservedException extends RuntimeException {
    public TicketingSeatAlreadyReservedException(TicketingEventId eventId, TicketingSeatId seatId) {
        super("Seat is already reserved.");
    }
    
}
