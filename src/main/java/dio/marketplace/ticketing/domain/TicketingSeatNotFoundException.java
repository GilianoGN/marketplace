package dio.marketplace.ticketing.domain;

public class TicketingSeatNotFoundException extends RuntimeException{
    public TicketingSeatNotFoundException(TicketingEventId eventId, TicketingSeatId seatId) {
        super("Seat with id " + seatId.id() + " not found.");
    }
}