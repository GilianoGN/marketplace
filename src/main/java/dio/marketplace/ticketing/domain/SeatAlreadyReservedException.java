package dio.marketplace.ticketing.domain;

public class SeatAlreadyReservedException extends RuntimeException {
    public SeatAlreadyReservedException(EventId eventId, SeatId seatId) {
        super("Seat is already reserved.");
    }
    
}
